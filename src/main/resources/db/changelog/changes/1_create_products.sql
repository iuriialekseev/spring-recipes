--liquibase formatted sql

--changeset iurii:1
CREATE TABLE products
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255),
    unit VARCHAR(10)
);
