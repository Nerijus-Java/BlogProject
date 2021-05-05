


INSERT INTO USERS (userid,username,password) VALUES
    ('0aa8e6ce-8538-46a5-8209-1ca1090a0c52','user1','{bcrypt}$2a$10$8rr6Sf8b7f5/ElLvPJU6X.EGrE7fhLyqabKZ1vlATENr9gTdEKm0K'),
    ('b65c446e-cd05-4467-aa97-e4e33a91da06','user2','{bcrypt}$2y$10$Pr.rsEzCazbfG0KbbTbRUed6rFhDjjvMjqT61dSw63mQEz/KKfPWq');

INSERT INTO BLOGS (blogid,description,title,blog_id_user) VALUES
    ('c758b798-a969-488f-89a4-c80b51453520','description','title','0aa8e6ce-8538-46a5-8209-1ca1090a0c52');

INSERT INTO COMMENTS(commentid,description,comment_id_blog,comment_id_user) VALUES
    ('545a4b98-fe3e-4ab8-a9e9-97a7a5926425','random comment','c758b798-a969-488f-89a4-c80b51453520','b65c446e-cd05-4467-aa97-e4e33a91da06');

INSERT INTO ROLES (id,role) VALUES
    ('0aca59a2-7983-42fd-8c9c-aa7baf0ccd4c', 'USER'),
    ('3e156dfc-5dd5-4f6d-9f5c-7993dff721d7', 'ADMIN');

INSERT INTO USERS_ROLES(user_userid,roles_id) VALUES
    ('0aa8e6ce-8538-46a5-8209-1ca1090a0c52','0aca59a2-7983-42fd-8c9c-aa7baf0ccd4c'),
    ('0aa8e6ce-8538-46a5-8209-1ca1090a0c52','3e156dfc-5dd5-4f6d-9f5c-7993dff721d7'),
    ('b65c446e-cd05-4467-aa97-e4e33a91da06','0aca59a2-7983-42fd-8c9c-aa7baf0ccd4c');