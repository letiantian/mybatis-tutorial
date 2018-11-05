import java.io.IOException;
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
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.findByName("letian", null);
            log.info("{}", user);
        }
    }

    @Test
    public void test_02() throws IOException {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.findByName("letian", "1234");
            log.info("{}", user);
        }
    }

    @Test
    public void test_03() throws IOException {
        try (SqlSession sqlSession = getSqlSession()) {
            User queryCondition = new User();
            queryCondition.setId(1L);
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userMapper.find(queryCondition);
            log.info("{}", userList);
        }
    }

    @Test
    public void test_04() throws IOException {
        try (SqlSession sqlSession = getSqlSession()) {
            User queryCondition = new User();
            queryCondition.setPassword("123");
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userMapper.find(queryCondition);
            log.info("{}", userList);
        }
    }

    @Test
    public void test_05() throws IOException {
        try (SqlSession sqlSession = getSqlSession()) {
            User queryCondition = new User();
            queryCondition.setName("letian");
            queryCondition.setPassword("123");
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userMapper.find(queryCondition);
            log.info("{}", userList);
        }
    }

    private SqlSession getSqlSession() throws IOException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory;

        sessionFactory = sqlSessionFactoryBuilder.build(
                Resources.getResourceAsReader("mybatis-config.xml"),
                "development"
        );
        return sessionFactory.openSession();
    }

}