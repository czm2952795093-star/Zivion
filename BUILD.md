# 紫微星语复刻验证版 0.11.0

原生Java安卓页面，使用打包在应用内的独立计算模块。无服务器或联网权限；尚未与原版完全一致。

## 本版变化

新增按农历年份编号查询，自动换算虚岁、保留当前月序；修复输入草稿污染旧盘标题、返回命盘丢失选宫高亮、切换时期残留旧宫详情和“命宫宫”重复文字。关于页使用实际安装版本。复制摘要增加事项。

年份查询1260项检查通过，既有流运1385、来源选择486、月序1238项继续通过；Android9离线模拟器完成对应操作回归、数据保留及安装包一致性检查。具体范围见随包《复刻说明》及rea-evidence/v11。测试命例不是新增原版证据。

本命引擎与0.10.0字节一致，月序计算未改，沿用此前本命及月份原版对照证据。新日时静态研究见rea-evidence/v11-research；日期换算与边界尚未验收，本版未推出日时查询。

## 源码结构

- `src/local/ziweixingyu/rebuild/MainActivity.java`：输入、命盘、宫位详情及复制文字。
- `FlowState.java`、`FlowController.java`：流运状态、年龄边界、宫位叠加、四化摘要及切换控件。
- `MonthState.java`：月序落宫、月干、所选月宫位以及月序边界。
- `RecordBrowser.java`：命例、历史、笔记搜索及编辑入口。
- `RecordPolicy.java`、`BackupCodec.java`、`BackupManager.java`：容量限制、备份校验、合并和系统文件选择器。
- `LocalEngine.java`：仅加载本地资源的计算适配器。
- `calculation-engine/engine.js`：独立计算入口；`birth-year.js`、`auxiliary-rules.js` 为此前推导的兼容规则。
- `transformation-strength.js`、`observed-strength.json`：四化力度显示；`observed-strength-v06.json` 只用于版本对照，不进入运行打包。
- `observed-brightness.json`：552项庙旺显示校准，未覆盖的组合沿用独立库；本版未扩充。
- `compare-v07.js`：56份原版结构和力度对照、来源与冻结哈希校验。
- 根目录 `analyze_strength_v07.py`：从50份开发样本文字重建力度证据，不读取本轮独立验证样本。
- `rea-evidence/`：原始样本文字及分析结果；`v08/`保存流运静态依据，`v09/`为来源选择对照，`v10/`为本次月份依据与检查。`tests/`含数据保存及流运状态检查。
- `THIRD_PARTY_NOTICES.txt`：依赖许可原文。

## 构建 APK

需要 Python 3.9+、JDK 17、Android SDK Platform 34 和 Build Tools 34.0.0。

```powershell
python build.py --java-home "C:/path/to/jdk17" --sdk "C:/path/to/Android/Sdk"
```

也可以设置 `JAVA_HOME` 与 `ANDROID_SDK_ROOT` 后执行 `python build.py`。输出为 `build/app.apk`。首次构建生成本地签名文件，请保留用于后续更新；源码包不含交付安装包的签名私钥。

## 修改与验证计算模块

使用 Node.js 18 或更新版本。

```powershell
cd calculation-engine
npm ci
node test.js
node compare-v07.js --verify
npx esbuild engine.js --bundle --platform=browser --format=iife --global-name=ZiweiEngine --target=chrome61 --outfile=../assets/engine.bundle.js
```

返回根目录重新构建APK。依赖固定于 `package-lock.json`，包括 iztro 2.6.2、lunar-typescript。对照脚本中的比较辅助函数仅在Node环境使用，运行包保持旧WebView兼容。

## 数据和限制

命例、历史、笔记保存在本应用私有数据中，每类最多5000条。满额拒绝新增并保留旧数据；已有笔记仍可编辑。标题最多500字，笔记正文最多100000字。

“备份与恢复”导出JSON或合并恢复，保留当前记录、跳过内容相同的导入项；已有重复历史不会清理。上限8,000,000个UTF-8字节，导出前预检。格式兼容本复刻版0.2以后版本，不含显示设置，也不兼容原版专有格式。卸载应用会删除未导出的本地数据。

时间按UTC+8输入，经度东正西负，太阳时为近似模型；直接输入真太阳时时不重复校正。新安装默认关闭“换月”，升级保留已有选择。“换月”开启仍采用独立库的闰月十五日约定，尚未证明所有边界一致。内部扩展时间协议保持兼容旧复刻存档。

原版APK未改动，也没有绕过原生签名检查。启动图标来自用户所供APK的REA解码，其他运行代码独立实现。原版样本对照限于公历、东八区、东经120°、关闭“换月”。流月当前是正月至腊月的月序查询，不区分所查年份的闰月，也不换算实际起止日期。流日、流时、流曜和更复杂的标记与连线尚未加入；默认年龄、加减边界、复制摘要与新增流月入口和原版有区别。流运色块暂时代替本命生年四化；流月层为留出空间，暂时隐藏格内小限年龄与长生文字，切回其他模式恢复。部分庙旺、太阳时精度、其他工具和界面细节仍待完善。
