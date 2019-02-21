# Pang-Blog个人博客

# 1、功能概要

- 首页展示问候语及热门文章（点击量较多的文章）
- 文章列表及分组显示
- 文章预览及评论回复功能
- 用户留言版
- 后台可以对网站图标、首页问候语等进行设置
- 分组、文章管理
- markdown语法编辑器，支持图片文档

# 2、后台部分（代码见spring分支）

后台使用当前比较热门的**[Spring boot](https://spring.io/projects/spring-boot/)**框架进行开发，同时集成了*myBatis*、*Cors*、*Swagger2*等进行开发或管理。

Api全局使用了**Restful Api**风格，实现了前后端分离的开发模式。

采用**jwt(JSON Web Token )**方式进行登陆验证。

# 3、前端部分（代码见vue分支）

前端部分使用了渐进式 JavaScript 框架**[Vue.js](https://cn.vuejs.org/)**和**[iView](https://www.iviewui.com/)**UI框架。

使用**[vue-router](https://router.vuejs.org/zh/)**进行路由管理。

使用**[Vuex](https://vuex.vuejs.org/zh/)**进行状态管理。

使用**[axios](https://www.axios.com/)**进行http访问。

使用**[mavonEditor](https://github.com/hinesboy/mavonEditor)**进行文章编写。

使用**[vue-dnd](http://hilongjw.github.io/vue-dragging/)**进行分组顺序拖拽。