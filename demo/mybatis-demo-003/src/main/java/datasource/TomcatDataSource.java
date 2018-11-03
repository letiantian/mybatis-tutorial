package datasource;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class TomcatDataSource {

    public static DataSource get() {
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
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
