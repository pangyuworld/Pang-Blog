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
          <FormItem label="分类目录">
            <Select style="width:203.19px;" v-model="group">
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
            <Button style="margin:5px;" type="success" @click="submit()">发布</Button>
          </FormItem>
        </Form>
      </Card>
    </div>
    <div style="height:100%">
      <h1 style="margin:20px;">撰写新文章</h1>
      <Input style="margin:20px; width:80%" placeholder="在此输入标题" v-model="title" size="large"></Input>
      <mavon-editor
      ref=md
        style="height: 80%;width:80%;margin:20px;"
        code-style="vs2015"
        :ishljs="true"
        :scrollStyle="true"
        @change="editText"
        @imgAdd="$imgAdd"
      ></mavon-editor>
    </div>
  </div>
</template>
<script>
import { formatDate } from "../js/formatDate.js";
export default {
  name: "editor",
  components: {
    
  },
  filters: {
    formatDate(time) {
      var date = new Date(time);
      return formatDate(date, "yyyy-MM-dd");
    }
  },
  data() {
    return {
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
      group: null,
      markdown: null
    };
  },
  mounted: function() {
    this.init();
    this.$Spin.show();
    setTimeout(() => {
      this.$Spin.hide();
    }, 1000);
  },
  methods: {
    init() {
      this.time = new Date();
      this.getGroups();
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
      that.$Spin.show();
      setTimeout(() => {
        that.$Spin.hide();
      }, 1000);
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
      this.$http({
        method: "POST",
        data: parms,
        url: that.GLOBAL.adress + "/admin/article",
        headers: {
          token: this.$store.state.token
        }
      }).then(function(res) {
        that.$Notice.success({
          title: "添加文章成功",
          desc: res.data.msg
        });
        setTimeout(function() {
          that.$router.push({ path: "/article/" + res.data.data });
        }, 1000);
      });
    },
    changeDate(value, dateFormit) {
      this.date = value;
      console.log(this.date + " " + this.time);
    },
    changeTime(value, dateFormit) {
      this.time = value;
      console.log(this.date + " " + this.time);
    },
    // 绑定@imgAdd event
    $imgAdd(pos, $file) {
      var that = this;
      const $vm = this.$refs.md
      var parms = new FormData();
      parms.append("file", $file);
      console.log($file);
      this.$http({
        method: "POST",
        data: parms,
        url: that.GLOBAL.adress + "/admin/article/upload",
        headers: {
          token: this.$store.state.token
        }
      }).then(function(res) {
        if (res.data.success) {
          console.log(that.GLOBAL.adress + res.data.data.path);
          // this.$refs.editor.$img2Url(pos,that.GLOBAL.adress+res.data.data.path)
          $vm.$img2Url(pos, that.GLOBAL.adress + res.data.data.path);
        } else {
          that.$Notice.error({
            title: "上传图片失败"
          });
        }
      });
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