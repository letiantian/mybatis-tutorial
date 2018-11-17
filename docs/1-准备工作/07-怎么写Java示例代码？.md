# 07. 怎么写 Java 示例代码？



为什么写示例代码？

1、自己写了一些Java代码，通过示例告诉别人该怎么用。

2、看到神奇的Java项目，通过编写示例学习怎么用。

对于我，最开始用的是下面的方案1。

### 方案1：创建类，然后写main函数

在类中写几个static函数，在 main 函数中有选择的调用，然后执行代码，看运行结果。运行结果来自哪里？来自`System.out.println` 。

但是这种方式要经常更换 main 函数中的代码。所以，后来优化为，写多个类，每个类都有 main 函数，尽量保证代码少改动。这也有问题，就是类太多。

后来，在用 junit 写了一堆单元测试代码后，我发现 junit 可以完美应对写示例代码的需求。

### 方案2：使用 Junit 单元测试框架

我现在用的 Intellij IDEA IDE 很好的集成了junit。在一个类中写一堆用`@Test`注解的函数，随便一个函数中鼠标右击，就有执行这个函数的选项。

举个简单的使用示例。

**第一步**，用 Intellij IDEA 创建一个基于 gradle 的 java 项目。

**第二步**，将 build.gradle 中依赖

```gradle
dependencies {
    testCompile group: 'junit', name: 'junit', version: '4.12'
}
```

修改为：

```gradle
dependencies {
    compile group: 'junit', name: 'junit', version: '4.12'
}
```

我们的目的不是写单测代码，而是示例代码。把`testCompile`改成`compile`，这样就可以在`main/java`目录中使用 junit 了。

**第三步**，写个示例：

```java
import org.junit.Test;

public class Demo {

    @Test
    public void test_01() {
        System.out.println(1+1);
    }

    @Test
    public void test_02() {
        System.out.println(1+2);
    }

}
```

搞定。

### 方案3：在方案2的基础上引入lombok和slf4j

lombok和slf4j的使用见之前的介绍。