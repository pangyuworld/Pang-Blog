# Pang-Blog个人博客

### [安装教程](https://github.com/pangyuworld/Pang-Blog/tree/master/%E5%AE%89%E8%A3%85%E6%95%99%E7%A8%8B)

### [更新日志](https://github.com/pangyuworld/Pang-Blog/tree/master/%E6%9B%B4%E6%96%B0%E6%97%A5%E5%BF%97)

### [我的blog（示例demo）](https://pangyuworld.cn)

### 1、功能概要

- 首页展示问候语及热门文章（点击量较多的文章）
- 文章列表及分组显示
- 文章预览及评论回复功能
- 用户留言版
- 后台可以对网站图标、首页问候语等进行设置
- 分组、文章管理
- markdown语法编辑器，支持图片文档

### 2、后台部分（代码见[spring](https://github.com/pangyuworld/Pang-Blog/tree/spring)分支）

后台使用当前比较热门的**[Spring boot](https://spring.io/projects/spring-boot/)**框架进行开发，同时集成了*myBatis*、*Cors*、*Swagger2*等进行开发或管理。

Api全局使用了**Restful Api**风格，实现了前后端分离的开发模式。

采用**jwt(JSON Web Token )**方式进行登陆验证。

### 3、前端部分（代码见[vue](https://github.com/pangyuworld/Pang-Blog/tree/vue)分支）

前端部分使用了渐进式 JavaScript 框架**[Vue.js](https://cn.vuejs.org/)**和**[iView](https://www.iviewui.com/)**UI框架。

使用**[vue-router](https://router.vuejs.org/zh/)**进行路由管理。

使用**[Vuex](https://vuex.vuejs.org/zh/)**进行状态管理。

使用**[axios](https://www.axios.com/)**进行http访问。

使用**[mavonEditor](https://github.com/hinesboy/mavonEditor)**进行文章编写。

使用**[vue-dnd](https://github.com/hilongjw/vue-lazyload)**进行分组顺序拖拽。
