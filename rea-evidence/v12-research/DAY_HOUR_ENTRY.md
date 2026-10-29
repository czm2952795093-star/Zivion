# 流日／流时入口与字段：补充静态证据

日期：2026-10-29。目标为用户提供的原版 APK，SHA256 `fb87cd02a3cea411b0a07b4ec69f361571f13333a191bccc0ae0b638a98e4985`。本调查只复用已有 REA 结果并补取方法／调用引用，没有操作模拟器、修改原版或绕过签名。运行样本另见主流程的 v12 目录。

## 已确认的入口与状态

1. `zwpview.onTouchEvent` 的点击表中，“流盘区域·切换流盘模式”指向 `x1.y2.a()` case 9，将 `Liupantype` 按 `0→1→2→0` 循环；其中 2 是流年层。`g(Canvas)` 在此模式且 `J2=false` 时画月、日、时宫位。依据：`../v08/original-onTouch.json`、`../v08/original-flow-actions.json`、`../v08/original-flow-render.json`。
2. 年龄加减的 case 11/12 调用 `setLiurishiflag(true)`，令 `J2=true`，隐藏日时层。它不是“选择日／时”的入口。
3. 点击表中的“切换年月日时星”指向 case 8，仅将 `qiehuan` 在 0～6 之间循环。不能由菜单名推断它提供年月日时输入，或控制 `J2`。
4. 点击表中的“时间区域·更新到当前时间”以及中心左右时辰加减、换日是重新起出生盘的操作；不能将其当作保持出生盘、只改变查询日期的入口。

## 当前时间与协议字段

`chart-init` 已确认 `f3485r = new SimpleDateFormat("yyyy|M|d|H|m|s", Locale.US)`，时区 GMT+8。`getnowtimestr()` 用这个格式化器格式化 `new Date()`。`s(String,String)` 将该字符串写入 `V2`；`onDraw` 使用 `checks + "12|" + V2 + "|" + L2 + "|-8"` 调用原生日期协议。

字段名称易混淆，补取 setter 后确认：

| Java 方法 | 实际字段 | 含义及调查边界 |
|---|---|---|
| `setTimestr(String)` | `V2` | 协议的当前日期时间输入；静态入引用为 0 |
| `setNowtimestr(String)` | `C2` | 原生协议的返回字符串，不能当作日期输入；静态入引用为 0 |
| `setNowstrx(String)` | `E2` | 协议第 2 段；静态入引用为 0 |
| `setLiuriliushiflag(boolean)` | `K2` | 已完成本次日时协议取值的标志；静态入引用为 0 |
| `setLiurishiflag(boolean)` | `J2` | 日时层隐藏标志；已有年龄加减调用证据 |

对上述 setter 的引用搜索没有发现正常 UI 的任意查询日期选择入口。此结论只覆盖 REA/JADX 静态 using-node 结果，不能排除反射或未分析的动态路径，不能表述为全应用绝不存在日期查询功能。

对 `getNowstrx`、`getNowsizhustr` 的静态引用也为 0。复核已有 `r(Canvas)` smali 与 `a(Canvas)` 反编译正文未找到 `D2/E2/C2` 的直接显示位置；这次调查未证实正常界面能直接读出完整六段协议。`r(Canvas)` 有 `V2` 的格式化路径，显示的是输入时间，不等于原生换算结果。

## 重要的原版缓存行为

`s(String,String)` 读取全局 `lastxusui`／`lastxusuitime` 缓存，非空缓存能被用于计算虚岁。`onDraw` 在流年模式、`J2=false`、`K2=false` 时重新查询当前日期协议并更新 `D2/E2/F2/G2/H2/I2`，但此处没有重新计算虚岁。

因此原版默认流年的年龄／年份与当前日时输入可能不同步。此前“设备当前年为 2026 而默认流年为 2027”可能与此缓存有关，这是基于代码的解释假设，不能计作已验证的因果结论；运行取证时必须同时记录显示的流年、当前日期和实际月日时宫位，不应把默认流年直接当作当前农历年份。

## 宫位顺序与样式

- 月命：`o(B2, 月序)`；日命：`o(月命, 日序 - 1)`；时命：`o(日命, 时支索引)`。时支索引按 `子丑寅卯辰巳午未申酉戌亥`，子为 0。
- 各层从命宫起沿相同地支方向依次放 `命、父、福、田、官、友、迁、疾、财、子、夫、兄`。原循环从偏移 1 开始，依次绘 `父…兄、命`，最后偏移 12 回到命宫。
- 月色 `#FC9631`，日色 `#9752CF`，时色 `#42B0B1`。`g(Canvas)` 使用 `h(9.0f)` 文字大小，左对齐，各层分别在格底上方 `h(44)`／`h(52)`／`h(60)`，水平位置为格右侧减 `h(39)`。这是原版代码样式观察，不是复刻版必须照搬的像素验收。
- 日时层绘制不会将四化来源自动切成日命／时命；既有 `g(Canvas)` 后续仍按流年／选择宫确定四化来源。

## 证据索引

既有证据：`../ui/chart-init.json`（`ev_1a609c3baf83f7a65f10db132cfbcd4af4c399c8edb6cd2ec8936500535253ee`）、`../v11-research/chart-s.json`、`../v11-research/current-time-input.json`、`../ui/chart-onDraw.json`、`../v08/original-onTouch.json`、`../v08/original-flow-actions.json`、`../v08/original-flow-render.json`。完整索引亦见 `../v11-research/DAY_HOUR_FINDINGS.md`。

| 本次文件 | Evidence ID |
|---|---|
| `set-timestr.json` | `ev_767786f17fdaca9604fe2eab1b684dcf6d7e3320ee3b164a28bd3546fc8518ec` |
| `timestr-references.json` | `ev_4ff47fb3416e60f87b4aa4a71782281b136363bf281947ec62e293908ec34aa7` |
| `set-nowtime.json` | `ev_a820a6e62a7673c1a3d88098fb692fd3e6de0c52e0ed3037258a604f0fd032ca` |
| `nowtime-references.json` | `ev_160d3eca5e863ef62d753f8c9468747faff0777845f64ef6f5dbe98f8d7f652d` |
| `set-nowstrx.json` | `ev_912276941fdd7fc0333f2cc6da41e4f9218fff4ff11fbda733d796aa90c5ac96` |
| `nowstrx-set-references.json` | `ev_f169fa42c2e6bf3da1cc2f52a386f9203c6a05d13e60ecd9df72ad6ed51e40db` |
| `set-dayhour-protocol-cached.json` | `ev_d91612f56acf6f8358ea6e4bd459801eb0d4f8a5e88dac29f83c46a9c83281c8` |
| `dayhour-render-references.json` | `ev_e820e404b8384a9e59bf7ac2c1ea825727311ba6955f578bfe10674bfe7bb51f` |
| `nowstrx-read-references.json` | `ev_46b9eb3ce9eecd3d02428dc13a8aba67bc10d1bc3977101957ff7512150733d1` |
| `pillars-read-references.json` | `ev_250ae2830af2e5351aded68ce36d5cb1db02408328ad58d61f92900b90019fda` |

原生公历转农历、真太阳时、闰月和子时换日仍是未恢复／未完成运行验收的范围。手选月序、日序、时支的宫位预览可以明确采用上述已确认位移规则，但不能声称已经恢复自动日期换算或完整流日／流时排盘。
