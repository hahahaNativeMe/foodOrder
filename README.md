# foodOrder
 网上订餐

idea+jdbc+mysql8.0+Dao 
后端代码是自己的，前端是开源并根据项目进行改动 [网上订餐前端](https://themefisher.com/products/food-code-free-bootstrap-restaurant-web-template/) ,[管理员前端](http://x.xuebingsi.com/)

## 实现功能
1、注册登录<br>
2、网上订餐页面，前台看最新菜品，根据菜不同的种类进行选择，<br>
菜品详情页，菜品加入购物车，查看历史订单和购物车<br>
3、管理员页面对菜单、公告进行增删改查，对用户目录是删改查通过注册验证<br>

## 缺陷
1、没有用过滤器用户如果知道uri可以随便跳页面甚至管理员页面，但由于用户类型不是管理员所以无法登入<br>
2、首页没有设置默认url,需要手动输入首页url: https://localhost:8080/foodOrder/showIndex。<br>
   这是个调用servlet的uri,我没有找到可以将这种uri设置为默认页面的方法，如果知道方法，欢迎告知

## 数据库字段设计
总共4张表分别是food(食物表)，notice(公告表)，ordres(订单表)，users(用户表)<br>
1、food(食物表)
```
 名             类型      字段含义    长度<br>
 id            varchar    菜编号       36
 name          varchar    菜名         15
 price         float      菜价格
 category      varchar    菜种类       5
 pnum          int        菜数量
 imgurl        varchar    菜图片       255
 description   varchar    菜简介       255
```

2、notice(公告表)
```
 名             类型      字段含义    长度<br>
 n_id           int       公告编号       0
 title          varchar   公告标题       50
 details        varchar   公告详情       255
 n_time         date      公告操作时间    0
```

3、ordres(订单表)
```
 名           类型        字段含义                            长度<br>
 id          varchar      订单编号                             36
 studentid   varchar      学号(可以关联users表)                 13
 foodid      varchar      食物编号(可以关联food表)              36
 money       double       订单金额                              0
 num          int         订单数量                              0
 state        int         订单状态(0未支付1支付)                 0
 date        datetime     订单时间(精确到毫秒)                   0
 evaluation  varchar      订单评价(在项目上没用上是待完成块)      255
```

4、users(用户表)
```
 名           类型        字段含义                       长度<br>
 id          varchar      学号                             11    
 name        varchar      学生姓名                         10
 password    varchar      账号密码                         255
 telephone   varchar      联系电话                          11
 introduce   varchar      个人简介                         255
 role        varchar      身份(student学生admin管理员)      7
 state         int        账号状态(0未审核1审核通过)         0
 registTime  varchar      注册时间                         255
```

## 目录树
```
foodOrder
├─ .idea
├─ README.md 
├─ foodOrder.iml
├─ sql
│    └─ foodorder.sql                          //数据库文件
├─ src
│    └─ com
│           └─ lsu                            
│                  ├─ bean                     
│                  │    ├─ BaseDao.java          //每个bean都要调用的方法，连接数据库和关闭数据库
│                  │    ├─ Foods.java            //菜品对象
│                  │    ├─ Notice.java           //公告对象
│                  │    ├─ Order.java            //订单对象
│                  │    └─ User.java             //用户对象
│                  ├─ dao                        //定义对象的方法
│                  │    ├─ FoodsDao.java
│                  │    ├─ NoticeDao.java
│                  │    ├─ OrderDao.java
│                  │    ├─ UserDao.java
│                  │    └─ impl                 //实现对象的方法
│                  │           ├─ FoodDaoImpl.java
│                  │           ├─ NoticeDaoImpl.java
│                  │           ├─ OrderDaoImpl.java
│                  │           └─ UserDaoImpl.java
│                  ├─ servlet
│                  │    ├─ LoginServlet.java           //登录
│                  │    ├─ admin                        //管理员
│                  │    │    ├─ FoodServlet.java       //处理菜品相关操作
│                  │    │    ├─ NoticeServlet.java     //处理公告相关操作
│                  │    │    └─ UserServlet.java      //处理用户相关操作
│                  │    └─ client                              //客户
│                  │           ├─ NewUserServlet.java          //注册信息加入数据库
│                  │           ├─ NoticeFrontServlet.java      //餐厅页面公告显示
│                  │           ├─ OrderServlet.java            //订单相关操作处理
│                  │           ├─ ShopServlet.java             //菜品种类页相关逻辑
│                  │           └─ ShowIndexServlet.java        //动态显示首页
│                  └─ util
│                         └─ MyTool.java             //工具类（没用上）
└─ web
       ├─ META-INF                         
       │    └─ context.xml         //数据池配置
       ├─ WEB-INF
       │    ├─ lib
       │    │    ├─ jstl.jar
       │    │    ├─ mysql-connector-java-8.0.20.jar
       │    │    └─ standard.jar
       │    └─ web.xml
       ├─ admin
       │    ├─ README.md
       │    ├─ cart-list.jsp          //订单前端
       │    ├─ css
       │    ├─ echarts1.jsp          //本来想做一个热销菜品排行榜最后没做
       │    ├─ fonts
       │    ├─ food-add.jsp          //菜品添加前端
       │    ├─ food-edit.jsp         //菜品修改前端
       │    ├─ food-list.jsp         //菜品分页显示前端
       │    ├─ images                //X-admin这个里面自带的图片
       │    ├─ index.jsp             //管理员首页
       │    ├─ index1.jsp            //用户的订单页面由于要使用X-admin这个框架就没放到client
       │    ├─ js
       │    ├─ lib
       │    ├─ login.jsp            //登录页面
       │    ├─ member-edit-success.jsp //用户信息修改成功页面
       │    ├─ member-edit.jsp      //用户信息修改页面
       │    ├─ member-list.jsp      //用户信息分页显示前端
       │    ├─ newUser.jsp          //注册页面
       │    ├─ notice-add.jsp            //公告添加页面
       │    ├─ notice-edit-success.jsp   //公告修改成功页面
       │    ├─ notice-list.jsp          //公告分页显示页面
       │    ├─ notice-view.jsp          //公告详情页面
       │    ├─ order-food.jsp           //菜品详情页面
       │    ├─ order-list.jsp           //菜品分页列表页面
       │    ├─ unicode.html
       │    └─ welcome.jsp        //欢迎页面
       ├─ client
       │    ├─ blog.jsp          //公告页面
       │    ├─ buy-food.jsp      //菜品购买页面
       │    ├─ css               
       │    ├─ images            //网页前端自带的图片资源
       │    ├─ index.jsp         //网上订餐首页
       │    ├─ js                
       │    ├─ products.jsp      //全部菜品页面（调取所有菜品）
       │    ├─ products2.jsp     //种类菜品页面（只调取某个种类页面） //这俩可以前端写个选择但我能力有限就没精简
       │    └─ single-product.jsp //菜品详情页面
       ├─ foodimg      //食物图片（需要配置tomcat）
       ├─ index.jsp    //本来相当默认界面但没用上
       └─ layui       //前端框架自带的
```