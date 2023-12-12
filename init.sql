CREATE DATABASE redis_cache;

\c redis_login;

create table "users" (
    id varchar(255) not null,
    password varchar(255),
    username varchar(255),
    deleted boolean,
    primary key (id)
);
