# 06. Lombok的使用

lombok可以为我们减少样板代码，也可以让我们更早的体验Java的未来特性。

官网： https://projectlombok.org/

> Project Lombok is a java library that automatically plugs into your editor and build tools, spicing up your java.
> Never write another getter or equals method again. Early access to future java features such as val, and much more.

<!--more-->

## 示例1：使用@Data注解
这个注解，可以让我们不用写set、get、hashCode、toString方法。

项目结构：
```java

├── build.gradle
├── settings.gradle
└── src
    ├── main
    │   ├── java
    │   │   └── demo
    │   │       ├── Main.java
    │   │       └── Person.java
    │   └── resources
    └── test
        ├── java
        └── resources
```

build.gradle:

```gradle
plugins {
    id 'java'
}

group 'com.example'
version '1.0-SNAPSHOT'

sourceCompatibility = 1.8

repositories {
    mavenCentral()
}

dependencies {
    compile group: 'org.projectlombok', name: 'lombok', version: '1.18.0'

    testCompile group: 'junit', name: 'junit', version: '4.12'
}
```

虽然是compile，但lombok只是在编译期使用。
```java
package demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class Person implements Serializable {

    private String name;
    private int age;

}
```

```java
package demo;

public class Main {

    public static void main(String[] args) {
        Person p = new Person("Tom", 18);

        System.out.println(p);
        System.out.println(p.getName());
        System.out.println(p.getAge());
    }

}
```
编译运行：
```plain
$ gradle build
$ java -classpath build/libs/demo-1.0-SNAPSHOT.jar demo.Main
Person(name=Tom, age=18)
Tom
18
```
**用Intellij IDEA编辑、运行代码，需要装lombok插件，以及做一些配置。可以参考《[IDEA下lombok安装，以及找不到get,set的问题](https://blog.csdn.net/xzp_12345/article/details/80268834)》。** 

用lombok最可能遇到的问题是，代码看起来没问题，但是在IDE中一运行就报错。这是因为没有启用注解处理器（Annotation Processors）。进入 IDEA 菜单 => Preferences => Build, Execution, Deployment => Compiler => Annotation Processors ，选中`Enable annotation processing` 即可。


## 示例2：使用@Slf4j
Slf4j是一个日志框架。

项目结构：
```plain
├── build.gradle
├── settings.gradle
└── src
    ├── main
    │   ├── java
    │   │   └── demo
    │   │       └── Main.java
    │   └── resources
    └── test
        ├── java
        └── resources
``` 

build.gradle ：
```gradle
plugins {
    id 'java'
}

group 'com.example'
version '1.0-SNAPSHOT'

sourceCompatibility = 1.8

repositories {
    mavenCentral()
}

dependencies {
    compile group: 'org.projectlombok', name: 'lombok', version: '1.18.0'
    compile group: 'org.slf4j', name: 'slf4j-api', version: '1.7.25'
    compile group: 'org.slf4j', name: 'slf4j-simple', version: '1.7.25'

    testCompile group: 'junit', name: 'junit', version: '4.12'
}
```
@Slf4j 可以自动生成 log 变量。
```java
package demo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {
        log.info("1+1 = {}", 2);
    }

}

```

运行 `Main` 类，输出：
```plain
[main] INFO demo.Main - 1+1 = 2
```