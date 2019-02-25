// 留言
<template>
  <Card class="">
    <!-- 标题 -->
    <h2>{{BlogName}}的留言板</h2>
    <!-- 主人寄语 -->
    <Divider orientation="left">主人寄语</Divider>
    <div class="text">{{BlogMessage}}</div>
    <Divider orientation="left">添加留言</Divider>
    <!-- 添加留言 -->
    <div>
      <Input
        type="textarea"
        placeholder="写下你的留言"
        :autosize="{minRows: 5,maxRows: 5}"
        class="textarea"
        v-model="message.texts"
      />
      <div class="sub">
        <Input placeholder="昵称" style="width: auto" v-model="message.username">
          <Icon type="ios-contact" slot="prefix"/>
        </Input>&nbsp;
        &nbsp;
        <Input
          placeholder="邮箱"
          style="width: auto"
          v-model="message.mail"
          onkeyup="this.value=this.value.replace(,'')"
        >
          <Icon type="ios-mail" slot="prefix"/>
        </Input>&nbsp;
        &nbsp;
        <Button type="success" class="submit-message" @click="addMessage">留言</Button>
      </div>
    </div>
    <Divider orientation="left"></Divider>
    <!-- 留言展示 -->
    <div>
      <Card
        v-for="(content) in contentList"
        :key="content.id"
        style="margin-top: 20px;margin-bottom: 20px;"
      >
        <div style="display:inline;">
          <div style="float: left;display:inline;">
            <strong>{{content.username}}</strong>
          </div>
          <div style="float: right;display:inline;">
            <span>{{content.time}}</span>
          </div>
          <br>
          <div style="float: left;display:inline;">
            <a>{{content.mail}}</a>
          </div>
        </div>
        <Divider orientation="left"></Divider>
        <div class="text">
          <p>{{content.texts}}</p>
        </div>
      </Card>
      <Page :total="sum" show-total @on-change="nextpage"/>
    </div>
  </Card>
</template>

<script>
export default {
  data() {
    return {
      BlogName: null,
      BlogMessage: null,
      contentList: null,
      message: {
        username: null,
        mail: null,
        texts: null
      },
      page: 1,
      perpage: 10,
      sum: 0
    };
  },
  mounted: function() {
    this.getBlogMessage();
    this.getMessages(this.page, this.perpage);
    this.getSum();
    this.getBlogName();
    this.$Spin.show();
    setTimeout(() => {
      this.$Spin.hide();
    }, 1000);
  },
  methods: {
    getBlogMessage() {
      var that = this;
      this.$http({
        method: "get",
        url: that.GLOBAL.adress + "/config/blog-message"
      }).then(function(res) {
        that.BlogMessage = res.data.data;
      });
    },
    getBlogName() {
      var that = this;
      this.$http({
        method: "get",
        url: that.GLOBAL.adress + "/config/blog-name"
      }).then(function(res) {
        that.BlogName = res.data.data;
      });
    },
    getMessages(a, b) {
      var that = this;
      this.$http({
        method: "get",
        url: that.GLOBAL.adress + "/messages" + "?page=" + a + "&perpage=" + b
      }).then(function(res) {
        that.contentList = res.data.data;
      });
    },
    addMessage() {
      var that = this;
      var parms = new URLSearchParams();
      var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
      if (
        this.isEmpty(this.message.username) ||
        this.isEmpty(this.message.mail) ||
        this.isEmpty(this.message.texts || !reg.test(this.message.mail))
      ) {
        this.$Message.error("输入有误，请重新输入");
        return;
      }
      if (!reg.test(this.message.mail)) {
        this.$Message.error("邮箱格式错误，请重新输入");
        return;
      }
      parms.append("username", this.message.username);
      parms.append("mail", this.message.mail);
      parms.append("texts", this.message.texts);
      this.$http({
        method: "POST",
        data: parms,
        url: that.GLOBAL.adress + "/message"
      }).then(function(res) {
        that.$Spin.show();
        setTimeout(() => {
          that.$Spin.hide();
        }, 1000);
        var data = res.data;
        if (data.success == true) {
          that.$Notice.success({
            title: "留言成功"
          });
        }
        that.message.username = "";
        that.message.mail = "";
        that.message.texts = "";
        that.getMessages(that.page, that.perpage);
        that.getSum();
      });
    },
    nextpage(page) {
      this.getMessages(page, this.perpage);
    },
    getSum() {
      var that = this;
      this.$http({
        method: "get",
        url: that.GLOBAL.adress + "/message/sum/number"
      }).then(function(res) {
        that.sum = res.data.data;
      });
    },
    isEmpty(obj) {
      if (typeof obj == "undefined" || obj == null || obj == "") {
        return true;
      } else {
        return false;
      }
    }
  }
};
</script>

<style>
.mmessage-card {
  text-align: left;
  margin-left: 20px;

  margin: 0 auto;
  text-align: center;
}
.text {
  text-align: left;
  margin: 25px;
}
.submit-message {
  float: right;
  margin-right: 20px;
}
.sub {
  margin-top: 20px;
  text-align: left;
}
</style>