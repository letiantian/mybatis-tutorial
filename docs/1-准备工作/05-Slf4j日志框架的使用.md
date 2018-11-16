# 05. Slf4j日志框架的使用

slf4j全称是`Simple Logging Facade for Java`。facade是一种设计模式。

slf4j 是一个抽象程度更高的日志组件，本身并不提供实际的日志功能。实际的日志功能是通过log4j等日志组件实现，而使用者只需要关心 slf4j 给出的API。


## 示例1：整合Log4j

使用 IDEA 创建 gradle项目。

在 build.gradle 的 dependencies 中增加依赖：

```
compile group: 'org.slf4j', name: 'slf4j-api', version: '1.7.25'
compile group: 'org.slf4j', name: 'slf4j-log4j12', version: '1.7.25'
compile group: 'log4j', name: 'log4j', version: '1.2.17'
```

* `slf4j-api`提供了slf4j的抽象接口，我们作为使用者，只需要关心它提供的API就行。
* `slf4j-log4j12`是slf4j与log4j的桥接组件。
* `log4j`是我们常见的log4j日志组件。

在`src/main/java`目录下创建类`Example01.java`，内容如下：
```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Example01 {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Example01.class);
        logger.info("Hello {}", "World");
    }
}
```

`logger.info("Hello {}", "World");`的作用是打出info级别的日志内容`Hello World`。是的，我们不必写成`logger.info("Hello %s", "World");` 或者`logger.info("Hello " + "World");`，一切变得不用想太多了。

运行 Example01，输出：
```
log4j:WARN No appenders could be found for logger (Example01).
log4j:WARN Please initialize the log4j system properly.
log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
```

是的，没有输出我们想要的内容，反而报了警告。这是因为我们没有配置log4j。

在`src/main/resources`目录中创建文件`log4j.properties`，内容如下：
```
log4j.rootLogger=INFO, stdout
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%5p [%t] - %m%n
```

再次运行 Example01，将会输出：
```
 INFO [main] - Hello World
```

## 示例2：启用 Log4j 的 MDC 

log4j有一个MDC(Mapped Diagnostic Context)、NDC(Nested Diagnostic Context)机制，这个机制用于打印一些线程粒度上下文信息，但又不需要每次`logger.info`时把上下文信息填进去。

slf4j提供了对MDC的封装。

有什么使用场景呢？比如我们写了一个web服务器，客户端的每一次请求一般都是放在一个线程中处理的。如果我们想在日志中加入客户端的IP信息，就可以用到MDC。

首先修改`log4j.properties`中的`log4j.appender.stdout.layout.ConversionPattern`，在值中加入`%X{IP}`：
```
log4j.rootLogger=INFO, stdout
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%5p [%t] %X{IP} - %m%n
```

创建新的Java类Example02：
```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class Example02 {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Example01.class);
        MDC.put("IP", "123.123.123.123");
        logger.info("Hello {}", "World");
    }
}

```
运行结果：
```
 INFO [main] 123.123.123.123 - Hello World
```

Example01 也能正常运行，只不过没有IP信息而已。

## 示例3：整合slf4j-simple

`slf4j-simple`是slf4j系列自带的一个日志实现，可以不用配置，默认会把日志打印到终端。

使用 IDEA 新创建一个 gradle 项目。
`build.gradle`中添加依赖：
```
compile group: 'org.slf4j', name: 'slf4j-api', version: '1.7.25'
compile group: 'org.slf4j', name: 'slf4j-simple', version: '1.7.25'
```

编写类 Example01，内容如下：
```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Example01 {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Example01.class);
        logger.info("Hello {}", "World");
    }
}
```
运行后输出：
```
[main] INFO Example01 - Hello World
```

如果要配置`slf4j-simple`，可以参考下这篇文章： [How to configure slf4j-simple](https://stackoverflow.com/questions/14544991/how-to-configure-slf4j-simple)。
