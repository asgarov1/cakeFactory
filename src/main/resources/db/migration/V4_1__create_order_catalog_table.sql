create table IF NOT EXISTS order_catalog (
    order_id serial not null,
    catalog_id VARCHAR(10) not null,
    CONSTRAINT order_catalog_table_fk_order FOREIGN KEY(order_id) REFERENCES orders(id),
    CONSTRAINT order_catalog_table_fk_catalog FOREIGN KEY(catalog_id) REFERENCES catalog(item_code)
);