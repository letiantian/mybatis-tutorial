# MyBatis 入门

MySQL 使用 5.6 ， Gradle使用 4.1。

## 001. 查询id为1的用户的信息

代码见[mybatis-demo-001](mybatis-demo-001)。


先创建数据：
```sql
-- 创建数据库
create database blog_db;

-- 用户表
CREATE  TABLE `blog_db`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `email` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

-- name 字段保证唯一
ALTER TABLE `blog_db`.`user` 
ADD UNIQUE INDEX `name_unique` (`name` ASC) ;

-- 用户表添加数据
INSERT INTO `blog_db`.`user` (`name`, `email`, `password`) VALUES ('letian', 'letian@111.com', '123');

INSERT INTO `blog_db`.`user` ( `name`, `email`, `password`) VALUES ('xiaosi', 'xiaosi@111.com', '123');

-- 博客表
CREATE  TABLE `blog_db`.`blog` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `owner_id` INT NOT NULL ,  -- 所属用户的id
  `title` TEXT NOT NULL ,
  `content` TEXT NOT NULL ,
  PRIMARY KEY (`id`) )
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

-- 博客表插入数据
INSERT INTO `blog_db`.`blog` (`owner_id`, `title`, `content`) VALUES ('1', '标题1', '文本1');
INSERT INTO `blog_db`.`blog` (`owner_id`, `title`, `content`) VALUES ('1', '标题2', '文本2');
INSERT INTO `blog_db`.`blog` (`owner_id`, `title`, `content`) VALUES ('1', '标题3', '文本3');
INSERT INTO `blog_db`.`blog` (`owner_id`, `title`, `content`) VALUES ('1', '标题4', '文本4');
INSERT INTO `blog_db`.`blog` (`owner_id`, `title`, `content`) VALUES ('1', '标题5', '文本5');
INSERT INTO `blog_db`.`blog` (`owner_id`, `title`, `content`) VALUES ('2', '标题21', '文本21');
```

查看数据：

```
mysql> select * from blog_db.user;
+----+--------+----------------+----------+
| id | name   | email          | password |
+----+--------+----------------+----------+
|  1 | letian | letian@111.com | 123      |
|  2 | xiaosi | xiaosi@111.com | 123      |
+----+--------+----------------+----------+
2 rows in set (0.00 sec)

mysql> select * from blog_db.blog;
+----+----------+----------+----------+
| id | owner_id | title    | content  |
+----+----------+----------+----------+
|  1 |        1 | 标题1    | 文本1    |
|  2 |        1 | 标题2    | 文本2    |
|  3 |        1 | 标题3    | 文本3    |
|  4 |        1 | 标题4    | 文本4    |
|  5 |        1 | 标题5    | 文本5    |
|  6 |        2 | 标题21   | 文本21   |
+----+----------+----------+----------+
6 rows in set (0.00 sec)
```



运行 Main 类，输出：
```
User{id=1, name='letian', email='letian@111.com', password='123'}
```

在 UserMapper.xml 中，给`User findById(int id)`设置的SQL是：
```
select * from blog_db.user where id=#{id}
```
findById只有一个参数，而且Java本身是识别不出函数的参数名的。
所以`#{id}`中`id`可以改成其他值，例如：

```
select * from blog_db.user where id=#{id_value}
```


## 002. 查询所有密码为123的用户

代码见[mybatis-demo-002](mybatis-demo-002)，在[mybatis-demo-001](mybatis-demo-001)的基础上为UserMapper类增加了两个方法：
```java
List<User> findByPassword(String password);

User findOneUserByPassword(String password); // 必须保证最多返回一条数据，否则会报 TooManyResultsException 错误。无数据，则返回null
```

运行 Main 类，输出：
```
null
---
User{id=1, name='letian', email='letian@111.com', password='123'}
---
User{id=1, name='letian', email='letian@111.com', password='123'}
User{id=2, name='xiaosi', email='xiaosi@111.com', password='123'}
```

## 003. 如果Bean中成员变量和表中字段命名不一致
代码见[mybatis-demo-002](mybatis-demo-002)，以[mybatis-demo-001](mybatis-demo-001)为基础。

我们将bean.User类中的name字段改成username。数据库user表中对应的字段名仍是name。

此时运行Main类，输出：
```
User{id=1, username='null', email='letian@111.com', password='123'}
```
username成了null。

怎么解决？一种方法是改写UserMapper.xml 中的SQL语句，将
```
select * from blog_db.user where id=#{id}
```
改写成
```
select id, name AS username, email, password from blog_db.user where id=#{id}
```

另一种方法是，在UserMapper.xml加入resultMap配置：
```
<mapper namespace="dao.UserMapper">
    <resultMap id="userResult" type="bean.User">
        <result property="username" column="name" javaType="String" jdbcType="VARCHAR"/>
    </resultMap>

    <select id="findById" parameterType="int" resultMap="userResult" resultType="bean.User">
        select * from blog_db.user where id=#{id}
    </select>
</mapper>
```

## 004. 如何在SQL语句中注入多个参数
代码见[mybatis-demo-004](mybatis-demo-004)，以[mybatis-demo-001](mybatis-demo-001)为基础。

运行 Main 类，输出：

```
findByNameAndPasswordV1
User{id=1, name='letian', email='letian@111.com', password='123'}
----
findByNameAndPasswordV2
User{id=1, name='letian', email='letian@111.com', password='123'}
----
findByNameAndPasswordV3
User{id=1, name='letian', email='letian@111.com', password='123'}
----
findByNameAndPasswordV4
User{id=1, name='letian', email='letian@111.com', password='123'}
----
findByNameAndPasswordV5
User{id=1, name='letian', email='letian@111.com', password='123'}
----
findByNameAndPasswordV6
User{id=1, name='letian', email='letian@111.com', password='123'}
----
findByNameAndPasswordV7
User{id=1, name='letian', email='letian@111.com', password='123'}
----
```

## 005. 日志支持

1. gradle 增加 log4j 依赖
2. 配置 log4j.properties

以为log4j为例子，参考 http://www.mybatis.org/mybatis-3/zh/logging.html 。

代码见[mybatis-demo-005](mybatis-demo-005)，以[mybatis-demo-004](mybatis-demo-004)为基础。

运行 Main 类，输出：
```
findByNameAndPasswordV1
DEBUG [main] - ==>  Preparing: select * from blog_db.user where name=? and password=? 
DEBUG [main] - ==> Parameters: letian(String), 123(String)
TRACE [main] - <==    Columns: id, name, email, password
TRACE [main] - <==        Row: 1, letian, letian@111.com, 123
DEBUG [main] - <==      Total: 1
User{id=1, name='letian', email='letian@111.com', password='123'}
----
findByNameAndPasswordV2
DEBUG [main] - ==>  Preparing: select * from blog_db.user where name=? and password=? 
DEBUG [main] - ==> Parameters: letian(String), 123(String)
TRACE [main] - <==    Columns: id, name, email, password
TRACE [main] - <==        Row: 1, letian, letian@111.com, 123
DEBUG [main] - <==      Total: 1
User{id=1, name='letian', email='letian@111.com', password='123'}
----
... 省略部分输出 ...
```

## 006. 插入数据

代码见[mybatis-demo-006](mybatis-demo-006)，以[mybatis-demo-001](mybatis-demo-001)为基础。

运行 Main 类，输出：

```
1
User{id=3, name='xiaowei', email='xiaowei@111.com', password='456'}
-----
1
User{id=4, name='xiaohu', email='xiaohu@111.com', password='456'}
```

查看数据库内容：
```
mysql> select * from user;
+----+---------+-----------------+----------+
| id | name    | email           | password |
+----+---------+-----------------+----------+
|  1 | letian  | letian@111.com  | 123      |
|  2 | xiaosi  | xiaosi@111.com  | 123      |
|  3 | xiaowei | xiaowei@111.com | 456      |
|  4 | xiaohu  | xiaohu@111.com  | 456      |
+----+---------+-----------------+----------+
4 rows in set (0.00 sec)
```
## 007. 删除数据

代码见[mybatis-demo-007](mybatis-demo-007)，以[mybatis-demo-006](mybatis-demo-006)为基础。

运行 Main 类，输出：

```
2
---
0
```

## 008. 一对多
查询一个用户的所有博客、获取一篇博客的信息及其所属用户的信息。

用户和博客是一对多的关系。

代码见[mybatis-demo-008](mybatis-demo-008)，以[mybatis-demo-001](mybatis-demo-001)为基础。

为UserBean增加一个新字段。


遇到这种错误：
```
Exception in thread "main" org.apache.ibatis.exceptions.PersistenceException: 
### Error querying database.  Cause: org.apache.ibatis.executor.ExecutorException: No constructor found in bean.User matching [java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.String, java.lang.String]
### The error may exist in mapper/UserMapper.xml
### The error may involve dao.UserMapper.findById
### The error occurred while handling results
```
增加一个无参的构造函数就行了。

运行 Main 类，输出：
```
User{id=1, name='letian', email='letian@111.com', password='123', blogs=[Blog{id=1, ownerId=1, title='标题1', content='文本1', user=null}, Blog{id=2, ownerId=1, title='标题2', content='文本2', user=null}, Blog{id=3, ownerId=1, title='标题3', content='文本3', user=null}, Blog{id=4, ownerId=1, title='标题4', content='文本4', user=null}, Blog{id=5, ownerId=1, title='标题5', content='文本5', user=null}]}
---
Blog{id=1, ownerId=1, title='标题1', content='文本1', user=User{id=1, name='letian', email='letian@111.com', password='123', blogs=null}}
```

## 009. 延迟加载数据

未延迟加载：
```
DEBUG [main] - ==>  Preparing: SELECT user.id AS user_id, user.name AS user_name, user.email AS user_email, user.password AS user_password FROM user WHERE user.id = ?; 
DEBUG [main] - ==> Parameters: 1(Integer)
TRACE [main] - <==    Columns: user_id, user_name, user_email, user_password
TRACE [main] - <==        Row: 1, letian, letian@111.com, 123
DEBUG [main] - ====>  Preparing: SELECT * FROM blog WHERE blog.owner_id=?; 
DEBUG [main] - ====> Parameters: 1(Integer)
TRACE [main] - <====    Columns: id, owner_id, title, content
TRACE [main] - <====        Row: 1, 1, <<BLOB>>, <<BLOB>>
TRACE [main] - <====        Row: 2, 1, <<BLOB>>, <<BLOB>>
TRACE [main] - <====        Row: 3, 1, <<BLOB>>, <<BLOB>>
TRACE [main] - <====        Row: 4, 1, <<BLOB>>, <<BLOB>>
TRACE [main] - <====        Row: 5, 1, <<BLOB>>, <<BLOB>>
DEBUG [main] - <====      Total: 5
DEBUG [main] - <==      Total: 1
user name: letian
blogs: [Blog{id=1, ownerId=0, title='标题1', content='文本1', user=null}, Blog{id=2, ownerId=0, title='标题2', content='文本2', user=null}, Blog{id=3, ownerId=0, title='标题3', content='文本3', user=null}, Blog{id=4, ownerId=0, title='标题4', content='文本4', user=null}, Blog{id=5, ownerId=0, title='标题5', content='文本5', user=null}]
---
DEBUG [main] - ==>  Preparing: SELECT blog.id AS blog_id, blog.id AS user_id, blog.title AS blog_title, blog.content AS blog_content FROM blog WHERE blog.id = ?; 
DEBUG [main] - ==> Parameters: 1(Integer)
TRACE [main] - <==    Columns: blog_id, user_id, blog_title, blog_content
TRACE [main] - <==        Row: 1, 1, <<BLOB>>, <<BLOB>>
DEBUG [main] - ====>  Preparing: SELECT id user_id, name user_name, email user_email, password user_password FROM user WHERE user.id=?; 
DEBUG [main] - ====> Parameters: 1(Integer)
TRACE [main] - <====    Columns: user_id, user_name, user_email, user_password
TRACE [main] - <====        Row: 1, letian, letian@111.com, 123
DEBUG [main] - <====      Total: 1
DEBUG [main] - <==      Total: 1
blog title:标题1
blog owner:User{id=1, name='letian', email='letian@111.com', password='123', blogs=null}
```


开启懒加载。在 ybatis-config.xml 中增加：
```xml
<settings>
    <setting name="lazyLoadingEnabled" value="true"/>
    <setting name="aggressiveLazyLoading" value="false"/>
</settings>
```

运行 Main 类，输出：

```
DEBUG [main] - ==>  Preparing: SELECT user.id AS user_id, user.name AS user_name, user.email AS user_email, user.password AS user_password FROM user WHERE user.id = ?; 
DEBUG [main] - ==> Parameters: 1(Integer)
TRACE [main] - <==    Columns: user_id, user_name, user_email, user_password
TRACE [main] - <==        Row: 1, letian, letian@111.com, 123
DEBUG [main] - <==      Total: 1
user name: letian
DEBUG [main] - ==>  Preparing: SELECT * FROM blog WHERE blog.owner_id=?; 
DEBUG [main] - ==> Parameters: 1(Integer)
TRACE [main] - <==    Columns: id, owner_id, title, content
TRACE [main] - <==        Row: 1, 1, <<BLOB>>, <<BLOB>>
TRACE [main] - <==        Row: 2, 1, <<BLOB>>, <<BLOB>>
TRACE [main] - <==        Row: 3, 1, <<BLOB>>, <<BLOB>>
TRACE [main] - <==        Row: 4, 1, <<BLOB>>, <<BLOB>>
TRACE [main] - <==        Row: 5, 1, <<BLOB>>, <<BLOB>>
DEBUG [main] - <==      Total: 5
blogs: [Blog{id=1, ownerId=0, title='标题1', content='文本1', user=null}, Blog{id=2, ownerId=0, title='标题2', content='文本2', user=null}, Blog{id=3, ownerId=0, title='标题3', content='文本3', user=null}, Blog{id=4, ownerId=0, title='标题4', content='文本4', user=null}, Blog{id=5, ownerId=0, title='标题5', content='文本5', user=null}]
---
DEBUG [main] - ==>  Preparing: SELECT blog.id AS blog_id, blog.id AS user_id, blog.title AS blog_title, blog.content AS blog_content FROM blog WHERE blog.id = ?; 
DEBUG [main] - ==> Parameters: 1(Integer)
TRACE [main] - <==    Columns: blog_id, user_id, blog_title, blog_content
TRACE [main] - <==        Row: 1, 1, <<BLOB>>, <<BLOB>>
DEBUG [main] - <==      Total: 1
blog title:标题1
DEBUG [main] - ==>  Preparing: SELECT id user_id, name user_name, email user_email, password user_password FROM user WHERE user.id=?; 
DEBUG [main] - ==> Parameters: 1(Integer)
TRACE [main] - <==    Columns: user_id, user_name, user_email, user_password
TRACE [main] - <==        Row: 1, letian, letian@111.com, 123
DEBUG [main] - <==      Total: 1
blog owner:User{id=1, name='letian', email='letian@111.com', password='123', blogs=null}
```

问题。如果遇到：
```
Caused by: org.xml.sax.SAXParseException; lineNumber: 32; columnNumber: 17; 元素类型为 "configuration" 的内容必须匹配 "(properties?,settings?,typeAliases?,typeHandlers?,objectFactory?,objectWrapperFactory?,reflectorFactory?,plugins?,environments?,databaseIdProvider?,mappers?)"。
	at com.sun.org.apache.xerces.internal.util.ErrorHandlerWrapper.createSAXParseException(ErrorHandlerWrapper.java:203)
```

## 010. 分页查询



## 011. 动态SQL


## 012. 自动生成Mapper




## 事务

## 连接池

## 注解

## 只获取表中的部分字段

## ${} 与 #{}

## alias

## 干掉 mybatis-config.xml


## 参考
* [浅入浅出MyBatis](https://www.letiantian.me/topics/#toc-mybatis)
* [MyBatis学习笔记](http://blog.51cto.com/legend2011/1030804)
* [官方文档](http://www.mybatis.org/mybatis-3/)




