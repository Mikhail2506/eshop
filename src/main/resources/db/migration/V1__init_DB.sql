--users
DROP SEQUENCE IF EXISTS user_seq;
create sequence user_seq start 1 increment1;

DROP TABLE IF EXISTS users CASCADE;
create table users
    {
    id int8 not null,
    archive boolean not null,
    email varchar (255),
    password varchar (255),
    role varchar (255),
    bucket_id int8,
    primary_key (id)
    };

--bucket
DROP SEQUENCE IF EXISTS buckets CASCADE;
create table buckets
    {
    id int8 not null,
    user_id int8,
    primary_key (id)
    };

--LINK BETWEEN BUCKET AND USER
alter table if exists buckets
    and constraint buckets_fk_user
    foreign key (user_id) references users;

alter table if exists users
    add constraint users_fk_bucket
    foreign key (bicket_id) referebces buckets;

alter table if exists products_categories
    add constraint products_categories_fk_category
    foreign key (category_id) references categories;

alter table if exists products_categories
    add constraint products_categories_fk_products
    foreign key (product_id) references products;

--PRODUCT IN BUCKET
DROP TABLE IF EXISTS buckets_products CASCADE;
create table buckets_products
    {
    bucket_id int8 not null,
    product_id int8 not null
    };

alter table if exists buckets_products
    add constraint buckets_products_fk_products
    foreign key (product_id) references products;

alter table if exists buckets_products
    add constraint buckets_products_fk_bucket
    foreign key (bucket_id) references buckets;

--ORDERS
DROP SEQUENCE IF EXISTS order_seq;
create sequence order_seq start 1 increment 1;

DROP TABLE IF EXISTS orders CASCADE;
create table orders
    {
    id int8 not null,
    address varchar(255),
    changed timestamp,
    created timestamp,
    status  varchar(255),
    sum     numeric(19, 2),
    user_id int8,
    primary key (id)
    };

alter table if exists orders
add constraint orders_fk_user
foreign key (user_id) references users;

--ORDERS DETAILS
DROP SEQUENCE IF EXISTS order_details_seq;
create sequence order_details_seq start 1 increment 1;

DROP TABLE IF EXISTS orders_details CASCADE;
create table orders_details
    {
    id      int8 not null,
    amount  numeric(19, 2),
    price   numeric(19, 2),
    order_id  int8,
    product_id int8,
    details_id int8 not null,
    primary key  (id)
    };

alter table if exists orders_details
add constraint orders_details_fk_prder
foreign key (order_id) references orders;

alter table if exists orders_details
add constraint orders_details_fk_products
foreign key (product_id) references products,



create sequence bucket_seq start with 1 increment by 1 Hibernate:
create sequence category_seq start with 1 increment by 1 Hibernate:
create sequence order_details_seq start with 1 increment by 1 Hibernate:
create sequence order_seq start with 1 increment by 1 Hibernate:
create sequence product_seq start with 1 increment by 1 Hibernate:
create sequence user_seq start with 1 increment by 1 Hibernate:
create table buckets
(
    id      bigint not null,
    user_id bigint unique,
    primary key (id)
) Hibernate:
create table buckets_products
(
    bucket_id  bigint not null,
    product_id bigint not null
) Hibernate:
create table categories
(
    id    bigint not null,
    title varchar(255),
    primary key (id)
) Hibernate:
create table orders
(
    sum     numeric(38, 2),
    created timestamp(6),
    id      bigint not null,
    updated timestamp(6),
    user_id bigint,
    address varchar(255),
    status  varchar(255) check (status in ('NEW', 'APPROVED', 'CANCELED', 'PAID', 'CLOSED')),
    primary key (id)
) Hibernate:
create table orders_details
(
    amount     numeric(38, 2),
    price      numeric(38, 2),
    details_id bigint not null unique,
    id         bigint not null,
    order_id   bigint,
    product_id bigint,
    primary key (id)
) Hibernate:
create table products
(
    price numeric(38, 2),
    id    bigint not null,
    title varchar(255),
    primary key (id)
) Hibernate:
create table products_categories
(
    category_id bigint not null,
    product_id  bigint not null
) Hibernate:
create table users
(
    archive   boolean not null,
    bucket_id bigint unique,
    id        bigint  not null,
    email     varchar(255),
    name      varchar(255),
    password  varchar(255),
    role      varchar(255) check (role in ('CLIENT', 'MANAGER', 'ADMIN')),
    primary key (id)
) Hibernate:
alter table if exists buckets add constraint FKnl0ltaj67xhydcrfbq8401nvj foreign key (user_id) references users
    Hibernate:
alter table if exists buckets_products add constraint FKloyxdc1uy11tayedf3dpu9lci foreign key (product_id) references products
    Hibernate:
alter table if exists buckets_products add constraint FKc49ah45o66gy2f2f4c3os3149 foreign key (bucket_id) references buckets
    Hibernate:
alter table if exists orders add constraint FK32ql8ubntj5uh44ph9659tiih foreign key (user_id) references users
    Hibernate:
alter table if exists orders_details add constraint FK5o977kj2vptwo70fu7w7so9fe foreign key (order_id) references orders
    Hibernate:
alter table if exists orders_details add constraint FKs0r9x49croribb4j6tah648gt foreign key (product_id) references products
    Hibernate:
alter table if exists orders_details add constraint FKgvp1k7a3ubdboj3yhnawd5m1p foreign key (details_id) references orders_details
    Hibernate:
alter table if exists products_categories add constraint FKqt6m2o5dly3luqcm00f5t4h2p foreign key (category_id) references categories
    Hibernate:
alter table if exists products_categories add constraint FKtj1vdea8qwerbjqie4xldl1el foreign key (product_id) references products
    Hibernate:
alter table if exists users add constraint FK8l2qc4c6gihjdyoch727guci foreign key (bucket_id) references buckets