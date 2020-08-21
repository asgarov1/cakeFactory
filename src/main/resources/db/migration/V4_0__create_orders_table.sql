CREATE TABLE IF NOT EXISTS orders (
    id serial primary key,
    placedOrder TIMESTAMP,
    address_id serial
);

alter table orders add constraint orders_table_fk_address foreign key (address_id) references addresses;