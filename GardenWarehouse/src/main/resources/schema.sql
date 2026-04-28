create sequence if not exists item_seq start with 1 increment by 1;

create table if not exists item
(
    id identity,
    code varchar(6) not null,
    type varchar(50) not null,
    name varchar(50) not null,
    price double not null,
    item_count int
);