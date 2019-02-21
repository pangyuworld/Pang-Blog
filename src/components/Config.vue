<template>
  <div class="config-box">
    <br>
    <h2>基础配置</h2>
    <br>
    <Form :label-width="100">
      <FormItem v-for="config in configList" :key="config.blogKey" :label="config.blogNote">
        <Input v-model="config.blogValue"></Input>
      </FormItem>
      <FormItem>
        <Button type="primary" @click="submit()">确定修改</Button>
      </FormItem>
    </Form>
  </div>
</template>
<script>
export default {
  data() {
    return {
      configList: null
    };
  },
  mounted: function() {
    this.getConfig();
  },
  methods: {
    getConfig() {
      var that = this;
      this.$http({
        method: "get",
        url: that.GLOBAL.adress + "/admin/config",
        headers: {
          token: this.$store.state.token
        }
      })
        .then(function(res) {
          that.configList = res.data.data;
        })
        .catch(function(res) {
          that.$router.push({ path: "/login" });
        });
    },
    submit() {
      var that = this;
      var parms = new URLSearchParams();
      parms.append("str", JSON.stringify(this.configList));
      this.$http({
        method: "PUT",
        data: parms,
        url: that.GLOBAL.adress + "/admin/config",
        headers: {
          token: this.$store.state.token
        }
      }).then(function(res) {
      that.$Spin.show();
      setTimeout(() => {
        that.$Spin.hide();
      }, 1000);
        window.history.go(0)
      });
    }
  }
};
</script>
<style>
.config-box {
  text-align: center;
  margin: 0 auto;
  width: 600px;
}
</style>
