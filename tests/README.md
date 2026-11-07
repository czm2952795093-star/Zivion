# 验证

0.14.2已完成13185项换算检查与129项非闰月边界检查，覆盖香港天文台2025/2026共730个官方日期，以及其中23个完整非闰月的月末。官方CSV保存在tests/calendar-data；calendar-official.json是转录结果。

使用JDK和org.json 20240303编译CalendarLookup.java、CalendarLookupTest.java和RegularMonthTest.java后运行：

```powershell
java -cp "tests/classes;tests/json-20240303.jar" CalendarLookupTest assets/calendar-months.json tests/calendar-official.json
java -cp "tests/classes;tests/json-20240303.jar" RegularMonthTest assets/calendar-months.json tests/calendar-official.json
```

Android 9模拟器通过5组交互检查：小月拒绝/取消/改正、大月与切月/重启、范围外保护、普通月与闰月区分、公历填入与记录保留。最终包另核对版本说明、恢复和复制结果。验证摘要见results/0.14.2.json。未完成全部年份逐日外部对照或更多实体设备验证。


## 0.15.0 阴历日期选择

编译最新CalendarLookup.java、LunarDateSelection.java与tests/LunarDateSelectionTest.java后运行：

```powershell
java -cp "tests/classes;tests/json-20240303.jar" LunarDateSelectionTest assets/calendar-months.json tests/calendar-official.json
```

预期PASS7604。覆盖730个官方日期、1900—2100年的逐年月份列表、闰月切换、大小月调整、时分秒保留和无效变更保护。13185项换算与129项非闰月边界检查在本轮重新通过。

Android 9中验证6组交互：取消与日期调整、跨年闰月处理、键盘时分秒/立即关闭与恢复、无效旧日期、直接键盘改年填入、明确起盘及记录保留。另核对公历入口与1.3倍系统字体。范围与摘要见results/0.15.0.json。


## 0.15.1 出生日期显示

编译BirthDateText.java与tests/BirthDateTextTest.java（依赖org.json）后执行：

```powershell
java -cp "tests/classes;tests/json-20240303.jar" BirthDateTextTest
```

预期PASS21，覆盖闰月标记及前缀归一、阴历/公历、秒与输入格式，确认不修改计算结果。4组Android 9交互验证输入切换、选择器填入、隐藏/模式切换/重启、普通和闰月记录；另检查1.3倍字体换行。记录未发生变化。详情见results/0.15.1.json。既有日期/模型测试在本轮沿用历史结果，不计入本轮新测试。


## 0.16.0 流曜

编译 `FlowStarState.java`、`FlowState.java` 和 `FlowStarStateTest.java`，使用 org.json 依赖运行：

```powershell
java -cp "tests/classes;tests/json-20240303.jar" FlowStarStateTest tests/flow-fixtures.json
```

预期通过48项检查。验证摘要见 `results/0.16.0.json`。
