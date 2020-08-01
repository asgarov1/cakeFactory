CREATE TABLE IF NOT EXISTS catalog
(
    item_code VARCHAR(10) PRIMARY KEY,
    title     VARCHAR(20),
    price     numeric
);