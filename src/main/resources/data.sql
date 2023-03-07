INSERT INTO application_user (first_name, last_name, username, email, phone_number, password, enabled)
VALUES ('Jan', 'Kowalski', 'Jan', 'admin', '997', '{noop}admin', true),
       ('John', 'Nowak', 'John', 'user', '999', '{noop}admin', true);

INSERT INTO user_role (name, description)
VALUES ('ADMIN', 'Ma dostęp do wszystkiego'),
       ('USER', 'Dostęp  do ogloszen');

INSERT INTO user_roles (user_id, user_role_id)
VALUES (1, 1),
       (2, 2);

INSERT INTO category(name, description)
VALUES ('Nauka', 'Wszystko o edukacji'),
       ('Warzywa', 'Wszystko o zwierzętach'),
       ('Owoce', 'Wszystko o owocach');

INSERT INTO advertisement(title, url, price, description, date_added, user_id, category_id, view_counter)
VALUES ('Ksiazka o programowaniu', 'allegro.pl', 100.99, 'Dedykowana dla osób przebranżawiających się', '2021-01-05', 1,
        1, 0),
       ('Marchewka', 'allegro.pl', 2.99, 'Ma karoten', '2021-03-15', 2, 2, 0),
       ('Pomidor', 'allegro.pl', 12.99, 'Czerwony', '2022-09-01', 2, 2, 0),
       ('Ogórek', 'allegro.pl', 13.99, 'Zielony', '2022-10-15', 2, 2, 0),
       ('Cebula', 'allegro.pl', 4.99, 'Piecze w oczy', '2022-11-15', 2, 2, 0),
       ('Burak', 'allegro.pl', 10.00, 'Czerwony', '2022-12-31', 2, 2, 0),
       ('Pietruszka', 'allegro.pl', 6.50, 'Zielona', '2023-01-15', 2, 2, 0),
       ('Jablko', 'allegro.pl', 4.99, 'Champion', '2023-01-15', 2, 3, 0),
       ('Malina', 'allegro.pl', 21.99, 'Słodka', '2023-02-15', 2, 3, 0);

