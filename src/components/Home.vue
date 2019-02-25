// 主页
<template>
  <div class="home-box">
    <br>
    <!-- 头部 -->
    <div class="hello-text">{{hello}}</div>
    <!-- 底部推荐 -->
    <br>
    <div class="tuijian">
      <Divider orientation="left">推荐文章</Divider>
      <div class="article-list" v-for="article in clickArticles" :key="article.id">
        <router-link :to="'/article/'+article.id">
          <h2>{{showText(article.title) }}</h2>
        </router-link>
        <br>
        <time class="time">
          <Icon type="ios-time-outline"/>
          {{article.firstTime}}&nbsp;&nbsp;发布
        </time>
        <Divider></Divider>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      hello: null,
      clickArticles: null,
      imgList: null
    };
  },
  mounted: function() {
    this.getHello();
    this.getClick();
      this.$Spin.show();
      setTimeout(() => {
        this.$Spin.hide();
      }, 1000);
  },
  methods: {
    getHello() {
      var that = this;
      this.$http({
        method: "get",
        url: that.GLOBAL.adress + "/config/blog-hello"
      }).then(function(res) {
        that.hello = res.data.data;
      });
    },
    getClick() {
      var that = this;
      this.$http({
        method: "get",
        url: that.GLOBAL.adress + "/articles/click"
      }).then(function(res) {
        if (res.data.success) {
          that.clickArticles = res.data.data;
        }
      });
    },
    showText(str) {
      return this.GLOBAL.showText(str);
    }
  }
};
</script>

<style>
.home-box {
  margin: 0 auto;
  text-align: center;
  width: 1050px;
}
.hello-text {
  font-family: Helvetica, "Hiragino Sans GB", "STXingkai", "华文行楷", Arial,
    sans-serif;
  font-size: 34px;
}
.time {
  margin: 0;
  padding: 0;
  border: 0;
  font-size: 100%;
  font: inherit;
  vertical-align: baseline;
  color: #aab2bd;
}
.article-list {
  text-align: left;
  margin: 125px;
}
.tuijian {
  margin-left: 125px;
  margin-right: 125px;
}
</style>
