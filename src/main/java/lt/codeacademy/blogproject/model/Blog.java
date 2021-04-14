package lt.codeacademy.blogproject.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Blog {

    private UUID blogID;
    private String title;
    private String description;
    private UUID userID;
}
