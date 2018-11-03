package datasource;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class DBCPDataSource {

    public static DataSource get() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/blog_db?useUnicode=true&characterEncoding=utf8");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setInitialSize(6);
        dataSource.setMaxIdle(8);
        dataSource.setMinIdle(6);
        return dataSource;
    }

}
