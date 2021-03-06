QWE# 校园食物分享

## 成员信息

严萌 

## 简介

学校生活了这么久，难免会有不知道吃什么或点什么外卖的时候，这时通过app看看其他用户推荐的食物岂不极好。

## 开发环境

- **操作系统**：Windows
- **IDE**：Android Studio

## 成员分工


- **严萌**：负责主页面编写，数据库操作编写。

## 重点&难点



### 实现用户不多次点赞

通过在数据库中为每一个食物推荐新建一个表(表名为食物名+用户名)来存储每一个条目有哪些用户点过赞，然后我们就可以在用户点赞的时候搜索该表来防止重复点赞。
同时，由于我们为每一个条目建了一个表，在删除食物条目的时候，我们不仅要删除总表中该食物的条目，还要删除食物条目的表。即每次删除操作会涉及一个条目和一个以食物名+用户名创建的表。

## 功能信息

1.实现用户注册，登录
2.实现用户发布，删除食物评价
3.实现用户对食物评价进行点赞操作

## 实现方法

![输入图片说明](https://images.gitee.com/uploads/images/2020/0708/205934_a2a1559c_5426652.jpeg "流程图.JPG")

## 类

User 用户名，密码
food 食物名称，推荐人用户名，评语，热度

项目包含8个主要文件：

### 5个Activity文件

页面代码。基本上都是先确认相关控件的对应关系，然后设置相关的点击时间。
其中Mainactivity，AddFoodactivity，DeleteFoodactivity需要接受来自上一个activity传递的用户名。
Mainactivity，DeleteFoodactivity需要绑定recycleview的适配器。

### FoodAdapter.java

适配器代码

### UserService.java
- **login()**：判断用户的用户名和密码是否对应的上
- **register()**：判断用户名是否重复，然后注册该用户

### FoodService.java
- **add()**：将食物添加到总表
- **delete()**：将食物从总表删除
- **updateLike()**：更新总表中的食物信息，暂定为更新点赞认数
- **create_new_table()**：为每一个食物条目新建一个表，用来保存点赞的用户，表名设置为食物名加上用户名
- **delete_table()**：在数据库中删除食物表，在用户删除自己的食物评价时调用
- **add_like()**：在食物评价的分表中添加点赞用户，评价防止重复点赞


## 内容展示

![输入图片说明](https://images.gitee.com/uploads/images/2020/0708/210004_7efe14e5_5426652.jpeg "捕获.JPG")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0708/210019_c4f6aca3_5426652.jpeg "3.JPG")
