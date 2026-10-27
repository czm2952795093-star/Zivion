# Zivion

基于 REA 证据持续开发的紫微星语独立安卓复刻项目。当前仓库保存源码、版本快照、原版对照证据、验证结果和开发记录。

**这是0.10.0流月开发分支，完整验收尚未完成。已验证版本请使用main或v0.9.0标签。**

- [当前进展](docs/STATUS.md)
- [开发日志](docs/DEVELOPMENT_LOG.md)
- [版本变化](CHANGELOG.md)
- [后续工作](docs/ROADMAP.md)
- [构建和依赖说明](BUILD.md)
- [测试说明](tests/README.md)
- [0.10.0 开发分支](https://github.com/czm2952795093-star/Zivion/tree/develop/monthly-0.10.0)
- [0.9.0 版本说明](docs/releases/0.9.0.md)

## 已有功能

本地排盘、十二宫显示、命例/历史/笔记、搜索及备份恢复、本命/大限/流年切换、虚岁查询、大限流年同屏、四化来源选择、标记与连线、复制盘面。

算法由独立计算模块实现，没有使用原版原生算法库或绕过其签名校验。应用没有联网权限。有限样本中的一致不代表所有日期、设置和功能完全一致；仍有庙旺、太阳时精度、工具页面及界面细节差异。

## 版本历史的来源

v0.1.0～v0.9.0 从本机已交付源码快照导入。Git提交时间是本次导入时间，并非伪造的原始开发时间。各版本说明保留在 `docs/releases/`，对照记录在 `rea-evidence/`。`main` 保持已验证代码，未完成工作保存在开发分支。

仓库不包含原版APK、签名私钥、凭据、模拟器偏好文件或Android SDK。第三方依赖许可见 [THIRD_PARTY_NOTICES.txt](THIRD_PARTY_NOTICES.txt)。
