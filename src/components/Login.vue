<template>
  <div class="box">
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <h1>登陆后台</h1>
    <br>
    <Form ref="formInline" :model="formInline" :rules="ruleInline">
      <FormItem prop="user">
        <Input type="text" v-model="formInline.user" placeholder="Username">
          <Icon type="ios-person-outline" slot="prepend"></Icon>
        </Input>
      </FormItem>
      <FormItem prop="password">
        <Input type="password" v-model="formInline.password" placeholder="Password">
          <Icon type="ios-lock-outline" slot="prepend"></Icon>
        </Input>
      </FormItem>
      <FormItem>
        <Button type="primary" @click="handleSubmit()">登陆</Button>
      </FormItem>
    </Form>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
  </div>
</template>
<script>
export default {
  data() {
    return {
      formInline: {
        user: "",
        password: ""
      },
      ruleInline: {
        user: [
          {
            required: true,
            message: "请填写用户名",
            trigger: "blur"
          }
        ],
        password: [
          {
            required: true,
            message: "请填写密码",
            trigger: "blur"
          },
        ]
      }
    };
  },
  methods: {
    handleSubmit() {
      var that = this;
      var parms = new URLSearchParams();
      parms.append("username", this.formInline.user);
      parms.append("password", this.formInline.password);
      this.$http({
        method: "POST",
        data: parms,
        url: that.GLOBAL.adress + "/login"
      }).then(function(res) {
        if (res.data.success) {
          that.$store.commit("getToken", res.data.data);
          that.$router.push({ path: "/admin/config" });
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
.box {
  width: 300px;
  text-align: center;
  margin: 0 auto;
}
</style>
