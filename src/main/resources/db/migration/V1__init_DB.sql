-- --users
-- DROP SEQUENCE IF EXISTS user_seq;
-- create sequence user_seq start 1 increment1;
--
-- DROP TABLE IF EXISTS users CASCADE;
-- create table users
--     {
--     id bigint not null,
--     archive boolean not null,
--     email varchar (255),
--     password varchar (255),
--     role varchar (255),
--     bucket_id bigint,
--     primary_key (id)
--     };
--
-- --bucket
-- DROP SEQUENCE IF EXISTS buckets CASCADE;
-- create table buckets
--     {
--     id bigint not null,
--     user_id bigint,
--     primary_key (id)
--     };
--
-- --LINK BETWEEN BUCKET AND USER
-- alter table if exists buckets
--     and constraint buckets_fk_user
--     foreign key (user_id) references users;
--
-- alter table if exists users
--     add constraint users_fk_bucket
--     foreign key (bucket_id) references buckets;
--
-- alter table if exists products_categories
--     add constraint products_categories_fk_category
--     foreign key (category_id) references categories;
--
-- alter table if exists products_categories
--     add constraint products_categories_fk_products
--     foreign key (product_id) references products;
--
-- --PRODUCT IN BUCKET
-- DROP TABLE IF EXISTS buckets_products CASCADE;
-- create table buckets_products
--     {
--     bucket_id bigint not null,
--     product_id bigint not null
--     };
--
-- alter table if exists buckets_products
--     add constraint buckets_products_fk_products
--     foreign key (product_id) references products;
--
-- alter table if exists buckets_products
--     add constraint buckets_products_fk_bucket
--     foreign key (bucket_id) references buckets;
--
-- --ORDERS
-- DROP SEQUENCE IF EXISTS order_seq;
-- create sequence order_seq start 1 increment 1;
--
-- DROP TABLE IF EXISTS orders CASCADE;
-- create table orders
--     {
--     id int8 not null,
--     address varchar(255),
--     changed timestamp,
--     created timestamp,
--     status  varchar(255),
--     sum     numeric(19, 2),
--     user_id bigint,
--     primary key (id)
--     };
--
-- alter table if exists orders
-- add constraint orders_fk_user
-- foreign key (user_id) references users;
--
-- --ORDERS DETAILS
-- DROP SEQUENCE IF EXISTS order_details_seq;
-- create sequence order_details_seq start 1 increment 1;
--
-- DROP TABLE IF EXISTS orders_details CASCADE;
-- create table orders_details
--     {
--     id      int8 not null,
--     amount  numeric(19, 2),
--     price   numeric(19, 2),
--     order_id  bigint,
--     product_id bigint,
--     details_id bigint not null,
--     primary key  (id)
--     };
--
-- alter table if exists orders_details
-- add constraint orders_details_fk_orders
-- foreign key (order_id) references orders;
--
-- alter table if exists orders_details
-- add constraint orders_details_fk_products
-- foreign key (product_id) references products;
--
-- alter table if exists orders_details
-- add constraint orders_details_fk_orders_details
-- foreign key (details_id) references orders_details;
--
-- alter table if exists products_categories
-- add constraint products_categories_fk_categories
-- foreign key (category_id) references categories;
--
-- alter table if exists products_categories
-- add constraint products_categories_fk_products
-- foreign key (product_id) references products;
--
-- alter table if exists users
-- add constraint users_fk_buckets
--  foreign key (bucket_id) references buckets;

--
create sequence bucket_seq start with 1 increment by 1;
create sequence category_seq start with 1 increment by 1;
create sequence order_details_seq start with 1 increment by 1;
create sequence order_seq start with 1 increment by 1;
create sequence product_seq start with 1 increment by 1;
create sequence user_seq start with 1 increment by 1;

create table buckets
(
    id      bigint not null,
    user_id bigint unique,
    primary key (id)
);

create table buckets_products
(
    bucket_id  bigint not null,
    product_id bigint not null
);

create table categories
(
    id    bigint not null,
    title varchar(255),
    primary key (id)
);

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
);

create table orders_details
(
    amount     numeric(38, 2),
    price      numeric(38, 2),
    details_id bigint not null unique,
    id         bigint not null,
    order_id   bigint,
    product_id bigint,
    primary key (id)
);

create table products
(
    price numeric(38, 2),
    id    bigint not null,
    title varchar(255),
    primary key (id)
);

create table products_categories
(
    category_id bigint not null,
    product_id  bigint not null
);

create table users
(
    archive   boolean not null,
--     bucket_id bigint unique,
    id        bigint  not null,
    email     varchar(255),
    name      varchar(255),
    password  varchar(255) not null,
    role      varchar(255) check (role in ('CLIENT', 'MANAGER', 'ADMIN')),
    primary key (id)
);

alter table if exists buckets
    add constraint buckets_fk_users
    foreign key (user_id) references users;

alter table if exists buckets_products
    add constraint buckets_products_fk_products
    foreign key (product_id) references products;

alter table if exists buckets_products
    add constraint buckets_products_fk_buckets
    foreign key (bucket_id) references buckets;

alter table if exists orders
    add constraint orders_fk_users
    foreign key (user_id) references users;

alter table if exists orders_details
    add constraint orders_details_fk_orders
    foreign key (order_id) references orders;

alter table if exists orders_details
    add constraint orders_details_fk_products
    foreign key (product_id) references products;

alter table if exists orders_details
    add constraint orders_details_fk_orders_details
    foreign key (details_id) references orders_details;

alter table if exists products_categories
    add constraint products_categories_fk_categories
    foreign key (category_id) references categories;

alter table if exists products_categories
    add constraint products_categories_fk_products
    foreign key (product_id) references products;

-- alter table if exists users
--     add constraint users_fk_buckets
--     foreign key (bucket_id) references buckets;