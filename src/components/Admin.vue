<template>
  <Layout class="layout-box">
    <Sider
      style="background:#fff;"
      hide-trigger
      collapsible
      :collapsed-width="78"
      v-model="isCollapsed"
    >
      <Menu theme="light" active-name="config" width="auto" :class="menuitemClasses">
        <MenuItem to="/admin/config" name='config'>
          <Icon type="ios-glasses-outline"/>
          <span>基础配置</span>
        </MenuItem>
        <MenuItem name="admin" to='/admin/user'>
          <Icon type="md-contact" />
          <span>账户设置</span>
        </MenuItem>
        <MenuItem to="/admin/article" name='article'>
          <Icon type="md-add"/>
          <span>新建文章</span>
        </MenuItem>
        <MenuItem to="/admin/articlemanage" name="articleManage">
          <Icon type="ios-home"/>
          <span>文章管理</span>
        </MenuItem>
        <MenuItem to="/admin/group" name="groupManage">
          <Icon type="ios-home"/>
          <span>分组管理</span>
        </MenuItem>
        <!-- <MenuItem name="1-4">
          <Icon type="ios-list-box"/>
          <span>留言管理</span>
        </MenuItem> -->
      </Menu>
    </Sider>
    <Content :style="{ background: '#fff'}">
      <router-view></router-view>
    </Content>
  </Layout>
</template>
<script>
export default {
  data() {
    return {
      configList: null,
      isCollapsed: false
    };
  },
  beforeMount: function() {
    this.init();
  },
  methods: {
    init() {
      if (this.GLOBAL.isEmpty(this.$store.state.token)) {
        this.$router.push({ path: "/login" });
      }
    },
    menuitemClasses: function() {
      return ["menu-item", this.isCollapsed ? "collapsed-menu" : ""];
    }
  }
};
</script>
<style>
.layout-con {
  height: 100%;
  width: 100%;
}
.menu-item span {
  display: inline-block;
  overflow: hidden;
  width: 69px;
  text-overflow: ellipsis;
  white-space: nowrap;
  vertical-align: bottom;
  transition: width 0.2s ease 0.2s;
}
.menu-item i {
  transform: translateX(0px);
  transition: font-size 0.2s ease, transform 0.2s ease;
  vertical-align: middle;
  font-size: 16px;
}
.collapsed-menu span {
  width: 0px;
  transition: width 0.2s ease;
}
.collapsed-menu i {
  transform: translateX(5px);
  transition: font-size 0.2s ease 0.2s, transform 0.2s ease 0.2s;
  vertical-align: middle;
  font-size: 22px;
}
.layout-box {
  min-height: 41vw;
}
</style>
