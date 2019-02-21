<template>
  <div id="editor">
    <br>
    <div class="right">
      <Card title="发布" :padding="10" shadow style="width: 300px;">
        <br>
        <Form :label-width="80">
          <FormItem label="发布时间">
            <DatePicker
              type="datetime"
              @on-change="time=$event"
              v-model="time"
              format="yyyy-MM-dd HH:mm:ss"
            ></DatePicker>
          </FormItem>
          <FormItem label="分类目录" v-model="group">
            <Select style="width:203.19px;" :value='group'>
              <Option v-for="group in groups" :key="group.id" :value="group.id">{{group.groupName}}</Option>
            </Select>
          </FormItem>
          <FormItem label="是否公开">
            <RadioGroup v-model="show">
              <Radio label="true"></Radio>&nbsp;&nbsp;&nbsp;&nbsp;
              <Radio label="false"></Radio>
            </RadioGroup>
          </FormItem>
          <FormItem class="btn">
            <Button style="margin:5px;" type="success" @click="submit()">更新</Button>
          </FormItem>
        </Form>
      </Card>
    </div>
    <div style="height:100%">
      <h1 style="margin:20px;">修改文章：{{title}}</h1>
      <Input style="margin:20px; width:80%" placeholder="在此输入标题" v-model="title" size="large"></Input>
      <mavon-editor
        style="height: 80%;width:80%;margin:20px;"
        code-style="vs2015"
        :ishljs="true"
        :scrollStyle="true"
        v-model="markdown"
        @change="editText"
      ></mavon-editor>
    </div>
  </div>
</template>
<script>
import { mavonEditor } from "mavon-editor";
import "mavon-editor/dist/css/index.css";
import { formatDate } from "../js/formatDate.js";
export default {
  name: "editor",
  components: {
    mavonEditor
  },
  filters: {
    formatDate(time) {
      var date = new Date(time);
      return formatDate(date, "yyyy-MM-dd");
    }
  },
  data() {
    return {
      articleTemp: {
        id: 0
      },
      title: null,
      date: null,
      time: null,
      groups: [
        {
          id: 0
        }
      ],
      show: "true",
      texts: null,
      markdown: "",
      group: null
    };
  },
  mounted: function() {
    this.getArticleRoot(this.$route.params.id);
    this.getArticle(this.$route.params.id);
    this.init();
  },
  methods: {
    getArticleRoot(aId) {
      var that = this;
      this.$http({
        mounted: "get",
        url: that.GLOBAL.adress + "/admin/article/" + aId,
        headers: {
          token: this.$store.state.token
        }
      }).then(function(res) {
        that.markdown = res.data.data.markdown.toString();
      });
    },
    getArticle(aId) {
      var that = this;
      this.$http({
        mounted: "get",
        url: that.GLOBAL.adress + "/article/" + aId
      }).then(function(res) {
        that.articleTemp = res.data.data;
        that.title = res.data.data.title;
        that.time = res.data.data.firstTime;
        that.show = String(res.data.data.show);
        that.group=res.data.data.articleGroup
      });
    },
    init() {
        var that=this
      this.getGroups();
      this.$Spin.show();
      setTimeout(() => {
        this.$Spin.hide();
      }, 1000);
    },
    getGroups() {
      var that = this;
      this.$http({
        method: "get",
        url: that.GLOBAL.adress + "/groups"
      }).then(function(res) {
        that.groups = res.data.data;
      });
    },
    editText(value, render) {
      this.texts = render;
      this.markdown = value;
    },
    submit() {
      var that = this;
      if (
        this.GLOBAL.isEmpty(this.texts) ||
        this.GLOBAL.isEmpty(this.title) ||
        this.GLOBAL.isEmpty(this.show) ||
        this.GLOBAL.isEmpty(this.time)
      ) {
        this.$Notice.error({
          title: "存在未填写内容，请重新检查"
        });
        return;
      }
      var parms = new URLSearchParams();
      parms.append("title", this.title);
      parms.append("texts", this.texts);
      parms.append(
        "firstTime",
        formatDate(new Date(this.time), "yyyy-MM-dd hh:mm:ss")
      );
      parms.append("articleGroupe", this.group);
      parms.append("show", this.show);
      parms.append("markdown", this.markdown);
      parms.append("articleGroup", this.group);
      parms.append("id", this.articleTemp.id);
      this.$http({
        method: "PUT",
        data: parms,
        url: that.GLOBAL.adress + "/admin/article",
        headers: {
          token: this.$store.state.token
        }
      }).then(function(res) {
        that.$Notice.success({
          title: "更新文章成功",
          desc: res.data.msg
        });
        setTimeout(function() {
          that.$router.push({ path: "/article/" + that.articleTemp.id });
        }, 1000);
      });
    },
    changeDate(value, dateFormit) {
      this.date = value;
    },
    changeTime(value, dateFormit) {
      this.time = value;
    },
    getGroup(groupId) {
      for (var group in this.groups) {
        if (this.groups[group].id == groupId) {
          return this.groups[group].groupName;
        }
      }
      return "无";
    }
  }
};
</script>
<style>
#editor {
  height: 37vw;
  text-align: left;
}
.right {
  float: right;
}
.btn {
  text-align: end;
}
</style>