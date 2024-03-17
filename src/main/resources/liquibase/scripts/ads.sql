------liquibase formatted sql
------
------ changeset elebedeva:1
CREATE TABLE  IF NOT EXISTS ADS
(
    ID INTEGER generated by default as identity primary key,
    PRICE INTEGER,
    TITLE VARCHAR,
    USER_ID   INTEGER references users (id) on delete set null,
    IMAGE_ID INTEGER references image (id) on delete set null

) ;