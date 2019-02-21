<template>
  <div>{{comNum}}</div>
</template>
<script>
export default {
  name: "com",
  props: ["aId"],
  data() {
    return {
      comNum: 0,
    };
  },
  mounted:function() {
    this.getCom(this.aId);
  },
  methods: {
    getCom(id) {
      var that = this;
      this.$http({
        method: "get",
        url: that.GLOBAL.adress + "/article/common/" + id
      }).then(function(res) {
        that.comNum = res.data.data;
      });
    }
  }
};
</script>
