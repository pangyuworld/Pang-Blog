<template>
  <div>
    <br>
    <h2>分组管理</h2>
    <br>
    <Divider></Divider>
    <Row>
      <Col span="4">
        <h2>&nbsp;</h2>
      </Col>
      <Col span="4">
        <h2>分&nbsp;组&nbsp;名</h2>
      </Col>
      <Col span="4">
        <h2>文&nbsp;章&nbsp;数</h2>
      </Col>
      <Col span="4">
        <h2>是&nbsp;否&nbsp;显&nbsp;示</h2>
      </Col>
      <Col span="4">
        <h2>拖&nbsp;拽&nbsp;排&nbsp;序</h2>
      </Col>
    </Row>
    <Divider></Divider>
    <Row
      class="item"
      v-for="group in groups"
      v-dragging="{ item: group, list: groups, group: 'group' }"
      :key="group.id"
      type="flex"
      align="middle"
    >
      <Col span="4">
        <h2>&nbsp;</h2>
      </Col>
      <Col span="4">
        <Input
          type="text"
          v-model="group.groupName"
          size="small"
          placeholder="输入分组名称"
          style="text-align:center;width:50%;"
        ></Input>
      </Col>
      <Col span="4">
        <nummber :aId="group.id"></nummber>
      </Col>
      <Col span="4">
        <i-switch v-model="group.groupShow"></i-switch>
      </Col>
      <Col span="4">
        <Button type="info">
          <Icon type="md-move"/>
        </Button>
      </Col>
    </Row>
    <Divider></Divider>
    <Button type="success" @click="change">确定更改</Button>
  </div>
</template>
<script>
import Nummber from "./GroupNum";

export default {
  data() {
    return {
      groups: []
    };
  },
  mounted() {
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
    change(){
        // 更改顺序
        var i=1;
        for(var a in this.groups){
            this.groups[a].groupOrder=i++;
        }

        var that = this;
      var parms = new URLSearchParams();
      parms.append("str", JSON.stringify(this.groups));
      this.$http({
        method: "PUT",
        data: parms,
        url: that.GLOBAL.adress + "/admin/group",
        headers: {
          token: this.$store.state.token
        }
      }).then(function(res) {
      that.$Spin.show();
      setTimeout(() => {
        that.$Spin.hide();
      }, 1000);
        that.getGroups();
      });
    }
  },
  components: {
    Nummber
  }
};
</script>
<style>
.item {
  margin-top: 20px;
  margin-bottom: 20px;
}
</style>
