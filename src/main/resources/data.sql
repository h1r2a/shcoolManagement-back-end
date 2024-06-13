
INSERT INTO role (name) VALUES ('ROLE_USER');
INSERT INTO role (name) VALUES ('ROLE_ADMIN');


INSERT INTO user (email, password, role_id) VALUES ('teacher@example.com', 'password', (SELECT id FROM role WHERE name='ROLE_USER'));


INSERT INTO teacher (first_name, last_name, phone_number, user_id)
VALUES ('John', 'Doe', '1234567890', (SELECT id FROM user WHERE email='teacher@example.com'));
