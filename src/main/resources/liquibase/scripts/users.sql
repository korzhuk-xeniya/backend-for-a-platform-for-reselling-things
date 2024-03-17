------liquibase formatted sql
------
------changeset aglinka:create_table_user
CREATE TABLE users
(
    ID          INTEGER PRIMARY KEY NOT NULL,
    EMAIL       VARCHAR,
    FIRST_NAME  VARCHAR,
    LAST_NAME   VARCHAR,
    PHONE       VARCHAR,
    ROLE        INTEGER,
    PASSWORD    VARCHAR,
    REG_DATE    TIMESTAMP,
    AVATAR_ID   INTEGER references image (id) on delete set null
);
