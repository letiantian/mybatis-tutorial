import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;
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
    public void test_findById() throws IOException {
        try ( SqlSession sqlSession = getSqlSession() ) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.findById(1L);
            log.info("{}", user);
        }
    }

    @Test
    public void test_findByNameAndPasswordV1() throws IOException {
        try ( SqlSession sqlSession = getSqlSession() ) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.findByNameAndPasswordV1("letian", "123");
            log.info("{}", user);
        }
    }

    @Test
    public void test_findByNameAndPasswordV2() throws IOException {
        try ( SqlSession sqlSession = getSqlSession() ) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.findByNameAndPasswordV2("letian", "123");
            log.info("{}", user);
        }
    }

    @Test
    public void test_findByNameAndPasswordV3() throws IOException {
        try ( SqlSession sqlSession = getSqlSession() ) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Map<String, Object> data = new HashMap<>();
            data.put("username", "letian");
            data.put("password", "123");
            User user = userMapper.findByNameAndPasswordV3(data);
            log.info("{}", user);
        }
    }

    @Test
    public void test_findByNameAndPasswordV4() throws IOException {
        try ( SqlSession sqlSession = getSqlSession() ) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Map<String, Object> data = new HashMap<>();
            data.put("username", "letian");
            User user = userMapper.findByNameAndPasswordV4(data, "123");
            log.info("{}", user);
        }
    }

    @Test
    public void test_findByNameAndPasswordV5() throws IOException {
        try ( SqlSession sqlSession = getSqlSession() ) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Map<String, Object> data = new HashMap<>();
            data.put("username", "letian");
            User user = userMapper.findByNameAndPasswordV5(data, "123");
            log.info("{}", user);
        }
    }

    @Test
    public void test_findByNameAndPasswordV6() throws IOException {
        try ( SqlSession sqlSession = getSqlSession() ) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User queryUser = new User();
            queryUser.setName("letian");
            queryUser.setPassword("123");
            User user = userMapper.findByNameAndPasswordV6(queryUser);
            log.info("{}", user);
        }
    }

    @Test
    public void test_findByNameAndPasswordV7() throws IOException {
        try ( SqlSession sqlSession = getSqlSession() ) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User queryUser = new User();
            queryUser.setName("letian");
            queryUser.setPassword("123");
            User user = userMapper.findByNameAndPasswordV7(queryUser);
            log.info("{}", user);
        }
    }

    /**
     * 获取 SqlSession
     * @return
     * @throws IOException
     */
    private SqlSession getSqlSession() throws IOException {
        SqlSessionFactory sessionFactory;
        sessionFactory = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsReader("mybatis-config.xml"));
        return sessionFactory.openSession();
    }
}