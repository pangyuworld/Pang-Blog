<template>
  <div class="box404">
    <img src="../assets/head404.png">
    <div class="txtbg404">
      <div class="txtbox">
        <p>对不起，您请求的页面不存在、或已被删除、或暂时不可用</p>
        <p class="paddingbox">请点击以下链接继续浏览网页</p>
        <p>
          ->
          <a style="cursor:pointer" onclick="history.back()">返回上一页面</a>
        </p>
        <p>->
          <router-link to="/">返回网站首页</router-link>
        </p>
      </div>
    </div>
  </div>
</template>
<script>
export default {};
</script>
<style>
.box404 {
  background-color: #494949;
  height: 41vw;
}
.txtbg404 {
  background-image: url(/imgs/error/txtbg404.png);
  width: 499px;
  height: 169px;
  margin: 10px auto 0 auto;
}
.txtbg404 .txtbox {
  width: 390px;
  position: relative;
  top: 30px;
  left: 60px;
  color: #eee;
  font-size: 13px;
}

.txtbg404 .txtbox p {
  margin: 5px 0;
  line-height: 18px;
}

.txtbg404 .txtbox .paddingbox {
  padding-top: 15px;
}

.txtbg404 .txtbox p a {
  color: #eee;
  text-decoration: none;
}

.txtbg404 .txtbox p a:hover {
  color: #fc9d1d;
  text-decoration: underline;
}
</style>
