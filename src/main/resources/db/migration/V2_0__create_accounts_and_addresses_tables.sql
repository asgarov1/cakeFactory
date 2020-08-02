CREATE TABLE IF NOT EXISTS accounts (
    email VARCHAR(40) primary key,
    password VARCHAR(255),
    first_name VARCHAR(20),
    last_name VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS addresses (
    id serial primary key,
    email VARCHAR(40),
    address_line VARCHAR(50),
    country VARCHAR(20),
    city VARCHAR(20),
    zip VARCHAR(20)
);