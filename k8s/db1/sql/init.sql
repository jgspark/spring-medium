create table orders
(
    id         bigint not null auto_increment,
    content    varchar(255),
    status     varchar(255),
    product_id bigint not null,
    primary key (id)
) engine=InnoDB;
create table products
(
    id     bigint not null auto_increment,
    name   varchar(255),
    status varchar(255),
    primary key (id)
) engine=InnoDB;
create table stock
(
    id           bigint not null auto_increment,
    count        bigint,
    limit_count  bigint,
    origin_count bigint,
    version      bigint,
    product_id   bigint,
    primary key (id)
) engine=InnoDB;

alter table orders
    add constraint FKkp5k52qtiygd8jkag4hayd0qg foreign key (product_id) references products (id);

alter table stock
    add constraint FKeuiihog7wq4cu7nvqu7jx57d2 foreign key (product_id) references products (id);
