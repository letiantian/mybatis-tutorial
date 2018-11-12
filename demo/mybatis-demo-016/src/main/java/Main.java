import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Blog;
import bean.BlogExample;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import mapper.BlogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import bean.User;
import mapper.UserMapper;
import org.junit.Test;

@Slf4j
public class Main {

    @Test
    public void test_01() throws IOException {
        try (SqlSession sqlSession = getSqlSession()){
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

            User user = userMapper.selectByPrimaryKey(1L);
            Blog blog = blogMapper.selectByPrimaryKey(1L);
            log.info("user: {}", user);
            log.info("blog: {}", blog);
        }
    }

    @Test
    public void test_02() throws IOException {
        try (SqlSession sqlSession = getSqlSession()){
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

            Blog blog = new Blog();
            blog.setOwnerId(1L);
            blog.setTitle("你好, World");
            blog.setContent("你好, 😆");

            int result = blogMapper.insertSelective(blog);
            log.info("result: {}", result);
            log.info("blog: {}", blog);
            // 因为没有commit，所以这条数据并未真正插入数据库
        }
    }


    @Test
    public void test_03() throws IOException {
        try (SqlSession sqlSession = getSqlSession()){
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

            BlogExample blogExample = new BlogExample();
            blogExample.createCriteria().andIdBetween(1L, 4L);
            blogExample.setOrderByClause("id asc");
            // 不要用 selectByExample，用selectByExampleWithBLOBs，因为 title、content类型为text，会被当做blob。
            // List<Blog> blogList = blogMapper.selectByExample(blogExample);
            List<Blog> blogList = blogMapper.selectByExampleWithBLOBs(blogExample);
            blogList.forEach( item -> {
                log.info("blog: {}", item);
            });
        }
    }


    @Test
    public void test_04() throws IOException {
        try (SqlSession sqlSession = getSqlSession()){
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

            BlogExample blogExample = new BlogExample();
            blogExample.setOrderByClause("id desc");
            RowBounds rowBounds = new RowBounds(0, 2); // offset 为0， limit 为2
            List<Blog> blogList = blogMapper.selectByExampleWithBLOBsWithRowbounds(blogExample, rowBounds);

            blogList.forEach( item -> {
                log.info("blog: {}", item);
            });
        }
    }


    /**
     * 获取 SqlSession
     */
    private SqlSession getSqlSession() throws IOException {
        SqlSessionFactory sessionFactory;
        sessionFactory = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsReader("mybatis-config.xml"));
        return sessionFactory.openSession();
    }
}