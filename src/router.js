import Vue from 'vue'
import Router from 'vue-router'
import Home from './components/Home.vue'
import ArticleList from './components/ArticleList.vue'
import Article from './components/Article.vue'
import Message from './components/Message.vue'
import Error from './components/error404.vue'
import Login from './components/Login.vue'
import Admin from './components/Admin.vue'
import NewArticle from './components/NewArticle.vue'
import Config from './components/Config.vue'
import UserInfo from './components/UserInfo.vue'
import ArticleManage from './components/ArticleManage.vue'
import EditArticle from './components/EditArticle.vue'
import GroupManage from './components/GroupManage.vue'
import Index from './views/Index.vue'
import Mobile from './views/Mobile.vue'
import MHome from './mobile/Home.vue'
import MArticleList from './mobile/ArticleList.vue'
import MArticle from './mobile/Article.vue'
import MMessage from './mobile/Message.vue'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: Index,
      children: [
        {
          path: '',
          name: "home",
          component: Home
        },
        {
          path: 'login',
          name: "login",
          component: Login
        }, {
          path: 'admin',
          name: "admin",
          component: Admin,
          children: [
            {
              path: 'article',
              component: NewArticle
            }, {
              path: 'config',
              component: Config
            }, {
              path: 'user',
              component: UserInfo
            }, {
              path: 'articlemanage',
              component: ArticleManage
            }, {
              path: 'article/:id',
              component: EditArticle
            }, {
              path: 'group',
              component: GroupManage
            }
          ]
        },
        {
          path: 'article-list',
          name: 'article-list',
          component: ArticleList
        },
        {
          path: 'message',
          name: 'message',
          component: Message
        }, {
          path: 'article/:id',
          name: 'article',
          component: Article,
        },
      ]
    },
    {
      path:'/sj',
      name:'mobile',
      component:Mobile,
      children: [
        {
          path: '',
          name: "home",
          component: MHome
        },
        {
          path: 'login',
          name: "login",
          component: Login
        }, {
          path: 'admin',
          name: "admin",
          component: Admin,
          children: [
            {
              path: 'article',
              component: NewArticle
            }, {
              path: 'config',
              component: Config
            }, {
              path: 'user',
              component: UserInfo
            }, {
              path: 'articlemanage',
              component: ArticleManage
            }, {
              path: 'article/:id',
              component: EditArticle
            }, {
              path: 'group',
              component: GroupManage
            }
          ]
        },
        {
          path: 'article-list',
          name: 'article-list',
          component: MArticleList
        },
        {
          path: 'message',
          name: 'message',
          component: MMessage
        }, {
          path: 'article/:id',
          name: 'article',
          component: MArticle,
        },
      ]
    },
    {
      name: '404',
      path: '/404',
      component: Error
    }
  ]
})
