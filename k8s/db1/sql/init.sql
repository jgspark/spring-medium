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
    id       bigint not null auto_increment,
    name     varchar(255),
    status   varchar(255),
    stock_id bigint,
    primary key (id)
) engine=InnoDB;

create table stock
(
    id           bigint not null auto_increment,
    count        bigint,
    limit_count  bigint,
    origin_count bigint,
    version      bigint,
    primary key (id)
) engine=InnoDB;

alter table orders
    add constraint FKkp5k52qtiygd8jkag4hayd0qg
        foreign key (product_id)
            references products (id);

alter table products
    add constraint FKspi67gawp2uo9fhpefswcicqb
        foreign key (stock_id)
            references stock (id);


INSERT INTO stock (id, count, limit_count, origin_count, version)
    value (1, 0, 10, 500, 0);

INSERT INTO products (id, name, status, stock_id)
VALUES (1, 'test', 'SALE', 1);

