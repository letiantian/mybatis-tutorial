package mapper;

import bean.User;

public interface UserMapper {

    /**
     * 根据 id 查询用户
     * @param id
     * @return
     */
    User findById(Long id);

}