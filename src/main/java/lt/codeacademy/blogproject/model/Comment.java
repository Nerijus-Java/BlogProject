package lt.codeacademy.blogproject.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Comment {

    private UUID commentID;
    private String description;
    private UUID userID;
    private UUID blogID;
}
