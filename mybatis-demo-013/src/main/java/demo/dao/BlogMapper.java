package demo.dao;

import demo.model.Blog;
import demo.model.BlogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BlogMapper {
    long countByExample(BlogExample example);

    int deleteByExample(BlogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Blog record);

    int insertSelective(Blog record);

    List<Blog> selectByExampleWithBLOBsWithRowbounds(BlogExample example, RowBounds rowBounds);

    List<Blog> selectByExampleWithBLOBs(BlogExample example);

    List<Blog> selectByExampleWithRowbounds(BlogExample example, RowBounds rowBounds);

    List<Blog> selectByExample(BlogExample example);

    Blog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Blog record, @Param("example") BlogExample example);

    int updateByExampleWithBLOBs(@Param("record") Blog record, @Param("example") BlogExample example);

    int updateByExample(@Param("record") Blog record, @Param("example") BlogExample example);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKeyWithBLOBs(Blog record);

    int updateByPrimaryKey(Blog record);
}