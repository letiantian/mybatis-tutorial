package datasource;

import org.apache.ibatis.datasource.pooled.PooledDataSource;

import javax.sql.DataSource;

public class MyBatisDataSource {

    public static DataSource get() {
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/blog_db?useUnicode=true&characterEncoding=utf8");
        dataSource.setDriver("com.mysql.jdbc.Driver");
        dataSource.setPoolMaximumActiveConnections(100);
        dataSource.setPoolMaximumIdleConnections(8);
        return dataSource;
    }

}
