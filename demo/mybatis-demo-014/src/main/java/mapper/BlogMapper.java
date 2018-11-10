package mapper;

import bean.Blog;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface BlogMapper {

    /**
     * 获取用户id为ownerId的博客
     * @param ownerId
     * @param limit
     * @param offset
     * @return
     */
List<Blog> findByUserId(Long ownerId, Integer limit, Integer offset);

    /**
     *
     * @param ownerId
     * @param rowBounds
     * @return
     */
    List<Blog> findByUserIdWithRowBounds(Long ownerId, RowBounds rowBounds);

}
