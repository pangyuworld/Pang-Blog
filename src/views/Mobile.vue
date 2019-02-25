<template>
  <div id="app" class="van-hairline--surround">
    <van-nav-bar
      :title="barTitle"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
      @click-right="onClickRight"
      :fixed="true"
    >
      <Dropdown trigger="custom" :visible="visible" slot="right" @on-click="check">
        <a href="javascript:void(0)">
          <Icon type="md-menu"/>
        </a>
        <DropdownMenu slot="list">
          <DropdownItem name="主页">
            <router-link to="/sj">主页</router-link>
          </DropdownItem>
          <DropdownItem name="文章">
            <router-link to="/sj/article-list">文章</router-link>
          </DropdownItem>
          <DropdownItem name="留言">
            <router-link to="/sj/message">留言</router-link>
          </DropdownItem>
          <DropdownItem>
            <a :href="git">Git</a>
          </DropdownItem>
          <DropdownItem name="后台">
            <router-link to="/sj/admin">后台</router-link>
          </DropdownItem>
        </DropdownMenu>
      </Dropdown>
    </van-nav-bar>
    <div class="content">
      <router-view></router-view>
      <Divider></Divider>
    </div>
    <div class="blog-footer">
      <div class="blog-footer-code">
        <span>
          ©{{year}}
          <a href="https://pangyuworld.cn">{{adminer}}</a>
          ,{{beian}}
        </span>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      visible: false,
      year: 0,
      adminer: null,
      beian: null,
      theme: "light",
      icon: null,
      git: null,
      adress: null,
      barTitle: "主页"
    };
  },
  methods: {
    onClickLeft() {
      window.history.go(0);
    },
    onClickRight() {
      this.visible = this.visible ? false : true;
    },
    getIcon() {
      var that = this;
      this.$http({
        method: "get",
        url: that.GLOBAL.adress + "/config/blog-icon"
      }).then(function(res) {
        that.icon = that.GLOBAL.adress + res.data.data;
      });
    },
    getGit() {
      var that = this;
      this.$http({
        method: "get",
        url: that.GLOBAL.adress + "/config/blog-git"
      }).then(function(res) {
        that.git = res.data.data;
      });
    },
    getBeian() {
      var that = this;
      this.$http({
        method: "get",
        url: that.GLOBAL.adress + "/config/blog-filing"
      }).then(function(res) {
        that.beian = res.data.data;
      });
    },
    getAdminer() {
      var that = this;
      this.$http({
        method: "get",
        url: that.GLOBAL.adress + "/config/blog-adminer"
      }).then(function(res) {
        that.adminer = res.data.data;
      });
    },
    getAdress() {
      var that = this;
      this.$http({
        method: "get",
        url: that.GLOBAL.adress + "/config/blog-adress"
      }).then(function(res) {
        that.adress = res.data.data;
      });
    },
    check(a) {
      this.barTitle = a;
    }
  },
  mounted() {
    this.getIcon();
    this.getGit();
    this.getAdminer();
    this.getBeian();
    var date = new Date();
    this.year = date.getFullYear();
  }
};
</script>
<style>
.blog-footer-code {
  text-align: center;
  font: inherit;
  font-size: 100%;
  margin-bottom: 20px;
}
.content {
  background-color: #f8f8f9;
  margin-top: 46px;
}
</style>
