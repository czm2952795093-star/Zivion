# 紫微星语复刻验证版 0.7.0

原生 Java 安卓页面，加打包在应用内的 JavaScript 计算模块。没有服务器、联网权限或原版原生排盘库。尚未与原版全部功能、算法和外观一致，具体范围见随包《复刻说明》。

## 本版变化

四化力度显示兼容表从359项扩展为468项。每项均有至少两份不同原版命盘支持，没有观察到冲突。48份旧样本加2份针对性样本用于开发；表冻结后另采集6份日期验证，288项力度全部匹配。旧版已确认的数值保持不变。

这只说明当前验证范围中的显示兼容，不代表恢复了原版算法。输入仍由独立模块计算，不按具体日期读取预存命盘；遇到未覆盖的组合仍保留“力度未确认”处理。

## 源码结构

- `src/local/ziweixingyu/rebuild/MainActivity.java`：输入、命盘、宫位详情及复制文字。
- `RecordBrowser.java`：命例、历史、笔记搜索及编辑入口。
- `RecordPolicy.java`、`BackupCodec.java`、`BackupManager.java`：容量限制、备份校验、合并和系统文件选择器。
- `LocalEngine.java`：仅加载本地资源的计算适配器。
- `calculation-engine/engine.js`：独立计算入口；`birth-year.js`、`auxiliary-rules.js` 为此前推导的兼容规则。
- `transformation-strength.js`、`observed-strength.json`：四化力度显示；`observed-strength-v06.json` 只用于版本对照，不进入运行打包。
- `observed-brightness.json`：552项庙旺显示校准，未覆盖的组合沿用独立库；本版未扩充。
- `compare-v07.js`：56份原版结构和力度对照、来源与冻结哈希校验。
- 根目录 `analyze_strength_v07.py`：从50份开发样本文字重建力度证据，不读取本轮独立验证样本。
- `rea-evidence/`：原始样本文字及分析结果；`tests/`：独立数据保存检查。
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

原版APK未改动，也没有绕过原生签名检查。启动图标来自用户所供APK的REA解码，其他运行代码独立实现。原版样本对照限于公历、东八区、东经120°、关闭“换月”。流运、部分庙旺、太阳时精度、其他工具和界面细节仍待完善。
