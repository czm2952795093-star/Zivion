# 流运和数据保存检查

使用 JDK17、Node18+，桌面测试依赖 `org.json:json:20240303`（自行取得，放在 `tests/json-20240303.jar`，不打入安卓）。安卓运行使用系统JSON实现。

源码包根目录执行：

```powershell
cd calculation-engine
npm ci
node test.js
node compare-v07.js --verify
cd ..
node tests/generate-flow-fixtures.js
javac --release 8 -encoding UTF-8 -cp tests/json-20240303.jar -d tests/classes src/local/ziweixingyu/rebuild/FlowState.java tests/FlowStateTest.java
java -cp "tests/classes;tests/json-20240303.jar" FlowStateTest tests/flow-fixtures.json
```

预期 `PASS 1385 checks`。测试的6组固定预期从原版截图人工核对，见 `rea-evidence/v08/flow-observations.json`；`flow-fixtures.json` 仅为独立引擎生成的输入命盘，不能作为原版证据。另检查男女各1～120虚岁的结构、年龄边界和本命数据隔离。这6组均属开发对照，未另外采集流运留出样本。

安装包实测记录见 `rea-evidence/v08/flow-ui-test.json`。UI脚本依赖工作环境中的辅助ADB和REA配置，未包含在通用源码包内。

旧版备份、数据容量测试继续保留。源码包根目录执行：

```powershell
javac --release 8 -encoding UTF-8 -cp tests/json-20240303.jar -d tests/classes src/local/ziweixingyu/rebuild/RecordPolicy.java src/local/ziweixingyu/rebuild/BackupCodec.java tests/BackupCodecTest.java tests/RecordPolicyTest.java
java -cp "tests/classes;tests/json-20240303.jar" BackupCodecTest
java -cp "tests/classes;tests/json-20240303.jar" RecordPolicyTest
```

预期16项备份格式、合并检查，24项记录容量、文字长度和中文UTF-8大小检查。0.8.0未改动保存模块，本轮检查覆盖升级保留记录，完整容量实机测试沿用0.4.0记录。
