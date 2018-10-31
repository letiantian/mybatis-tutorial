import java.io.IOException;
import java.util.Iterator;
import java.util.List;

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

        User user1 = userMapper.findOneUserByPassword("1234");
        System.out.println(user1);

        System.out.println("---");

        User user2 = userMapper.findOneUserByPassword("123");
        System.out.println(user2);

        System.out.println("---");

        List<User> users = userMapper.findByPassword("123");
        Iterator<User> it = users.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

    }
}