<template>
  <div>
    <br>
    <h2>{{hello}}</h2>
    <Divider orientation="left">推荐文章</Divider>
    <div
      v-for="article in clickArticles"
      :key="article.id"
      class="articleList van-hairline--surround"
    >
      <router-link :to="'/sj/article/'+article.id">
        <h3 class="van-ellipsis">{{article.title }}</h3>
      </router-link>
      <van-row type="flex" justify="space-between">
        <van-col>
          <time class="time">
            <Icon type="ios-time-outline"/>
            {{article.firstTime}}&nbsp;&nbsp;发布
          </time>
        </van-col>
        <van-col style="color: #aab2bd;">
            {{article.clickNum}}人阅读&nbsp;
        </van-col>
      </van-row>
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
.articleList {
  text-align: left;
  margin: 10px;
}
</style>
