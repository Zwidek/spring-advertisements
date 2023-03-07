-- liquibase formatted sql
-- changeset kjurczyk:12

CREATE TABLE application_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20),
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE user_role (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        varchar(30)  NOT NULL,
    description varchar(200) NOT NULL
);

CREATE TABLE user_roles (
    id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES application_user(id),
    FOREIGN KEY (role_id) REFERENCES user_role(id)
);

CREATE TABLE item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    item_name VARCHAR(100) NOT NULL,
    price DECIMAL NOT NULL,
    item_description VARCHAR(500) NOT NULL,
    added_date TIMESTAMP NOT NULL,
    user_id BIGINT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES application_user(id)
);

CREATE TABLE category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(50) NOT NULL
);

CREATE TABLE item_categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    item_id     BIGINT NOT NULL,
    category_id BIGINT NOT NULL,

    FOREIGN KEY (item_id) REFERENCES item (id),
    FOREIGN KEY (category_id) REFERENCES category (id)
);