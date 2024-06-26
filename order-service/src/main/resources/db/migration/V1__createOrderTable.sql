create database if not exists order_db_dev;
create table orders (
    id bigint not null AUTO_INCREMENT,
    order_number varchar(255) default null,
    sku_code varchar(255),
    price decimal(19, 2),
    quantity int not null,
    PRIMARY KEY (id)
);