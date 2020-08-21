create table IF NOT EXISTS user_role (
    email varchar(255) not null,
    roles varchar(255)
);

alter table user_role add constraint FKq8m2csl2twsrg8vw5dtq7ui0y foreign key (email) references accounts;