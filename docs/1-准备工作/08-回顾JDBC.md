# 08. 回顾 JDBC

为什么要回顾 JDBC ？因为 MyBatis 本质上是对 JDBC 的封装。

我们看下维基百科对 JDBC 的解释：

> Java数据库连接，（Java Database Connectivity，简称JDBC）是Java语言中用来规范客户端程序如何来访问数据库的应用程序接口，提供了诸如查询和更新数据库中数据的方法。JDBC也是Sun Microsystems的商标[1]。JDBC是面向关系型数据库的。


本文简单讲解下如何通过 JDBC 查询 MySQL 表中数据：

## 准备数据
```
-- 若有则删除
DROP DATABASE IF EXISTS `blog_db`;

-- 创建数据库
CREATE DATABASE `blog_db` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 用户表
CREATE  TABLE `blog_db`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `email` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uk_name` (`name`) -- name 保证唯一
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_general_ci;

-- 用户表添加数据
INSERT INTO `blog_db`.`user` (`name`, `email`, `password`) VALUES ('letian', 'letian@111.com', '123');
INSERT INTO `blog_db`.`user` ( `name`, `email`, `password`) VALUES ('xiaosi', 'xiaosi@111.com', '123');
```

查看数据：

```
mysql> use blog_db;
mysql> select * from user;
+----+--------+----------------+----------+
| id | name   | email          | password |
+----+--------+----------------+----------+
|  1 | letian | letian@111.com | 123      |
|  2 | xiaosi | xiaosi@111.com | 123      |
+----+--------+----------------+----------+
```



## 查询示例

新建 gradle 项目，在 build.gradle 加入依赖：

```
compile group: 'mysql', name: 'mysql-connector-java', version: '5.1.47'

compile group: 'org.projectlombok', name: 'lombok', version: '1.18.0'

compile group: 'org.slf4j', name: 'slf4j-api', version: '1.7.25'
compile group: 'org.slf4j', name: 'slf4j-log4j12', version: '1.7.25'
compile group: 'log4j', name: 'log4j', version: '1.2.17'

compile group: 'junit', name: 'junit', version: '4.12'
```



新建 Main 类，加入示例代码：

```java
import java.io.IOException;
import java.sql.*;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;


@Slf4j
public class Main {

    @Test
    public void test_01() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn =  DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/blog_db?useUnicode=true&amp;characterEncoding=utf8",
                "root",
                "123456"
        );

        Statement stmt = conn.createStatement();
        String sql = "SELECT id, name, email, password FROM user WHERE id=1";
        ResultSet rs = stmt.executeQuery(sql);

        // 读取数据
        while(rs.next()){
            log.info("id: {}", rs.getLong("id"));
            log.info("name: {}", rs.getString("name"));
            log.info("email: {}", rs.getString("email"));
            log.info("password: {}", rs.getString("password"));
        }

        // 关闭资源
        rs.close();
        stmt.close();
        conn.close();
    }

    @Test
    public void test_02() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn =  DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/blog_db?useUnicode=true&amp;characterEncoding=utf8",
                "root",
                "123456"
        );

        PreparedStatement stmt = conn.prepareStatement("SELECT id, name, email, password FROM user WHERE id=?");
        stmt.setLong(1, 1); // 第1个参数代表对应sql的第几个问好，第2个参数代表对应的值
        ResultSet rs = stmt.executeQuery();

        // 读取数据
        while(rs.next()){
            log.info("id: {}", rs.getLong("id"));
            log.info("name: {}", rs.getString("name"));
            log.info("email: {}", rs.getString("email"));
            log.info("password: {}", rs.getString("password"));
        }

        // 关闭资源
        rs.close();
        stmt.close();
        conn.close();
    }

}
```



test_01 函数是直接执行sql；test_01 通过 PrepareStatement 执行查询，更安全。



两个示例的执行结果都是：

```
 INFO [main] - id: 1
 INFO [main] - name: letian
 INFO [main] - email: letian@111.com
 INFO [main] - password: 123
```



如何更新数据、删除数据、事务的使用等等，在网络上可以搜索到，这里不做更多介绍。



本节代码示例见：  [jdbc-demo-001](../../demo/jdbc-demo-001) 。

