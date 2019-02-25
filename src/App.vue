<template>
  <div>
    <router-view></router-view>
  </div>
</template>

<script>
import Index from "@/views/Index.vue";
export default {
  components: {
    Index
  },
  beforeCreate() {
    //检验是否是手机登陆
    if (
      navigator.userAgent.match(
        /(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i
      )
    ) {
      //如果是手机登陆，则跳转到/sj的界面
      this.$router.push("/sj");
    }else{
      
      this.$router.push("/");
    }
  }
};
</script>


<style>
</style>
