package dao;

import bean.User;

public interface UserMapper {

    User findById(int id);

    int insertUserV1(User user);
    int insertUserV2(User user);

    int deleteById(int id);
    int deleteByMinId(int minId); // 删除id大于等于minId的所有用户

}