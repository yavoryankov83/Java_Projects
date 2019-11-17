create schema beer_tag;

use beer_tag;


create table breweries
(
    id   int auto_increment
        primary key,
    name varchar(50) charset utf8 not null,
    constraint breweries_name_uindex
        unique (name)
);

create table origin_countries
(
    id   int auto_increment
        primary key,
    name varchar(50) charset utf8 not null,
    constraint origin_countries_name_uindex
        unique (name)
);

create table status_values
(
    id           int auto_increment
        primary key,
    status_value varchar(20) not null
);

create table styles
(
    id   int auto_increment
        primary key,
    name varchar(50) charset utf8 not null,
    constraint styles_name_uindex
        unique (name)
);

create table beers
(
    id          int auto_increment
        primary key,
    name        varchar(50) charset utf8 not null,
    abv         double(2, 1)             not null,
    description text                     null,
    photo       longblob                 null,
    style_id    int                      null,
    country_id  int                      null,
    brewery_id  int                      null,
    constraint beers_breweries_fk
        foreign key (brewery_id) references breweries (id),
    constraint beers_breweries_id_fk
        foreign key (brewery_id) references breweries (id),
    constraint beers_origin_countries_fk
        foreign key (country_id) references origin_countries (id),
    constraint beers_styles_fk
        foreign key (style_id) references styles (id)
);

create table tags
(
    id   int auto_increment
        primary key,
    name varchar(50) charset utf8 not null,
    constraint tags_name_uindex
        unique (name)
);

create table beers_tags
(
    beer_id int not null,
    tag_id  int not null,
    primary key (beer_id, tag_id),
    constraint beers_tags_beer_id_fk
        foreign key (beer_id) references beers (id),
    constraint beers_tags_tag_id_fk
        foreign key (tag_id) references tags (id)
);

create table users
(
    id                   int auto_increment
        primary key,
    username             varchar(50)  not null,
    password             varchar(68)  not null,
    enabled              tinyint      not null,
    email                varchar(50)  null,
    photo                longblob     null,
    hash                 varchar(100) null,
    value                varchar(100) null,
    passwordConfirmation varchar(68)  null,
    constraint users_email_uindex
        unique (email),
    constraint users_username_uindex
        unique (username)
);

create table authorities
(
    username  varchar(50) not null,
    authority varchar(50) not null,
    constraint username_authority
        unique (username, authority),
    constraint users__fk
        foreign key (username) references users (username)
);

create table ratings
(
    id      int auto_increment
        primary key,
    value   int(10) not null,
    user_id int     null,
    beer_id int     null,
    constraint ratings_beers_id_fk
        foreign key (beer_id) references beers (id),
    constraint ratings_users_id_fk
        foreign key (user_id) references users (id)
);

create table statuses
(
    id              int auto_increment
        primary key,
    status_value_id int not null,
    beer_id         int null,
    user_id         int null,
    constraint statuses_beers_id_fk
        foreign key (beer_id) references beers (id),
    constraint statuses_status_values_id_fk
        foreign key (status_value_id) references status_values (id),
    constraint statuses_users_id_fk
        foreign key (user_id) references users (id)
);


