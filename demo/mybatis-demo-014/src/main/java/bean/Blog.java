package bean;

import lombok.Data;

@Data
public class Blog {

    private Long id;
    private Long ownerId;  // 博客作者的用户id
    private String title;
    private String content;

}