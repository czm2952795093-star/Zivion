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

## 0.9.0：双层叠宫与四化来源选择

在根目录执行下列新增检查（与上述FlowStateTest一起运行）：

```powershell
node tests/generate-flow-v09.js
javac --release 8 -encoding UTF-8 -cp tests/json-20240303.jar -d tests/classes src/local/ziweixingyu/rebuild/FlowState.java tests/FlowSelectionTest.java
java -cp "tests/classes;tests/json-20240303.jar" FlowSelectionTest tests/flow-v09.json
```

预期PASS486：新增原版6个状态、48次来源选择、不移动叠宫、不改写原始数据以及无效选择。2组输入此前曾用于本命对照，本次采集的是新流运状态。原版截图与静态依据索引见rea-evidence/v09/flow-observations.json，安装包交互检查见flow-ui-test.json。0.8的1385项检查继续通过；本命引擎字节未变，沿用0.8已有84组结构和56份本命对照结果。

## 0.10.0：流月月序

```powershell
node tests/generate-month-fixtures.js
javac --release 8 -encoding UTF-8 -cp tests/json-20240303.jar -d tests/classes src/local/ziweixingyu/rebuild/FlowState.java src/local/ziweixingyu/rebuild/MonthState.java tests/MonthStateTest.java
java -cp "tests/classes;tests/json-20240303.jar" MonthStateTest tests/month-fixtures.json
```

预期PASS1238。固定月份位置/月干来自5个出生输入的7个原版年度截图状态（其中4个新截图、3个旧截图）；84项是月份标注，不是完整流月命盘。月宫旋转来自REA静态代码。月序边界、所选宫位四化来源、本命数据隔离、无效输入也被检查。原1385和486检查继续通过。原版证据索引在rea-evidence/v10/month-observations.json，安装包结果在month-ui-test.json。

本命引擎与0.9.0字节一致；本轮没有重复运行全部56份本命对照，沿用已保存结果。新生成的month-fixtures.json只用于独立引擎输入，不是原版输出。

## 0.11.0：年份查询与状态一致性

```powershell
javac --release 8 -encoding UTF-8 -cp tests/json-20240303.jar -d tests/classes src/local/ziweixingyu/rebuild/FlowState.java tests/YearQueryTest.java
java -cp "tests/classes;tests/json-20240303.jar" YearQueryTest tests/month-fixtures.json
```

预期PASS1260：5份既有输入、每份120虚岁、农历出生年换算、上下界、非法年份、数据隔离。1385、486、1238项也在本版重新运行通过。Android端交互检查见rea-evidence/v11/query-ui-test.json；升级记录保留及安装包身份见同目录update-data-test.json和artifact-check.json。UI脚本依赖本地ADB、REA和剪贴板探针，不作为独立可移植测试发布。
