package example;

import demo.dao.BlogMapper;
import demo.dao.UserMapper;
import demo.model.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * 插入数据
 */
public class Example02 {

    public static void main(String[] args) throws IOException {

        SqlSessionFactory sessionFactory;
        sessionFactory = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsReader("mybatis-config.xml"));

        SqlSession sqlSession = sessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        Blog blog = new Blog();
        blog.setOwnerId(1);
        blog.setTitle("你好, World");
        blog.setContent("你好, 😆");

        int result = blogMapper.insertSelective(blog);
        System.out.println(result);
        System.out.println(blog);
        sqlSession.commit();

        System.out.println("----");

        Blog b = blogMapper.selectByPrimaryKey(blog.getId());
        System.out.println(b);

    }
}
