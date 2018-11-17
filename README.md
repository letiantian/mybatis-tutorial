# mybatis 入门教程


> MyBatis 是一款优秀的持久层框架，它支持定制化 SQL、存储过程以及高级映射。MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。MyBatis 可以使用简单的 XML 或注解来配置和映射原生信息，将接口和 Java 的 POJOs(Plain Old Java Objects,普通的 Java对象)映射成数据库中的记录。

官方文档：[http://www.mybatis.org/mybatis-3/zh/index.html](http://www.mybatis.org/mybatis-3/zh/index.html)


目录：

* [1-准备工作](1-%E5%87%86%E5%A4%87%E5%B7%A5%E4%BD%9C)
	* [01-MyBatis是什么？.md](docs/1-%E5%87%86%E5%A4%87%E5%B7%A5%E4%BD%9C/01-MyBatis%E6%98%AF%E4%BB%80%E4%B9%88%EF%BC%9F.md)
	* [02-软件安装与使用.md](docs/1-%E5%87%86%E5%A4%87%E5%B7%A5%E4%BD%9C/02-%E8%BD%AF%E4%BB%B6%E5%AE%89%E8%A3%85%E4%B8%8E%E4%BD%BF%E7%94%A8.md)
	* [03-使用Intellij-IDEA创建Java项目.md](docs/1-%E5%87%86%E5%A4%87%E5%B7%A5%E4%BD%9C/03-%E4%BD%BF%E7%94%A8Intellij-IDEA%E5%88%9B%E5%BB%BAJava%E9%A1%B9%E7%9B%AE.md)
	* [04-Junit的使用.md](docs/1-%E5%87%86%E5%A4%87%E5%B7%A5%E4%BD%9C/04-Junit%E7%9A%84%E4%BD%BF%E7%94%A8.md)
	* [05-Slf4j日志框架的使用.md](docs/1-%E5%87%86%E5%A4%87%E5%B7%A5%E4%BD%9C/05-Slf4j%E6%97%A5%E5%BF%97%E6%A1%86%E6%9E%B6%E7%9A%84%E4%BD%BF%E7%94%A8.md)
	* [06-Lombok的使用.md](docs/1-%E5%87%86%E5%A4%87%E5%B7%A5%E4%BD%9C/06-Lombok%E7%9A%84%E4%BD%BF%E7%94%A8.md)
	* [07-怎么写Java示例代码？.md](docs/1-%E5%87%86%E5%A4%87%E5%B7%A5%E4%BD%9C/07-%E6%80%8E%E4%B9%88%E5%86%99Java%E7%A4%BA%E4%BE%8B%E4%BB%A3%E7%A0%81%EF%BC%9F.md)
	* [08-回顾JDBC.md](docs/1-%E5%87%86%E5%A4%87%E5%B7%A5%E4%BD%9C/08-%E5%9B%9E%E9%A1%BEJDBC.md)
* [2-通过示例学习MyBatis](2-%E9%80%9A%E8%BF%87%E7%A4%BA%E4%BE%8B%E5%AD%A6%E4%B9%A0MyBatis)
	* [01-数据准备.md](docs/2-%E9%80%9A%E8%BF%87%E7%A4%BA%E4%BE%8B%E5%AD%A6%E4%B9%A0MyBatis/01-%E6%95%B0%E6%8D%AE%E5%87%86%E5%A4%87.md)
	* [02-查找id为1的用户信息.md](docs/2-%E9%80%9A%E8%BF%87%E7%A4%BA%E4%BE%8B%E5%AD%A6%E4%B9%A0MyBatis/02-%E6%9F%A5%E6%89%BEid%E4%B8%BA1%E7%9A%84%E7%94%A8%E6%88%B7%E4%BF%A1%E6%81%AF.md)
	* [03-自定义连接池.md](docs/2-%E9%80%9A%E8%BF%87%E7%A4%BA%E4%BE%8B%E5%AD%A6%E4%B9%A0MyBatis/03-%E8%87%AA%E5%AE%9A%E4%B9%89%E8%BF%9E%E6%8E%A5%E6%B1%A0.md)
	* [04-如果不 MyBatis配置文件.md](docs/2-%E9%80%9A%E8%BF%87%E7%A4%BA%E4%BE%8B%E5%AD%A6%E4%B9%A0MyBatis/04-%E5%A6%82%E6%9E%9C%E4%B8%8D%E7%94%A8%08MyBatis%E9%85%8D%E7%BD%AE%E6%96%87%E4%BB%B6.md)
	* [05-查询密码为123的所有用户.md](docs/2-%E9%80%9A%E8%BF%87%E7%A4%BA%E4%BE%8B%E5%AD%A6%E4%B9%A0MyBatis/05-%E6%9F%A5%E8%AF%A2%E5%AF%86%E7%A0%81%E4%B8%BA123%E7%9A%84%E6%89%80%E6%9C%89%E7%94%A8%E6%88%B7.md)
	* [06-如果Bean中成员变量和表中字段命名不一致.md](docs/2-%E9%80%9A%E8%BF%87%E7%A4%BA%E4%BE%8B%E5%AD%A6%E4%B9%A0MyBatis/06-%E5%A6%82%E6%9E%9CBean%E4%B8%AD%E6%88%90%E5%91%98%E5%8F%98%E9%87%8F%E5%92%8C%E8%A1%A8%E4%B8%AD%E5%AD%97%E6%AE%B5%E5%91%BD%E5%90%8D%E4%B8%8D%E4%B8%80%E8%87%B4.md)
	* [07-更多查询用户的方式.md](docs/2-%E9%80%9A%E8%BF%87%E7%A4%BA%E4%BE%8B%E5%AD%A6%E4%B9%A0MyBatis/07-%E6%9B%B4%E5%A4%9A%E6%9F%A5%E8%AF%A2%E7%94%A8%E6%88%B7%E7%9A%84%E6%96%B9%E5%BC%8F.md)
	* [08-排序.md](docs/2-%E9%80%9A%E8%BF%87%E7%A4%BA%E4%BE%8B%E5%AD%A6%E4%B9%A0MyBatis/08-%E6%8E%92%E5%BA%8F.md)
	* [09-日志.md](docs/2-%E9%80%9A%E8%BF%87%E7%A4%BA%E4%BE%8B%E5%AD%A6%E4%B9%A0MyBatis/09-%E6%97%A5%E5%BF%97.md)
	* [10-添加、删除、修改数据.md](docs/2-%E9%80%9A%E8%BF%87%E7%A4%BA%E4%BE%8B%E5%AD%A6%E4%B9%A0MyBatis/10-%E6%B7%BB%E5%8A%A0%E3%80%81%E5%88%A0%E9%99%A4%E3%80%81%E4%BF%AE%E6%94%B9%E6%95%B0%E6%8D%AE.md)
	* [11-事务.md](docs/2-%E9%80%9A%E8%BF%87%E7%A4%BA%E4%BE%8B%E5%AD%A6%E4%B9%A0MyBatis/11-%E4%BA%8B%E5%8A%A1.md)
	* [12-动态SQL.md](docs/2-%E9%80%9A%E8%BF%87%E7%A4%BA%E4%BE%8B%E5%AD%A6%E4%B9%A0MyBatis/12-%E5%8A%A8%E6%80%81SQL.md)
	* [13-一对一和一对多的实现.md](docs/2-%E9%80%9A%E8%BF%87%E7%A4%BA%E4%BE%8B%E5%AD%A6%E4%B9%A0MyBatis/13-%E4%B8%80%E5%AF%B9%E4%B8%80%E5%92%8C%E4%B8%80%E5%AF%B9%E5%A4%9A%E7%9A%84%E5%AE%9E%E7%8E%B0.md)
	* [14-一对一和一对多的延迟加载.md](docs/2-%E9%80%9A%E8%BF%87%E7%A4%BA%E4%BE%8B%E5%AD%A6%E4%B9%A0MyBatis/14-%E4%B8%80%E5%AF%B9%E4%B8%80%E5%92%8C%E4%B8%80%E5%AF%B9%E5%A4%9A%E7%9A%84%E5%BB%B6%E8%BF%9F%E5%8A%A0%E8%BD%BD.md)
	* [15-分页查询.md](docs/2-%E9%80%9A%E8%BF%87%E7%A4%BA%E4%BE%8B%E5%AD%A6%E4%B9%A0MyBatis/15-%E5%88%86%E9%A1%B5%E6%9F%A5%E8%AF%A2.md)
	* [16-把SQL写在注解中.md](docs/2-%E9%80%9A%E8%BF%87%E7%A4%BA%E4%BE%8B%E5%AD%A6%E4%B9%A0MyBatis/16-%E6%8A%8ASQL%E5%86%99%E5%9C%A8%E6%B3%A8%E8%A7%A3%E4%B8%AD.md)
	* [17-自动生成Mapper代码和映射XML.md](docs/2-%E9%80%9A%E8%BF%87%E7%A4%BA%E4%BE%8B%E5%AD%A6%E4%B9%A0MyBatis/17-%E8%87%AA%E5%8A%A8%E7%94%9F%E6%88%90Mapper%E4%BB%A3%E7%A0%81%E5%92%8C%E6%98%A0%E5%B0%84XML.md)