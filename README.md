# Zivion

基于 REA 证据持续开发的紫微星语独立安卓复刻项目。当前仓库保存源码、版本快照、原版对照证据、验证结果和开发记录。

**已验证版本：0.13.0（继续上次命盘与草稿保留）。整款 APP 尚未与原版完全一致。**

- [当前进展](docs/STATUS.md)
- [开发日志](docs/DEVELOPMENT_LOG.md)
- [开发与主动同步约定](docs/DEVELOPMENT_WORKFLOW.md)
- [版本变化](CHANGELOG.md)
- [后续工作](docs/ROADMAP.md)
- [构建和依赖说明](BUILD.md)
- [测试说明](tests/README.md)
- [0.13.0 开发记录](https://github.com/czm2952795093-star/Zivion/tree/develop/resume-0.13.0)
- [0.13.0 版本说明](docs/releases/0.13.0.md)

[下载安装包和源码](https://github.com/czm2952795093-star/Zivion/releases/tag/v0.13.0)

## 已有功能

本地排盘、十二宫显示、命例/历史/笔记、搜索及备份恢复、本命/大限/流年/流月切换、虚岁／年份查询、大限流年流月同屏、正月至腊月选择、手选农历日与时辰叠宫、四化来源选择、标记与连线、复制盘面、重启后继续上次命盘与独立输入草稿保留。

算法由独立计算模块实现，没有使用原版原生算法库或绕过其签名校验。应用没有联网权限。有限样本中的一致不代表所有日期、设置和功能完全一致；仍有庙旺、太阳时精度、工具页面及界面细节差异。

## 版本历史的来源

v0.1.0～v0.9.0 从本机已交付源码快照导入。Git提交时间是本次导入时间，并非伪造的原始开发时间。各版本说明保留在 `docs/releases/`，对照记录在 `rea-evidence/`。`main` 保持已验证代码，未完成工作保存在开发分支。

仓库不包含原版APK、签名私钥、凭据、模拟器偏好文件或Android SDK。第三方依赖许可见 [THIRD_PARTY_NOTICES.txt](THIRD_PARTY_NOTICES.txt)。
