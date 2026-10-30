# 公历换算证据与限制

2026-10-30。官方日历来源、摘要见sources.json；原始CSV和转录JSON随源码保留。历表生成器calculation-engine/generate-calendar.js读取锁定的lunar-typescript1.8.6，生成assets/calendar-months.json，不写入本命engine.bundle.js。

民用历法按UTC+8午夜换日。小时分支按钟表小时分配；23点明确拒绝叠宫，闰月也拒绝叠宫。不是对原版原生日期协议的复原；无原版自动日期边界新运行证据。既有REA位移依据见../v12-research/DAY_HOUR_ENTRY.md。

730个官方日期仅覆盖2025与2026。范围端点、输入校验、时区与历表连续性是程序性质检查，不能代替全范围外部日历核对。Android运行测试用于功能与数据保留验收，不充当原版一致性证据。

UI首轮已通过预览取消，但填入后首次复制摘要断言失败；随后现场重新读取的摘要及持久状态均正确。加入界面等待后续验，春节摘要又出现同类断言失败。检查发现辅助剪贴板Activity启动未等待完成，存在读到旧结果文件的竞争；最后改用am start -W等待辅助Activity启动，重新核对春节年界和恢复摘要。所有阶段使用同一安装包，保留calendar-ui-run.log、calendar-ui-resume.log、calendar-ui-finish.log。没有修改应用算法或移除断言。缺少首两次失败瞬间的完整摘要，因此不能将推测的竞争原因称为已独立复现的唯一根因。
