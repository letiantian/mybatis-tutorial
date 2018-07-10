package example;

import demo.dao.BlogMapper;
import demo.dao.UserMapper;
import demo.model.Blog;
import demo.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * 通过 id 获取数据
 */
public class Example01 {

    public static void main(String[] args) throws IOException {

        SqlSessionFactory sessionFactory;
        sessionFactory = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsReader("mybatis-config.xml"));

        SqlSession sqlSession = sessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);


        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(user);

        System.out.println("---");

        Blog blog = blogMapper.selectByPrimaryKey(1);
        System.out.println(blog);

    }

}
