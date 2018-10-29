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


        int result = userMapper.deleteByMinId(3);
        sqlSession.commit();
        System.out.println(result);

        System.out.println("---");

        result = userMapper.deleteById(3);
        sqlSession.commit();
        System.out.println(result);

    }
}