# Pang-Blog
Pang-Blog 基于spring boot的后台
## 打包后台程序
**尽量先自己配置一下后台程序然后再运行，不然可能报所，请按需所求**
1. 运行**生成jar包.bat**
2. 进入**target**文件夹下
3. **pang-blog-0.0.1-SNAPSHOT.jar**即为项目打包以后的jar包，执行即可
## spring配置简介
### 基础配置
配置文件路径为**src/main/resources/application.properties**,下面如果不作特殊说明，默认即为这个配置文件。
- 配置数据库相关信息：`spring.datasource`下的相关配置（*注意url后面的参数尽量不要修改，否则程序可能出错*）
- 项目启动端口：`server.port`
- 静态文件（主要是图片）的绝对地址：`web.upload-path`，（*注意要把最后的 **/** 加上，不然文件路径可能会解析错误*）
- 文件压缩大小：`img.fileSize`,单位是**字节（b）**
- 文件压缩比例：`img.scaleRatio`
### 跨域配置
开启跨域的话，只需要在**src/main/java/com/pang/blog/config/CrosConfig.java**类中添加一下注释`@Configuration`再启动程序即可，配置完以后的类内容如下
```java
/**
 * @author pang
 * @version V1.0
 * @ClassName: CorsConfig
 * @Package com.pang.book.config
 * @description: 跨域配置
 * @date 2019/1/7 12:26
 */
@Configuration
public class CorsConfig {
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1允许任何域名使用
        corsConfiguration.addAllowedHeader("*"); // 2允许任何头
        corsConfiguration.addAllowedMethod("*"); // 3允许任何方法（post、get等）
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 4
        return new CorsFilter(source);
    }
}
```
## 数据库简介
### 数据表介绍
| 数据表名      |     简介 |
| :--------: | :--------:| 
| article_root|   文章的markdown记录 | 
| articles|   文章主要信息 | 
| comment|   文章内的评论 | 
| config|   博客的主要配置（采用键-值对方式存储） | 
| groups|   博客文章分类 | 
| message|   博客留言板 | 
| user|   博客管理员登陆信息 | 
### 数据表详细介绍
#### article_root
| 字段      |     简介 | 
| :--------: | :--------:| 
| id| 文章id，对应articles表的id  |  
| markdown| 文章markdown内容  |  
#### articles
| 字段      |     简介 | 
| :--------: | :--------:| 
|id |文章id，对应article_root表的id   |  
| texts| 文章内容（被解析为html）  |  
| first_time| 文章发表时间  |  
| click_num| 文章点击量   |  
| article_group|文章所属分组，对应groups表中的id   |  
| title| 文章标题  |  
| show|文章是否显示   |  
#### comment
| 字段      |     简介 | 
| :--------: | :--------:| 
| id|评论id   |  
| comment_texts|  评论内容 |  
| username| 评论人姓名  |  
|mail | 评论人邮箱  |  
| ip| 评论人ip  |  
| time| 评论时间  |  
| article_id|评论所属文章，对应articles表中的id   |  
#### config
| 字段      |     简介 | 
| :--------: | :--------:| 
| blog_key| 配置键  |  
| blog_value|  ֵ配置值 |  
| blog_note|  配置介绍 |  
##### 各个配置详解
blog-adminer	博客管理员名
blog-adress	主人地址
blog-filing	备案号
blog-git	博客git地址
blog-hello	博客问候语
blog-icon	博客图标
blog-message	留言板寄语
blog-name	博客名
#### groups
| 字段      |     简介 | 
| :--------: | :--------:| 
|id | 分组id  |  
| group_name|分组名   |  
| group_order| 分组顺序  |  
| group_show|是否显示分组   |
*最多支持7个分组*  
#### message
| 字段      |     简介 | 
| :--------: | :--------:| 
|id |留言id   |  
| time|留言时间   |  
| username|留言人昵称   |  
| mail|留言人邮箱   |  
| texts|留言内容   |  
#### user
| 字段      |     简介 | 
| :--------: | :--------:| 
| username|登陆用用户名   |  
| hashword|加密后的密码   |  
| salt|加密盐值   | 
*user表只保留一个用户* 
