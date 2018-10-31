package example;

import demo.dao.BlogMapper;
import demo.model.Blog;
import demo.model.BlogExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.List;

/**
 * 分页查询
 */
public class Example04 {

    public static void main(String[] args) throws IOException {

        SqlSessionFactory sessionFactory;
        sessionFactory = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsReader("mybatis-config.xml"));

        SqlSession sqlSession = sessionFactory.openSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        BlogExample blogExample = new BlogExample();
        blogExample.createCriteria().andIdBetween(1, 4);
        RowBounds rowBounds = new RowBounds(0, 2); // offset 为0， limit 为2
        List<Blog> blogList = blogMapper.selectByExampleWithBLOBsWithRowbounds(blogExample, rowBounds);

        blogList.forEach(b->{
            System.out.println(b);
        });
    }

}
