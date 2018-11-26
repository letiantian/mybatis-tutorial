import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import mapper.StudentMapper;
import mapper.TeacherMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import org.junit.Test;


@Slf4j
public class Main {

    @Test
    public void test_01() throws IOException {
        try (SqlSession sqlSession = getSqlSession()) {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            log.info("{}", studentMapper.findById(1L));
            log.info("{}", studentMapper.findById(2L));
        }
    }

    @Test
    public void test_02() throws IOException {
        try (SqlSession sqlSession = getSqlSession()) {
            TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
            log.info("{}", teacherMapper.findById(1L));
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