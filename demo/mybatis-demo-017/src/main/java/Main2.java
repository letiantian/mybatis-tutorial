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
public class Main2 {

    @Test
    public void test_01() throws IOException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = sqlSessionFactoryBuilder.build(
                Resources.getResourceAsReader("mybatis-config.xml"),
                "development"
        );
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            log.info("{}", studentMapper.findById(1L));
            log.info("{}", studentMapper.findById(2L));
        }
    }

}