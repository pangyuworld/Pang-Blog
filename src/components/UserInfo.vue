<template>
  <div class="config-box">
    <br>
    <h2>更改用户名、密码</h2>
    <br>
    <Form :label-width="100" ref="ruleInline">
      <FormItem label="新用户名">
        <Input v-model="username"></Input>
      </FormItem>
      <FormItem label="新密码">
        <Input type="password" v-model="password"></Input>
      </FormItem>
      <FormItem label="确认密码">
        <Input type="password" v-model="password1"></Input>
      </FormItem>
      <FormItem>
        <Button type="primary" @click="isReady()">修改</Button>
      </FormItem>
    </Form>
    <Modal v-model="ready" title="确认修改用户名、密码？" @on-ok="submit()" @on-cancel="cancel()">
      <p>一旦修改密码，您将重新登陆后台</p>
      <p>是否继续？</p>
    </Modal>
  </div>
</template>
<script>
export default {
  data() {
    return {
      username: this.$store.state.username,
      password: null,
      password1: null,
      ready: false,
      ruleInline: {
        password: {
          type: "string",
          min: 6,
          message: "密码长度不得低于6位",
          trigger: "blur"
        }
      }
    };
  },
  methods: {
    submit() {
      var that = this;
      var parms = new URLSearchParams();

      parms.append("username", this.username);
      parms.append("password", this.password);
      parms.append("oldUsername", this.$store.state.username);
      this.$http({
        method: "PUT",
        data: parms,
        url: that.GLOBAL.adress + "/admin/update",
        headers: {
          token: this.$store.state.token
        }
      }).then(function(res) {
        that.configList = res.data.data;
        that.$router.push({ path: "/login" });
      });
    },
    cancel() {},
    isReady() {
      if (
        this.GLOBAL.isEmpty(this.username) ||
        this.GLOBAL.isEmpty(this.password)
      ) {
        this.$Notice.error({
          title: "账号或密码为空，请重新输入"
        });
        return;
      }
      if (this.password.length < 6) {
        this.$Notice.error({
          title: "密码长度过短，请重新输入"
        });
        return;
      }
      if (this.password != this.password1) {
        this.$Notice.error({
          title: "密码输入不一致，请重新输入"
        });
        return;
      }
      this.ready = true;
    }
  }
};
</script>
<style>
.config-box {
  text-align: center;
  margin: 0 auto;
  width: 400px;
}
</style>
