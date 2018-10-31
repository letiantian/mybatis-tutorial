import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

        User user;

        /// v1

        System.out.println("findByNameAndPasswordV1");
        user = userMapper.findByNameAndPasswordV1("letian", "123");
        System.out.println(user);
        System.out.println("----");

        /// v2

        System.out.println("findByNameAndPasswordV2");
        user = userMapper.findByNameAndPasswordV2("letian", "123");
        System.out.println(user);
        System.out.println("----");

        /// v3

        System.out.println("findByNameAndPasswordV3");
        Map<String, Object> data = new HashMap<>();
        data.put("username", "letian");
        data.put("password", "123");
        user = userMapper.findByNameAndPasswordV3(data);
        System.out.println(user);
        System.out.println("----");

        /// v4

        System.out.println("findByNameAndPasswordV4");
        data = new HashMap<>();
        data.put("username", "letian");
        user = userMapper.findByNameAndPasswordV4(data, "123");
        System.out.println(user);
        System.out.println("----");

        /// v5

        System.out.println("findByNameAndPasswordV5");
        data = new HashMap<>();
        data.put("username", "letian");
        user = userMapper.findByNameAndPasswordV5(data, "123");
        System.out.println(user);
        System.out.println("----");

        /// v6

        System.out.println("findByNameAndPasswordV6");
        User queryUser = new User();
        queryUser.setName("letian");
        queryUser.setPassword("123");
        user = userMapper.findByNameAndPasswordV6(queryUser);
        System.out.println(user);
        System.out.println("----");

        /// v7

        System.out.println("findByNameAndPasswordV7");
        queryUser = new User();
        queryUser.setName("letian");
        queryUser.setPassword("123");
        user = userMapper.findByNameAndPasswordV7(queryUser);
        System.out.println(user);
        System.out.println("----");


    }
}