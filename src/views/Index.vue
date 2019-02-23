<template>
  <Layout id="main-layout">
    <Menu mode="horizontal" :theme="theme" active-name="1" @on-select="ClickMenu">
      <!-- LOGO -->
      <div class="blog-head-logo">
        <router-link to="/">
          <img class="logo-img" :src="icon">
        </router-link>
      </div>
      <!-- 菜单 -->
      <div class="blog-head-nav">
        <MenuItem name="index" to="/">
          <Icon type="ios-home"/>主页
        </MenuItem>
        <MenuItem name="article" to="/article-list">
          <Icon type="md-albums"/>文章
        </MenuItem>
        <MenuItem name="message" to="/message">
          <Icon type="ios-book"/>留言
        </MenuItem>
        <a :href="git">
          <MenuItem name="git">
            <Icon type="logo-github"/>Git
          </MenuItem>
        </a>
        <!-- <Submenu name="theme">
          <template slot="title">
            <Icon type="md-swap"/>主题
          </template>
          <MenuItem name="theme-light">light</MenuItem>
          <MenuItem name="theme-dark">dark</MenuItem>
          <MenuItem name="theme-primary">primary</MenuItem>
        </Submenu> -->
        <!-- 进入后台 -->
        <MenuItem name="admin" to="/admin/config">
          <Icon type="ios-construct"/>后台
        </MenuItem>
      </div>
    </Menu>
    <Content>
      <router-view class="content-detail"></router-view>
    </Content>
    <Footer style="">
      <div class="blog-footer">
        <Divider></Divider>
        <div class="blog-footer-code">
          <span>
            ©{{year}}
            <a href="https://pangyuworld.cn">{{adminer}}</a>
            ,{{beian}}
          </span>
        </div>
      </div>
    </Footer>
     <BackTop></BackTop>
  </Layout>
</template>

<script>
export default {
  name: "index",
  components: {},
  data() {
    return {
      year: 0,
      adminer: null,
      beian: null,
      theme: "light",
      icon:null,
      git:null,
      adress:null
    };
  },
  mounted: function() {
    this.getIcon();
    this.getGit();
    this.getAdminer();
    this.getBeian();
    var date = new Date();
    this.year = date.getFullYear();

  },
  methods: {
    ClickMenu: function(menuId) {
      switch (menuId) {
        case "theme-light":
          this.theme = "light";
          break;
        case "theme-dark":
          this.theme = "dark";
          break;
        case "theme-primary":
          this.theme = "primary";
          break;
      }
    },
    getIcon(){
      var that=this;
      this.$http({
        method:"get",
        url: that.GLOBAL.adress + "/config/blog-icon"
      }).then(function(res){
        that.icon=that.GLOBAL.adress+res.data.data
      })
    },
    getGit(){
      var that=this;
      this.$http({
        method:"get",
        url: that.GLOBAL.adress + "/config/blog-git"
      }).then(function(res){
        that.git=res.data.data
      })
    },
    getBeian(){
      var that=this;
      this.$http({
        method:"get",
        url: that.GLOBAL.adress + "/config/blog-filing"
      }).then(function(res){
        that.beian=res.data.data
      })
    },
    getAdminer(){
      var that=this;
      this.$http({
        method:"get",
        url: that.GLOBAL.adress + "/config/blog-adminer"
      }).then(function(res){
        that.adminer=res.data.data
      })
    },
    getAdress(){
      var that=this;
      this.$http({
        method:"get",
        url: that.GLOBAL.adress + "/config/blog-adress"
      }).then(function(res){
        that.adress=res.data.data
      })
    }
  }
};
</script>
<style>
.blog-footer-code {
  font: inherit;
  font-size: 100%;
  margin-bottom: 20px;
}
.logo-img {
  float: left;
  position: relative;
  height: 30px;
  top: 15px;
  border-radius: 4px;
  left: 20px;
  background-color: #f8f8f9;
}
.blog-head-nav {
  float: right;
}
.content-detail{
}
</style>

