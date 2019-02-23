# Pang-Blog安装教程

### 必备环境

Java运行环境（我的Java环境为**jdk-11.0.1**）

MySQL 5.5.3以上吧（我的MySQL版本为5.7.25）

一个web服务器，如apache、nginx等（我的web服务器为nginx，因为可以用来做反向代理）

## 安装教程

#### 基于spring boot的后端安装

1. 使用git克隆源文件：`git clone-b spring https://github.com/pangyuworld/Pang-Blog.git `
2. 进入MySQL客户端：`mysql -u root -p`，然后输入密码（看不到的，输入就行）
3. 创建数据库：`create database blog;`（blog为数据库名，可更换）
4. 向刚刚创建的数据库导入源文件中的**blog.sql**文件：`source /home/ubuntu/Pang-blog/blog.sql;`（source后面的参数为.sql文件的绝对地址）
5. 创建一个新的数据库用户并赋予blog数据库的全部权限：`grant all on blog.* to test@'%'identified by ‘123’ ;`（其中blog.*代表blog数据库下的所有表，test代表新的用户名，123代表密码）
6. 退出数据库：`exit`
7. 修改配置文件，详情请见：[spring配置简介](https://github.com/pangyuworld/Pang-Blog/tree/spring)
8. 上述步骤确保无误以后，运行**生成jar包**程序：`./生成jar包.sh`或直接运行`生成jar包.bat`
9. 进入target目录下，**pang-blog-1.0.0.jar**即为生成的jar包，执行即可
10. 执行成功以后，尝试访问：`域名:端口号`，如出现**spring已经正常启动**字样，证明基于spring boot的后端已经正常启动
11. 点击[api测试]()，测试api是否正常启动，访问成功即为正常启动。

至此，基于spring boot的后端已经安装成功，下面是一些额外的配置

1. 若数据库出现中文乱码，是因为我为了支持emjoy表情而将数据库编码设为utf8mb4而导致的，添加一些配置即可，详情见[mysql支持emoji表情](https://www.cnblogs.com/jentary/p/6655471.html)]
2. 配置文件中存放图片的路径，有时可能报错，这是因为没有读写权限导致的，给指定目录执行以下指令`sudo chmod a+wr /var/www/html/upload`即可

#### 基于vue.js的前端安装

直接下载[vue前端打包](https://github.com/pangyuworld/Pang-Blog/archive/out.zip)然后解压到web服务器根目录即可

配置文件位于项目根目录下的**config**文件夹内，可以修改后台api地址

下载指令为：`wget https://github.com/pangyuworld/Pang-Blog/archive/out.zip`

解压指令为：`unzip out.zip`

移动指令为：`sudo mv out/* /var/www/html/`

