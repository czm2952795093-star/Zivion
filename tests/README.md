# 验证

0.14.2已完成13185项换算检查与129项非闰月边界检查，覆盖香港天文台2025/2026共730个官方日期，以及其中23个完整非闰月的月末。官方CSV保存在tests/calendar-data；calendar-official.json是转录结果。

使用JDK和org.json 20240303编译CalendarLookup.java、CalendarLookupTest.java和RegularMonthTest.java后运行：

```powershell
java -cp "tests/classes;tests/json-20240303.jar" CalendarLookupTest assets/calendar-months.json tests/calendar-official.json
java -cp "tests/classes;tests/json-20240303.jar" RegularMonthTest assets/calendar-months.json tests/calendar-official.json
```

Android 9模拟器通过5组交互检查：小月拒绝/取消/改正、大月与切月/重启、范围外保护、普通月与闰月区分、公历填入与记录保留。最终包另核对版本说明、恢复和复制结果。验证摘要见results/0.14.2.json。未完成全部年份逐日外部对照或更多实体设备验证。
