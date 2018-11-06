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
    public void test_01() throws IOException {
        SqlSession sqlSession = getSqlSession(false);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(2L);
        user.setPassword("123456");

        try {
            int result = userMapper.updateUserPasswordById(user);
            log.info("result: {}, user: {}", result, user);
            sqlSession.rollback();  // 回滚。若之前没有insert、delete、update，则不进行回滚
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test_02() throws IOException {
        SqlSession sqlSession = getSqlSession(false);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(2L);
        user.setPassword("123456");

        try {
            int result = userMapper.updateUserPasswordById(user);
            log.info("result: {}, user: {}", result, user);
            sqlSession.rollback(true); // 强制回滚
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test_03() throws IOException {
        SqlSession sqlSession = getSqlSession(false);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(2L);
        user.setPassword("123456");

        try {
            int result = userMapper.updateUserPasswordById(user);
            log.info("result: {}, user: {}", result, user);
            sqlSession.commit();  // 提交
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test_04() throws IOException {
        SqlSession sqlSession = getSqlSession(false);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(2L);
        user.setPassword("456789");

        try {
            int result = userMapper.updateUserPasswordById(user);
            log.info("result: {}, user: {}", result, user);
            sqlSession.commit(true); // 强制提交
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