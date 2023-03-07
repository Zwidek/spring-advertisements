-- liquibase formatted sql
-- changeset zwidek:14

INSERT INTO
    application_user (first_name, last_name, username, email, phone_number, password, enabled)
VALUES
    ('Zwidek','Jan', 'Kowalski', 'admin', '997', 'admin', true),
    ('Grzyb','John', 'Abacki', 'user', '999', 'user', true);

INSERT INTO
    user_role (name, description)
VALUES
    ('ADMIN', 'Ma dostęp do wszystkiego'),
    ('USER', 'Dostęp  do ogloszen');

INSERT INTO
    user_roles (user_id, role_id)
VALUES
    (1, 1),
    (2, 2);