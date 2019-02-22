<template>
  <div class="config-box">
    <br>
    <h2>基础配置</h2>
    <br>
    <Form :label-width="100">
      <FormItem v-for="config in configList" :key="config.blogKey" :label="config.blogNote">
        <Input v-model="config.blogValue" v-if="config.blogKey!='blog-icon'"></Input>
        <Upload
          :action="uploadUrl"
          accept="image/*"
          :format="['jpg','jpeg','png']"
          :headers="uploadHeader"
          :on-success="handleSuccess"
          :show-upload-list="false"
           style="text-align: left;"
          v-else
        >
          <Row type="flex" justify="center" align="top">
            <Button icon="ios-cloud-upload-outline">上传图片</Button>&nbsp;&nbsp;
            <img :src="icon" style="height:50px;">
          </Row>
        </Upload>
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
      configList: null,
      uploadUrl: this.GLOBAL.adress + "/admin/config/upload",
      uploadHeader: {
        token: this.$store.state.token
      },
      icon: null,
      iconUrl:null
    };
  },
  mounted: function() {
    this.getConfig();
    this.getIcon();
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
      for(var a in this.configList){
        if(this.configList[a].blogKey=="blog-icon"){
          this.configList[a].blogValue=this.iconUrl;
          break;
        }
      }
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
        window.history.go(0);
      });
    },
    getIcon(){
      var that=this;
      this.$http({
        method:"get",
        url: that.GLOBAL.adress + "/config/blog-icon"
      }).then(function(res){
        that.iconUrl=res.data.data;
        that.icon=that.GLOBAL.adress+res.data.data
      })
    },
    handleSuccess (res, file){
      this.icon=this.GLOBAL.adress+res.data.path;
      this.iconUrl=res.data.path
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
