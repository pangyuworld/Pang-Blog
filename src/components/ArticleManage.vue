<template>
  <div>
    <br>
    <h2>文章管理</h2>
    <br>
    <Divider></Divider>
    <Row>
      <Col span="6">
        <h2>标&nbsp;题</h2>
      </Col>
      <Col span="4">
        <h2>分&nbsp;组</h2>
      </Col>
      <Col span="3">
        <h2>是&nbsp;否&nbsp;显&nbsp;示</h2>
      </Col>
      <Col span="2">
        <h2>评&nbsp;论</h2>
      </Col>
      <Col span="2">
        <h2>阅&nbsp;读</h2>
      </Col>
      <Col span="3">
        <h2>发&nbsp;布</h2>
      </Col>
      <Col span="3">
        <h2>删&nbsp;除&nbsp;文&nbsp;章</h2>
      </Col>
    </Row>
    <Divider></Divider>
    <Row
      v-for="article in articles"
      :key="article.id"
      class="row"
      style="margin-top: 30px;margin-bottom: 30px;"
    >
      <Col span="6">
        <router-link :to="'/admin/article/'+article.id">{{showText(article.title)}}</router-link>
      </Col>
      <Col span="4">{{getGroup(article.articleGroup)}}</Col>
      <Col span="3">
        <i-switch v-model="article.show" @on-change="changeShow(article.id,article.show)"></i-switch>
      </Col>
      <Col span="2">
        <common :aId="article.id"></common>
      </Col>
      <Col span="2">{{article.clickNum}}</Col>
      <Col span="3">
        <span >{{article.firstTime}}</span>
      </Col>
      <Col span="3"><Button type="error" @click="deleteArticle(article.id)"><Icon type="md-trash" /></Button></Col>
      <br>
    </Row>
    <br>
    <Page :total="sum" show-total @on-change="nextpage"/>
    <br>


    <Modal
        v-model="deleteView"
        title="删除文章"
        @on-ok="ok"
        @on-cancel="deleteView=false">
        <p>删除文章后无法恢复，是否继续？</p>
    </Modal>
  </div>
</template>
<script>
import Common from "./CommonNum";
export default {
  data() {
    return {
      articles: null,
      groups: null,
      page: 1,
      perpage: 10,
      sum: 0,
      deleteView:false,
      deleteId:null
    };
  },
  mounted: function() {
    this.getSum();
    this.getGroups();
    this.getArticles(this.page, this.perpage);
    this.$Spin.show();
    setTimeout(() => {
      this.$Spin.hide();
    }, 1000);
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
    getGroup(groupId) {
      for (var group in this.groups) {
        if (this.groups[group].id == groupId) {
          return this.groups[group].groupName;
        }
      }
      return "无";
    },
    showText(str) {
      return this.GLOBAL.showText(str);
    },
    changeShow(id, show) {
      // return
      this.$Spin.show();
      setTimeout(() => {
        this.$Spin.hide();
      }, 1000);
      var that = this;
      var parms = new URLSearchParams();
      parms.append("id", id);
      parms.append("show", show);
      this.$http(
        {
          method: "PUT",
          url: that.GLOBAL.adress + "/admin/article/show",
          data: parms,
          headers: {
            token: this.$store.state.token
          }
        },
        function(res) {
          this.getArticles(this.page, this.perpage);
        }
      );
    },
    getSum() {
      var that = this;
      this.$http({
        method: "get",
        url: that.GLOBAL.adress + "/admin/article/sum/number",
        headers: {
          token: this.$store.state.token
        }
      }).then(function(res) {
        that.sum = res.data.data;
      });
    },
    nextpage(page) {
      this.getArticles(page, this.perpage);
    },
    ok(){
      var that = this;
      this.$http({
        method: "delete",
        url: that.GLOBAL.adress + "/admin/article/"+that.deleteId,
        headers: {
          token: this.$store.state.token
        }
      }).then(function(res) {
         that.$Notice.success({
          title: "删除文章成功",
          desc: res.data.data
        });
          that.getArticles(that.page,that.perpage)
      });
    },
    deleteArticle(id){
      this.deleteId=id
      this.deleteView=true;
    }
  },
  components: {
    Common
  }
};
</script>
<style>
.row {
}
</style>
