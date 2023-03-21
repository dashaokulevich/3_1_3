-- ROLE_ADMIN.id = 1
-- ROLE_USER.id = 2
INSERT INTO role (authority)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER');

-- пароль админа == "password"
-- пароль юзера == "password"
INSERT INTO user (username, password, email)
VALUES ('admin', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', 'admin@mail.ru'),
       ('user', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', 'user@mail.ru');


INSERT INTO user_role (user_id, role_id)
VALUES (1, 1),
       (1, 2),
       (2, 2);


