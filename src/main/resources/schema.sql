CREATE TABLE IF NOT EXISTS USERS (
    username VARCHAR(250) NOT NULL primary key,
    email VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL
);