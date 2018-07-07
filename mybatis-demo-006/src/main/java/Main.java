import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import bean.User;
import dao.UserMapper;


public class Main {

    public static void main(String[] args) throws IOException {

        SqlSessionFactory sessionFactory;
        sessionFactory = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsReader("mybatis-config.xml"));

        SqlSession sqlSession = sessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // insertUserV1
        User user1 = new User();
        user1.setName("xiaowei");
        user1.setEmail("xiaowei@111.com");
        user1.setPassword("456");

        int result = userMapper.insertUserV1(user1);
        sqlSession.commit();  // 一定要有的

        System.out.println(result);
        System.out.println(user1);

        System.out.println("-----");

        // insertUserV2
        User user2 = new User();
        user2.setName("xiaohu");
        user2.setEmail("xiaohu@111.com");
        user2.setPassword("456");

        result = userMapper.insertUserV2(user2);
        sqlSession.commit();

        System.out.println(result);
        System.out.println(user2);

    }
}