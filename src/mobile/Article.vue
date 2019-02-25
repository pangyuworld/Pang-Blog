// 文章详情
<template>
  <Card class="marticle">
    <!-- 标题 -->
    <h2>{{article.title}}</h2>
    <div>
      <div style="float:left">
        <time class="time">
          <Icon type="ios-time-outline"/>
          {{article.firstTime}}&nbsp;发布
        </time>
      </div>
      <div style="float:right">
        <time class="time">{{article.clickNum}}&nbsp;人阅读</time>
      </div>
    </div>
    <Divider orientation="left"></Divider>
    <div class="">
      <div v-html="article.texts"></div>
    </div>
    <Divider orientation="left"></Divider>
    <!-- 添加评论 -->
    <div class="comment">
      <!-- 评论列表 -->
      <div style="text-align:left;" v-for="aComment in commentList" :key="aComment.id">
        <div style="float:left">
          <strong>{{aComment.username}}</strong>
          <span class="time">&nbsp;说道：</span>
        </div>
        <div style="float:right">
          <a @click="addCommentText(aComment.username)">回复</a>&nbsp;
          <a @click="deleteComment(aComment.id)" style="color:red">删除</a>
        </div>
        <br>
        <div>
          <time class="time">{{aComment.time}}</time>
        </div>
        <div style="margin:10px;">{{aComment.commentTexts}}</div>
        <br>
      </div>
      <div style="text-align:left;margin-left:20px;">
        <h3>发表评论</h3>
      </div>
      <Input
        type="textarea"
        placeholder="写下你的评论"
        :autosize="{minRows: 5,maxRows: 5}"
        class="textarea"
        v-model="comment.texts"
      />
      <div class="sub">
        <Input placeholder="昵称" style="width: auto" v-model="comment.username">
          <Icon type="ios-contact" slot="prefix"/>
        </Input>&nbsp;
        &nbsp;
        <Input
          placeholder="邮箱"
          style="width: auto"
          v-model="comment.mail"
          onkeyup="this.value=this.value.replace(,'')"
        >
          <Icon type="ios-mail" slot="prefix"/>
        </Input>&nbsp;
        &nbsp;
        <Button type="success" class="submit-message" @click="addComment()">评论</Button>
      </div>
    </div>

    <Modal v-model="deleteView" width="300">
      <p slot="header" style="text-align:center">
        <Icon type="ios-information-circle"></Icon>
        <span>&nbsp;管&nbsp;理&nbsp;员&nbsp;登&nbsp;陆&nbsp;</span>
      </p>
      <div style="text-align:center">
        <!-- <Alert type="warning">您的操作较为敏感</Alert>
        <Alert type="warning">需要验证管理员身份</Alert>-->
        <br>
        <Form :model="admin">
          <FormItem prop="user">
            <Input type="text" v-model="admin.username" placeholder="Username">
              <Icon type="ios-person-outline" slot="prepend"></Icon>
            </Input>
          </FormItem>
          <FormItem prop="password">
            <Input type="password" v-model="admin.password" placeholder="Password">
              <Icon type="ios-lock-outline" slot="prepend"></Icon>
            </Input>
          </FormItem>
        </Form>
      </div>
      <div slot="footer">
        <Button type="primary" size="large" @click="login" long>登陆</Button>
      </div>
    </Modal>
  </Card>
</template>
<script>
import 'highlight.js/styles/googlecode.css'
import hljs from 'highlight.js'


hljs.highlightCode = function () { //自定义highlightCode方法，将只执行一次的逻辑去掉
  let blocks = document.querySelectorAll('pre code');
    [].forEach.call(blocks, hljs.highlightBlock);
};
export default {
  data() {
    return {
      article: {
        articleGroup: 0,
        clickNum: 0,
        firstTime: "2",
        id: 0,
        lastTime: "",
        texts: "",
        title: ""
      },
      comment: {
        username: null,
        mail: null,
        texts: null,
        articleId: null
      },
      commentList: null,
      deleteView: false,
      admin: {
        username: null,
        password: null
      }
    };
  },
  mounted: function() {
    hljs.highlightCode();
    this.$Spin.show();
    setTimeout(() => {
      this.$Spin.hide();
    }, 1000);
    this.getArticle(this.$route.params.id);
    this.getComment(this.$route.params.id);
  },
  methods: {
    getArticle(aId) {
      var that = this;
      this.$http({
        mounted: "get",
        url: that.GLOBAL.adress + "/article/" + aId
      }).then(function(res) {
        if (res.data.success == true) {
          that.article = res.data.data;
        } else {
          that.$router.push({ path: "/404" });
        }
      });
    },
    addComment() {
      var that = this;
      var parms = new URLSearchParams();
      var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
      if (
        this.isEmpty(this.comment.username) ||
        this.isEmpty(this.comment.mail) ||
        this.isEmpty(this.comment.texts || !reg.test(this.comment.mail))
      ) {
        this.$Message.error("输入有误，请重新输入");
        return;
      }
      if (!reg.test(this.comment.mail)) {
        this.$Message.error("邮箱格式错误，请重新输入");
        return;
      }
      that.$Spin.show();
      setTimeout(() => {
        that.$Spin.hide();
      }, 1000);
      parms.append("username", this.comment.username);
      parms.append("mail", this.comment.mail);
      parms.append("commentTexts", this.comment.texts);
      parms.append("articleId", this.$route.params.id);
      this.$http({
        method: "POST",
        data: parms,
        url: that.GLOBAL.adress + "/comment"
      }).then(function(res) {
        var data = res.data;
        if (data.success == true) {
          that.$Notice.success({
            title: "评论成功"
          });
        }
        that.comment.username = "";
        that.comment.mail = "";
        that.comment.texts = "";
        that.getComment(that.$route.params.id);
      });
    },
    getComment(aId) {
      var that = this;
      this.$http({
        mounted: "get",
        url: that.GLOBAL.adress + "/comment/" + aId
      }).then(function(res) {
        if (res.data.success == true) {
          that.commentList = res.data.data;
        } else {
          that.$router.push({ path: "/404" });
        }
      });
    },
    addCommentText(parm) {
      this.comment.texts = "回复 @" + parm + " ：";
    },
    isEmpty(obj) {
      if (typeof obj == "undefined" || obj == null || obj == "") {
        return true;
      } else {
        return false;
      }
    },
    deleteComment(id) {
      if (this.GLOBAL.isEmpty(this.$store.state.token)) {
        this.deleteView = true;
        return;
      }
      var that = this;
      this.$http({
        method: "delete",
        url: that.GLOBAL.adress + "/admin/comment/" + id,
        headers: {
          token: this.$store.state.token
        }
      }).then(function(res) {
        that.$Notice.success({
          title: "删除评论成功"
        });
        that.getComment(that.$route.params.id);
      });
    },
    login() {
      var that = this;
      var parms = new URLSearchParams();
      parms.append("username", this.admin.username);
      parms.append("password", this.admin.password);
      this.$http({
        method: "POST",
        data: parms,
        url: that.GLOBAL.adress + "/login"
      }).then(function(res) {
        if (res.data.success) {
          that.$store.commit("getToken", res.data.data);
          that.$Notice.success({
            title: res.data.msg
          });
          that.deleteView = false;
        } else {
          that.$Notice.error({
            title: res.data.msg
          });
        }
      });
    }
  }
};
</script>
<style>
.marticle{
    text-align: left;
}
</style>
