--liquibase formatted sql

--changeset ibalobanova:1
ALTER table users_aud
DROP CONSTRAINT users_aud_username_key;

--changeset ibalobanova:2
ALTER TABLE users_aud
ALTER COLUMN username DROP NOT NULL;

