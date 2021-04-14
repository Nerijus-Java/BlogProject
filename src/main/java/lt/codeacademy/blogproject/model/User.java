package lt.codeacademy.blogproject.model;

import lombok.Data;

import java.util.UUID;

@Data
public class User {

    private UUID userID;
    private String username;
    private String password;
    private String role;
}
