# 紫微星语复刻验证版

原生 Java 安卓页面 + 打包在应用内的 JavaScript 计算模块。无服务器、无联网权限，不包含原应用的 native 排盘库或访问凭据。

0.3.0 为可运行的复刻验证版。22份原版样本中的70颗星曜名称和落宫均匹配；部分星曜亮度和原版专有功能尚未实现一致，也不能保证其他日期都一致。详见随包说明及比较报告。

## 源码结构

- `src/local/ziweixingyu/rebuild/MainActivity.java`：输入、命盘画布、命例、历史和笔记。
- `src/local/ziweixingyu/rebuild/RecordBrowser.java`：命例、历史和笔记的搜索列表；按原始记录索引打开、编辑和删除，使用 ListView 按需创建可见行。
- `src/local/ziweixingyu/rebuild/LocalEngine.java`：离线计算适配器，WebView只加载本地资源。
- `src/local/ziweixingyu/rebuild/BackupCodec.java`、`BackupManager.java`：备份格式校验、合并和系统文件选择器。
- `assets/engine.bundle.js`：固定依赖打包后的计算实现，随APK离线运行。
- `calculation-engine/engine.js`：可编辑的独立计算适配层。
- `calculation-engine/auxiliary-rules.js`：根据原版样本推导的天魁、天钺、龙德、大耗、劫煞、截空、副截、副旬兼容规则，以生年干支计算，不按日期查找预存命盘。
- `calculation-engine/compare-v03.js`：比较12份既有样本与10份新验证样本，执行 `node calculation-engine/compare-v03.js --verify`。
- `calculation-engine/test.js`：日期边界和命盘结构测试。
- `calculation-engine/observed-brightness.json`：9个原版校准样本中重复出现且无冲突的124个星曜与地支组合，仅修正显示；其余组合继续使用独立库。
- `THIRD_PARTY_NOTICES.txt`：打包依赖的许可原文。

## 构建 APK

需要 Python 3.9+、JDK 17、Android SDK Platform 34 和 Build Tools 34.0.0。

```powershell
python build.py --java-home "C:/path/to/jdk17" --sdk "C:/path/to/Android/Sdk"
```

也可设置 `JAVA_HOME` 与 `ANDROID_SDK_ROOT` 后直接执行 `python build.py`。输出为 `build/app.apk`。本地签名文件首次构建时生成，请保留以便后续更新。源码包不附带交付安装包的签名私钥。

## 修改计算模块

```powershell
cd calculation-engine
npm ci
node test.js
npx esbuild engine.js --bundle --platform=browser --format=iife --global-name=ZiweiEngine --target=chrome61 --outfile=../assets/engine.bundle.js
```

然后返回根目录重新构建。依赖版本锁定在 `package-lock.json`。所用库为 iztro 2.6.2、lunar-typescript 及其依赖；数据结构按 REA 所见界面契约适配，不代表原算法源码。

## 数据和范围

命例、历史、笔记位于本应用私有 SharedPreferences。删除应用会移除这些本地记录；可在输入页“备份与恢复”导出 JSON 文件，重新安装后选择文件合并恢复。备份不包含个人设置，也不兼容原版应用的专有备份格式。恢复前校验整个文件，保留当前记录、跳过内容相同的导入记录；已有重复历史不会被清理。上限8MB、每类5000条记录。

时间按 UTC+8 输入，经度东正西负。太阳时采用近似天文模型；直接输入真太阳时时不再次校正该输入。阴历输入采用独立库的阴阳历换算。“换月”新安装时默认关闭，与原版默认状态一致；开启时采用 iztro 的闰月十五日分界约定，尚未证明所有边界与原版一致。升级保留已有选择和已保存命例中的选择。内部扩展协议在原11字段之后加一位换月标记，以兼容0.1存档；它不是原版协议的直接替代。

项目使用用户提供 APK 经 REA 新解码获得的启动图标作为外观参照。其他运行代码重新实现；原生签名检查未被修改或绕过。

命例和历史支持名称、出生日期搜索，例如 `19900101`；笔记支持标题、内容搜索，英文不区分大小写。清空搜索恢复全部列表，离开列表后再次进入保留本次运行中的查询。删除仍需确认，筛选不会改变记录的身份。
