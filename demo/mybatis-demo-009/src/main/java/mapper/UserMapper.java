package mapper;

import bean.User;

public interface UserMapper {

    /**
     * 根据 id 查询用户
     */
    User findById(Long id);

    /**
     * 添加用户
     * @return 影响的行数
     */
    int insertUser(User user);

    /**
     * 根据 id 更新密码
     * @return 影响的行数
     */
    int updateUserPasswordById(User user);

    /**
     * 删除指定id的记录
     * @return 影响的行数
     */
    int deleteById(Long id);

    /**
     * 删除id在 [minId, maxId] 范围内的记录
     * @return 影响的行数
     */
    int deleteByIdRange(Long minId, Long maxId);

}