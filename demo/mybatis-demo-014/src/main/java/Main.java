import java.io.IOException;
import java.util.List;

import bean.Blog;
import com.github.pagehelper.Page;
import com.sun.rowset.internal.Row;
import lombok.extern.slf4j.Slf4j;
import mapper.BlogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import org.junit.Test;

@Slf4j
public class Main {

    @Test
    public void test_01() throws IOException {
        SqlSession sqlSession = getSqlSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        List<Blog> blogList = blogMapper.findByUserId(1L, 0, 2);
        blogList.forEach(item -> {
            log.info("blog: {}", item);
        });
    }

    @Test
    public void test_02() throws IOException {
        SqlSession sqlSession = getSqlSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        List<Blog> blogList = blogMapper.findByUserIdWithRowBounds(1L, new RowBounds(0, 2));
        blogList.forEach(item -> {
            log.info("blog: {}", item);
        });
    }

    @Test
    public void test_03() throws IOException {
        SqlSession sqlSession = getSqlSessionWithPageHelperPluginInConfigXml();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        List<Blog> blogList = blogMapper.findByUserIdWithRowBounds(1L, new RowBounds(0, 2));
        blogList.forEach(item -> {
            log.info("blog: {}", item);
        });
    }

    private SqlSession getSqlSession() throws IOException {
        SqlSessionFactory sessionFactory;
        sessionFactory = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsReader("mybatis-config.xml"));
        return sessionFactory.openSession();
    }

    private SqlSession getSqlSessionWithPageHelperPluginInConfigXml() throws IOException {
        SqlSessionFactory sessionFactory;
        sessionFactory = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsReader("mybatis-config-with-pagehelper.xml"));
        return sessionFactory.openSession();
    }

}