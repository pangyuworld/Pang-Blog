// 文章列表
<template>
  <Card class="article-card">
    <h2>{{BlogName}}的文章</h2>
    <Layout>
      <!-- 分类菜单 -->
      <Menu mode="horizontal" theme="light" active-name="1" @on-select="selectGroup">
        <MenuItem name="all">全部</MenuItem>
        <div class="layout-nav" v-for="group in groups" :key="group.id">
          <MenuItem :name="group.id" v-if="group.groupShow">{{group.groupName}}</MenuItem>
        </div>
      </Menu>
      <!-- 文章列表 -->
      <Content>
        <Card
          class="article-list"
          v-for="article in articles"
          :key="article.id"
          @v-if="article.show"
        >
          <router-link :to="'/article/'+article.id" style="font-size:14px;">
            <h2>{{showText(article.title)}}</h2>
          </router-link>
          <br>
          <div class="article-body">
            <div class="jianjie">{{deleteHtmlTag(article.texts)}}</div>
          </div>
          <br>
          <time class="time">
            <Icon type="ios-time-outline"/>
            {{article.firstTime}}&nbsp;&nbsp;发布
          </time>
        </Card>
        <Page v-if="showPage" :total="sum" show-total @on-change="nextpage"/>
        <br>
      </Content>
    </Layout>
  </Card>
</template>
<script>
export default {
  data() {
    return {
      BlogName: null,
      groups: null,
      articles: null,
      page: 1,
      perpage: 10,
      sum: 0,
      showPage:true
    };
  },
  mounted: function() {
    this.getSum();
    this.getArticles(this.page, this.perpage);
    this.getGroups();
    this.getBlogName();
      this.$Spin.show();
      setTimeout(() => {
        this.$Spin.hide();
      }, 1000);
  },
  methods: {
    getBlogName() {
      var that = this;
      this.$http({
        method: "get",
        url: that.GLOBAL.adress + "/config/blog-name"
      }).then(function(res) {
        that.BlogName = res.data.data;
      });
    },
    getGroups() {
      var that = this;
      this.$http({
        method: "get",
        url: that.GLOBAL.adress + "/groups"
      }).then(function(res) {
        that.groups = res.data.data;
      });
    },
    getArticles(a, b) {
      var that = this;
      this.$http({
        method: "get",
        url: that.GLOBAL.adress + "/articles" + "?page=" + a + "&perpage=" + b
      }).then(function(res) {
        that.articles = res.data.data;
      });
    },
    deleteHtmlTag(str) {
      str = str.replace(/<[^>]+>|&[^>]+;/g, "").trim(); //去掉所有的html标签和&nbsp;之类的特殊符合
      return str;
    },
    nextpage(page) {
      this.getArticles(page, this.perpage);
    },
    getSum() {
      var that = this;
      this.$http({
        method: "get",
        url: that.GLOBAL.adress + "/article/sum/number"
      }).then(function(res) {
        that.sum = res.data.data;
      });
    },
    selectGroup(groupId) {
      if (groupId == "all") {
        this.getArticles(this.page, this.perpage);
        this.showPage=true
      } else {
        this.showPage=false
        var that = this;
        this.$http({
          method: "get",
          url: that.GLOBAL.adress + "/articles/group/" + groupId
        }).then(function(res) {
          that.articles = res.data.data;
        });
      }
    },
    showText(str) {
      return this.GLOBAL.showText(str);
    }
  }
};
</script>

<style>
.time {
  margin: 0;
  padding: 0;
  border: 0;
  font-size: 100%;
  font: inherit;
  vertical-align: baseline;
  color: #aab2bd;
}
.article-body {
  letter-spacing: 1px;
  color: #656d78;
  font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB",
    "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
}
.article-list {
  text-align: left;
  margin: 10px;
}
.article-card {
  margin: 0 auto;
  text-align: center;
  width: 1050px;
}
</style>
