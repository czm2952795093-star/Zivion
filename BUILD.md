# 构建 Zivion

需要 Python 3.9+、JDK 17、Android SDK Platform 34 和 Build Tools 34.0.0。

```powershell
python build.py --java-home "C:/path/to/jdk17" --sdk "C:/path/to/Android/Sdk"
```

也可设置 JAVA_HOME 与 ANDROID_SDK_ROOT 后执行 `python build.py`。输出为 `build/app.apk`。首次构建生成本地签名文件，请保留用于后续升级。源码包不包含发布签名私钥；自行签名的包不能直接覆盖官方发布包。

计算模块需要 Node.js 18 或更新版本：

```powershell
cd calculation-engine
npm ci
node test.js
npx esbuild engine.js --bundle --platform=browser --format=iife --global-name=ZiweiEngine --target=chrome61 --outfile=../assets/engine.bundle.js
```

返回项目根目录后构建 APK。依赖版本由 package-lock.json 固定。历表可使用 `node calculation-engine/generate-calendar.js assets/calendar-months.json` 生成。
