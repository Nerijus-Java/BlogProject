
INSERT INTO USERS (userid,username,password) VALUES
    ('0aa8e6ce-8538-46a5-8209-1ca1090a0c52','user1','{bcrypt}$2a$10$8rr6Sf8b7f5/ElLvPJU6X.EGrE7fhLyqabKZ1vlATENr9gTdEKm0K'),
    ('cfeb81a2-5ca3-4fbc-afd8-57edc507650b','admin2','{bcrypt}$2a$10$8rr6Sf8b7f5/ElLvPJU6X.EGrE7fhLyqabKZ1vlATENr9gTdEKm0K'),
    ('b65c446e-cd05-4467-aa97-e4e33a91da06','admin1','{bcrypt}$2y$10$Pr.rsEzCazbfG0KbbTbRUed6rFhDjjvMjqT61dSw63mQEz/KKfPWq');

INSERT INTO BLOGS (blogid,description,title,blog_id_user) VALUES
    ('c758b798-a969-488f-89a4-c80b51453520','description','title','b65c446e-cd05-4467-aa97-e4e33a91da06'),
    ('bef7307c-ca54-4c32-9b66-a39eb38b883b','description','something','b65c446e-cd05-4467-aa97-e4e33a91da06'),
    ('d43f5e9b-43a8-4128-91a3-a221abad2237','description','one more thing','cfeb81a2-5ca3-4fbc-afd8-57edc507650b'),
    ('463a7ce7-c676-4886-81d2-6b71c29d82aa','description','test','b65c446e-cd05-4467-aa97-e4e33a91da06'),
    ('dac1bd29-8eaa-4eb6-9575-5dcec2bac5e4','description','my blog','b65c446e-cd05-4467-aa97-e4e33a91da06'),
    ('1657e213-d34a-463f-8499-9986c92d6945','description','wow','cfeb81a2-5ca3-4fbc-afd8-57edc507650b'),
    ('608c6f2b-b066-4f51-ad78-66c1a3cfc8c7','description','title','b65c446e-cd05-4467-aa97-e4e33a91da06'),
    ('e45de124-1838-49c9-8adf-97c645b6ae38','description','title','b65c446e-cd05-4467-aa97-e4e33a91da06'),
    ('de2a2a53-e654-4f78-867d-d79350565d15','description','title','cfeb81a2-5ca3-4fbc-afd8-57edc507650b'),
    ('ba23802a-8899-466b-bc17-24d530a9ac10','description','title','b65c446e-cd05-4467-aa97-e4e33a91da06'),
    ('0a010a9c-8554-4bcb-9ced-fa70e134df54','description','title','cfeb81a2-5ca3-4fbc-afd8-57edc507650b'),
    ('59594105-a28e-4fbe-a9cd-916ae043f172','description','title','b65c446e-cd05-4467-aa97-e4e33a91da06');

INSERT INTO COMMENTS(commentid,description,comment_id_blog,comment_id_user) VALUES
    ('545a4b98-fe3e-4ab8-a9e9-97a7a5926425','random comment','c758b798-a969-488f-89a4-c80b51453520','b65c446e-cd05-4467-aa97-e4e33a91da06');

INSERT INTO ROLES (id,role) VALUES
    ('0aca59a2-7983-42fd-8c9c-aa7baf0ccd4c', 'USER'),
    ('3e156dfc-5dd5-4f6d-9f5c-7993dff721d7', 'ADMIN');

INSERT INTO USERS_ROLES(user_userid,roles_id) VALUES
    ('0aa8e6ce-8538-46a5-8209-1ca1090a0c52','0aca59a2-7983-42fd-8c9c-aa7baf0ccd4c'),
    ('b65c446e-cd05-4467-aa97-e4e33a91da06','0aca59a2-7983-42fd-8c9c-aa7baf0ccd4c'),
    ('b65c446e-cd05-4467-aa97-e4e33a91da06','3e156dfc-5dd5-4f6d-9f5c-7993dff721d7'),
    ('cfeb81a2-5ca3-4fbc-afd8-57edc507650b','0aca59a2-7983-42fd-8c9c-aa7baf0ccd4c'),
    ('cfeb81a2-5ca3-4fbc-afd8-57edc507650b','3e156dfc-5dd5-4f6d-9f5c-7993dff721d7');