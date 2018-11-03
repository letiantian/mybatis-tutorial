package bean;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String username; // 数据库字段是 name
    private String email;
    private String password;

}
