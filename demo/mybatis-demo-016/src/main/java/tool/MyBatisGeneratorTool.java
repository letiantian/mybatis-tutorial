package tool;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 这种方案有一个问题，如果删除了生成的mapper，而业务逻辑里已经引用了。那么再IDEA中再运行这里的代码，会因为其他代码有问题，而报错
 */
public class MyBatisGeneratorTool {

    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        File configFile = new File(MyBatisGeneratorTool.class.getResource("/mybatis-generator.xml").getPath());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);

        // 虽然overwrite为true，但只针对java代码。
        // XML是以追加的形式保存到文件中；如果要重新生成，先删除之前的XML
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);

        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

}
