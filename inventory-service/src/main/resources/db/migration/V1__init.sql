create table inventory (
  id bigint not null auto_increment,
  sku_code varchar(255) default null,
  quantity int default null,
  PRIMARY KEY (id)
);