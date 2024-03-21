------liquibase formatted sql
------
------changeset aglinka:create_table_user
CREATE TABLE users
(
    ID          INTEGER PRIMARY KEY NOT NULL,
    EMAIL       VARCHAR(255) UNIQUE NOT NULL,
    FIRST_NAME  VARCHAR(255) NOT NULL,
    LAST_NAME   VARCHAR(255) NOT NULL,
    PHONE       VARCHAR(50) NOT NULL,
    ROLE        VARCHAR(255) NOT NULL,
    PASSWORD    VARCHAR(255) NOT NULL,
    REG_DATE    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    AVATAR_ID   INTEGER references image (id) on delete set null
);

------changeset aglinka:insert into users
INSERT INTO users
(ID, EMAIL, FIRST_NAME, LAST_NAME, PHONE, ROLE, PASSWORD, REG_DATE)
VALUES (1, 'user@gmail.com', 'test', 'test', '+79998887766', 'USER', 'password', CURRENT_TIMESTAMP)