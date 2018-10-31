import java.io.IOException;

import bean.Blog;
import dao.BlogMapper;
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
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);


        User user = userMapper.findById(1);
        System.out.println("user name: " + user.getName());
        System.out.println("blogs: " + user.getBlogs());

        System.out.println("---");

        Blog blog = blogMapper.findById(1);
        System.out.println("blog title:" + blog.getTitle());
        System.out.println("blog owner:" + blog.getUser());

    }
}