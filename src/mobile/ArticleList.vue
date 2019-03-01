<template>
  <div>
    <div>
      <van-collapse v-model="activeNames" style="text-align:left">
        <van-collapse-item title="文章分组" name="1">
            <a @click="selectGroup('all')">全部<br/></a>
            <a v-for="group in groups" :key="group.id" @click="selectGroup(group.id)" :if="group.show">{{group.groupName}}<br/></a>
        </van-collapse-item>
      </van-collapse>
    </div>
    <div class="mlist" v-for="article in articles" :key="article.id" @v-if="article.show">
      <van-panel style="padding:4%;">
        <router-link :to="'/sj/article/'+article.id">
          <h3 class="van-ellipsis">{{article.title }}</h3>
        </router-link>
        <br>
        <div class="article-body">
          <div class="jianjie">{{deleteHtmlTag(article.texts)}}</div>
        </div>
        <time class="time">
          <Icon type="ios-time-outline"/>
          {{article.firstTime}}&nbsp;&nbsp;发布
        </time>
      </van-panel>
    </div>
    <br />
    <div class="van-hairline--surround">
      <van-pagination
        v-if="showPage"
        v-model="page"
        :total-items="sum"
        :items-per-page="perpage"
        @change="nextpage"
      />
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      groups: null,
      articles: null,
      page: 1,
      perpage: 10,
      sum: 0,
      showPage: true,
      activeNames: []
    };
  },
  mounted: function() {
    this.getSum();
    this.getArticles(this.page, this.perpage);
    this.getGroups();
  },
  methods: {
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
        this.showPage = true;
      } else {
        this.showPage = false;
        var that = this;
        this.$http({
          method: "get",
          url: that.GLOBAL.adress + "/articles/group/" + groupId
        }).then(function(res) {
          that.articles = res.data.data;
        });
      }
      activeNames=[]
    },
    showText(str) {
      return this.GLOBAL.showText(str);
    }
  }
};
</script>
<style>
.article-body {
  word-break: break-all;
}
.mlist {
  text-align: left;
}
</style>
