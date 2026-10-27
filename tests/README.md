# 数据保存检查

使用 JDK 17 和 `org.json:json:20240303` 在桌面运行独立逻辑检查。JSON依赖用于测试，不打入安卓安装包；安卓运行时使用系统JSON实现。

在源码包根目录执行，以下示例假定该依赖位于 `tests/json-20240303.jar`，JDK已加入PATH：

```powershell
javac --release 8 -encoding UTF-8 -cp tests/json-20240303.jar -d tests/classes src/local/ziweixingyu/rebuild/RecordPolicy.java src/local/ziweixingyu/rebuild/BackupCodec.java tests/BackupCodecTest.java tests/RecordPolicyTest.java
java -cp "tests/classes;tests/json-20240303.jar" BackupCodecTest
java -cp "tests/classes;tests/json-20240303.jar" RecordPolicyTest
```

预期通过16项备份格式、合并和重复恢复检查，以及24项保留记录、满额拒绝新增、文字长度和中文UTF-8备份大小检查。

安装包另在Android 9模拟器检查：1001条命例和61条历史继续保存时全部保留；每类5000条时拒绝新增；满额笔记可编辑。模拟测试使用临时数据，结束时恢复原有偏好文件，逐字节确认一致。
