# 12. 动态 SQL

动态SQL，是指SQL语句不是硬编码到代码中的，而是程序根据不同情况生成不同的SQL语句。MyBatis中动态SQL的内容还是挺多的，本文只做简单的介绍。更多内容请参考官方的[《动态SQL》](http://mybatis.github.io/mybatis-3/zh/dynamic-sql.html)。



本节示例代码在 [mybatis-demo-011](../../demo/mybatis-demo-011) 。



## 数据准备

见 [01. 数据准备](01-数据准备.md)。

user表的默认内容如下：

```plain
mysql> select * from user;
+----+--------+----------------+----------+
| id | name   | email          | password |
+----+--------+----------------+----------+
|  1 | letian | letian@111.com | 123      |
|  2 | xiaosi | xiaosi@111.com | 123      |
+----+--------+----------------+----------+
```


## 项目结构

使用 IDEA 创建 gradle 项目，最终结构如下：

<img src="../img/0001.jpg" width=300 />

## 示例1

### 在 UserMapper接口增加方法

```java
/**
 * 根据用户名和密码查询用户。用户名可选
 */
User findByName(@Param("name") String name, @Param("optionalPassword") String password);
```

findByName 根据用户名和密码查询用户，其中用户名是必须的，密码是可选的。也就是，若没有密码，则SQL查询条件中不会有密码的判断。



### 在 UserMapper.xml 增加映射

```xml
<select id="findByName" resultType="bean.User">
    select * from blog_db.user
    where name=#{name}
    <if test="optionalPassword != null">
        and password = #{optionalPassword}
    </if>
</select>
```

`<if>`中判断 optionalPassword 是否为空，若不为空，则将`and password = #{optionalPassword}`作为SQL的一部分。



### 在Main类中增加示例代码

```java
@Test
public void test_01() throws IOException {
    try (SqlSession sqlSession = getSqlSession()) {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findByName("letian", null);
        log.info("{}", user);
    }
}

@Test
public void test_02() throws IOException {
    try (SqlSession sqlSession = getSqlSession()) {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findByName("letian", "1234");
        log.info("{}", user);
    }
}
```

运行 test_01 ，输出：

```plain
 INFO [main] - User(id=1, name=letian, email=letian@111.com, password=123)
```

运行 test_02 ，输出：

```
 INFO [main] - null
```



## 示例2

### 在 UserMapper接口增加方法

```java
/**
 * 根据查询条件查询所有符合要求的用户
 */
List<User> find(User queryCondition);
```



### 在 UserMapper.xml 增加映射

```xml
<select id="find" parameterType="bean.User" resultType="bean.User">
    select * from blog_db.user
    <where>
        <if test="id != null">
            and id = #{id}
        </if>
        <if test="name != null">
            and name = #{name}
        </if>
        <if test="email != null">
            and email = #{email}
        </if>
        <if test="password != null">
            and password = #{password}
        </if>
    </where>
</select>
```

这里用了 `<where>`和`<if>`，每个`<if>`中判断某个字段是否有值，若有值，则在查询条件中增加该字段的判等。注意，每个`<if>`中的最前面都是`and`，会不会动态生成的SQL中出现`where and` 这种错误的SQL？不会，mybatis会处理好这种情况。



### 在Main类中增加示例代码

```java
@Test
public void test_03() throws IOException {
    try (SqlSession sqlSession = getSqlSession()) {
        User queryCondition = new User();
        queryCondition.setId(1L);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.find(queryCondition);
        log.info("{}", userList);
    }
}

@Test
public void test_04() throws IOException {
    try (SqlSession sqlSession = getSqlSession()) {
        User queryCondition = new User();
        queryCondition.setPassword("123");
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.find(queryCondition);
        log.info("{}", userList);
    }
}
```

`test_03`中查询条件给了id，运行结果为：

```java
 INFO [main] - [User(id=1, name=letian, email=letian@111.com, password=123)]
```

test_03`中查询条件给了password，运行结果为：

```plain
 INFO [main] - [User(id=1, name=letian, email=letian@111.com, password=123), User(id=2, name=xiaosi, email=xiaosi@111.com, password=123)]
```

得到两条记录，符合预期 😁

