import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
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
        SqlSession sqlSession = getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findOneUserByPassword("1234");
        log.info("{}", user);
    }

    @Test
    public void test_02() throws IOException {
        SqlSession sqlSession = getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findOneUserByPassword("123");
        log.info("{}", user);
    }

    @Test
    public void test_03() throws IOException {
        SqlSession sqlSession = getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.findByPassword("123");
        log.info("{}", userList);
    }

    @Test
    public void test_04() throws IOException {
        SqlSession sqlSession = getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.findByPassword("1234");
        log.info("{}", userList);
    }


    private SqlSession getSqlSession() throws IOException {
        SqlSessionFactory sessionFactory;
        sessionFactory = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsReader("mybatis-config.xml"));
        return sessionFactory.openSession();
    }

}