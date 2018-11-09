package bean;

import lombok.Data;

import java.util.List;

@Data
public class User {

    private Long id;
    private String name;
    private String email;
    private String password;
    private List<Blog> blogs; // 属于该用户的博客

}
