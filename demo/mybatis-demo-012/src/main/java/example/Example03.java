package example;

import demo.dao.BlogMapper;
import demo.dao.UserMapper;
import demo.model.Blog;
import demo.model.BlogExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.List;

public class Example03 {

    public static void main(String[] args) throws IOException {

        SqlSessionFactory sessionFactory;
        sessionFactory = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsReader("mybatis-config.xml"));

        SqlSession sqlSession = sessionFactory.openSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        BlogExample blogExample = new BlogExample();
        blogExample.createCriteria().andIdBetween(1, 4);
        // 不要用 selectByExample，用selectByExampleWithBLOBs，因为 title、content类型为text，会被当做blob。
        List<Blog> blogList = blogMapper.selectByExampleWithBLOBs(blogExample);

        blogList.forEach(b->{
            System.out.println(b);
        });
    }

}
