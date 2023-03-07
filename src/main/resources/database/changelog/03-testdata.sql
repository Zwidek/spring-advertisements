-- liquibase formatted sql
-- changeset zwidek:13

INSERT INTO
    application_user (first_name, last_name, username, email, phone_number, password, enabled)
VALUES
    ('Zwidek','Jan', 'Kowalski', 'admin2', '997', '{noop}admin', true),
    ('Grzyb','John', 'Abacki', 'user2', '999', '{noop}admin', true);

INSERT INTO
    user_roles (user_id, role_id)
VALUES
    (3, 1),
    (4, 2);