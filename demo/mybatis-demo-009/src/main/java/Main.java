import java.io.IOException;

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
    public void test_insertUser() throws IOException {
        SqlSession sqlSession = getSqlSession(false);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setName("xiaowei");
        user.setEmail("xiaowei@111.com");
        user.setPassword("456");

        try {
            int result = userMapper.insertUser(user);
            log.info("result: {}, user: {}", result, user);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test_updateUserPasswordById() throws IOException {
        SqlSession sqlSession = getSqlSession(false);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(3L);
        user.setPassword("123456");

        try {
            int result = userMapper.updateUserPasswordById(user);
            log.info("result: {}, user: {}", result, user);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test_deleteById() throws IOException {
        SqlSession sqlSession = getSqlSession(false);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        try {
            int result = userMapper.deleteById(3L);
            log.info("result: {}", result);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test_deleteByIdRange() throws IOException {
        SqlSession sqlSession = getSqlSession(false);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        try {
            int result = userMapper.deleteByIdRange(1L, 2L);
            log.info("result: {}", result);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }


    private SqlSession getSqlSession(boolean autoCommit) throws IOException {
        SqlSessionFactory sessionFactory;
        sessionFactory = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsReader("mybatis-config.xml"));
        return sessionFactory.openSession(autoCommit);
    }

}