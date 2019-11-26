# spring-batch-admin-backend

## 如何启动项目：
### 如何启动后端：
1. 下载代码：https://github.com/linghuxiong/spring-batch-admin-backend
2. 导入工程：推荐是用IDEA，直接打开下载的目录即可。
3. 初始化数据库: 找到目录：/spring-batch-admin-backend/src/main/db，里面有两个文件，一个是数据库创建脚本，一个是表结构+数据的脚本，先执行创建库的，如果想在已经存在的库里面运行程序，可以省略这一步，直接运行数据脚本即可。
4. 启动项目：如果使用的是上一步中默认的数据库建库语句，可以直接启动，如果是使用的自己的数据库，修改/spring-batch-admin-backend/src/main/resources/application-localhost.properties里面是数据配置信息，然后启动SpringBatchAdminApplication.java即可

![](https://raw.githubusercontent.com/linghuxiong/picback/master/img15747839886243.jpg)

### 如何启动前端
1. 下载代码：https://github.com/linghuxiong/spring-batch-admin-ui
2. npm install：在根目录运行`npm install`
3. npm start: install 之后，运行 `npm start` 

![](https://raw.githubusercontent.com/linghuxiong/picback/master/img15747840782809.jpg)

### 查看效果
浏览器打开 http://localhost:8000
进入登录页面，输入用户名密码：
* 用户名：admin
* 密码：123456 

登录成功之后即可进入系统查看

![](https://raw.githubusercontent.com/linghuxiong/picback/master/img15747838805787.jpg)

![](https://raw.githubusercontent.com/linghuxiong/picback/master/img15747851141862.jpg)

![](https://raw.githubusercontent.com/linghuxiong/picback/master/img15747839342373.jpg)

