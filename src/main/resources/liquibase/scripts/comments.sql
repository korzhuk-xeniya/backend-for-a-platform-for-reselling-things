------liquibase formatted sql
------
------changeset kkorzhuk:1
CREATE TABLE comment
(
    ID           INTEGER generated by default as identity primary key,
    CREATED_AT TIMESTAMP,
    TEXT        VARCHAR

    ADS_ID references ads (id) on delete set null,
    USER_ID BIGINT references user (id) on delete set null
);
