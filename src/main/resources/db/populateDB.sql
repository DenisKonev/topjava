DELETE
FROM user_role;
DELETE
FROM users;
DELETE
FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (name, description, dateTime, calories)
VALUES ('Завтрак', 'Breakfast', '2020-01-30 10:00:00', 500),
       ('Обед', 'Lunch', '2020-01-30 13:00:00', 1000),
       ('Ужин', 'Dinner', '2020-01-30 20:00:00', 500),
       ('Еда на граничное значение', 'Boundary value food', '2020-01-31 00:00:00', 100),
       ('Завтрак', 'Breakfast', '2020-01-31 10:00:00', 1000),
       ('Обед', 'Lunch', '2020-01-31 13:00:00', 500),
       ('Ужин', 'Dinner', '2020-01-31 20:00:00', 410);