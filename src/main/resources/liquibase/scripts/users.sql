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
    REG_DATE    TIMESTAMP NOT NULL,
    AVATAR_ID   INTEGER references image (id) on delete set null
);
