package datasource;

import lombok.Data;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

@Data
public class DBCPDataSourceFactory implements DataSourceFactory {

    private String username;
    private String password;
    private String driver;
    private String url;
    private int initialSize = 6;
    private int maxIdle = 8;
    private int minIdle = 6;

    @Override
    public void setProperties(Properties props) {
        username = props.getProperty("username");
        password = props.getProperty("password");
        driver = props.getProperty("driver");
        url = props.getProperty("url");

        initialSize = Integer.valueOf(props.getProperty("initialSize", ""+initialSize));
        maxIdle = Integer.valueOf(props.getProperty("maxIdle", ""+maxIdle));
        minIdle = Integer.valueOf(props.getProperty("minIdle", ""+minIdle));
    }

    @Override
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxIdle(maxIdle);
        dataSource.setMinIdle(minIdle);
        return dataSource;
    }

}
