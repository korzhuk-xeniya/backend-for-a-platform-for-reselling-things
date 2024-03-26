------liquibase formatted sql
------
CREATE TABLE IF NOT EXISTS users
(
    ID          SERIAL PRIMARY KEY NOT NULL,
    EMAIL      VARCHAR(255) UNIQUE,
    PASSWORD  VARCHAR(255) NOT NULL,
    ROLE        VARCHAR(255) NOT NULL,
    FIRST_NAME VARCHAR(255) NOT NULL,
    LAST_NAME   VARCHAR(255) NOT NULL,
    PHONE       VARCHAR(50) NOT NULL,
    REG_DATE    TIMESTAMP NOT NULL,
    AVATAR_ID   INTEGER REFERENCES image(id) ON DELETE SET NULL
);
--ALTER TABLE users ADD COLUMN username VARCHAR(255);
--ALTER TABLE users ADD COLUMN enabled BOOLEAN DEFAULT true;










