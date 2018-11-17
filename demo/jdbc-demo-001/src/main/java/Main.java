import java.io.IOException;
import java.sql.*;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;


@Slf4j
public class Main {

    @Test
    public void test_01() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn =  DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/blog_db?useUnicode=true&amp;characterEncoding=utf8",
                "root",
                "123456"
        );

        Statement stmt = conn.createStatement();
        String sql = "SELECT id, name, email, password FROM user WHERE id=1";
        ResultSet rs = stmt.executeQuery(sql);

        // 读取数据
        while(rs.next()){
            log.info("id: {}", rs.getLong("id"));
            log.info("name: {}", rs.getString("name"));
            log.info("email: {}", rs.getString("email"));
            log.info("password: {}", rs.getString("password"));
        }

        // 关闭资源
        rs.close();
        stmt.close();
        conn.close();
    }

    @Test
    public void test_02() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn =  DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/blog_db?useUnicode=true&amp;characterEncoding=utf8",
                "root",
                "123456"
        );

        PreparedStatement stmt = conn.prepareStatement("SELECT id, name, email, password FROM user WHERE id=?");
        stmt.setLong(1, 1); // 第1个参数代表对应sql的第几个问好，第2个参数代表对应的值
        ResultSet rs = stmt.executeQuery();

        // 读取数据
        while(rs.next()){
            log.info("id: {}", rs.getLong("id"));
            log.info("name: {}", rs.getString("name"));
            log.info("email: {}", rs.getString("email"));
            log.info("password: {}", rs.getString("password"));
        }

        // 关闭资源
        rs.close();
        stmt.close();
        conn.close();
    }

}