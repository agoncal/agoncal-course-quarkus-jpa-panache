create sequence hibernate_sequence start with 1 increment by 1
create table Item
(
    DTYPE            varchar(31) not null,
    id               bigint      not null,
    description      varchar(3000),
    title            varchar(100),
    unit_cost        float,
    isbn             varchar(15),
    language         varchar(255),
    nb_of_pages      integer,
    publication_date date,
    genre            varchar(255),
    music_company    varchar(255),
    total_duration   float,
    publisher_pk     bigint,
    primary key (id)
)
create table Publisher
(
    id   bigint not null,
    name varchar(30),
    primary key (id)
)
alter table Item
    add constraint FKr52rbvg2k7xlxfxg487ocbgtg foreign key (publisher_pk) references Publisher
create sequence hibernate_sequence start with 1 increment by 1
create table Item (DTYPE varchar(31) not null, id bigint not null, description varchar(3000), title varchar(100), unit_cost float, genre varchar(255), music_company varchar(255), total_duration float, isbn varchar(15), language varchar(255), nb_of_pages integer, publication_date date, publisher_pk bigint, primary key (id))
create table Publisher (id bigint not null, name varchar(30), primary key (id))
alter table Item add constraint FKr52rbvg2k7xlxfxg487ocbgtg foreign key (publisher_pk) references Publisher
create sequence hibernate_sequence start with 1 increment by 1
create table Item (DTYPE varchar(31) not null, id bigint not null, description varchar(3000), title varchar(100), unit_cost float, isbn varchar(15), language varchar(255), nb_of_pages integer, publication_date date, genre varchar(255), music_company varchar(255), total_duration float, publisher_pk bigint, primary key (id))
create table Publisher (id bigint not null, name varchar(30), primary key (id))
alter table Item add constraint FKr52rbvg2k7xlxfxg487ocbgtg foreign key (publisher_pk) references Publisher
create sequence hibernate_sequence start with 1 increment by 1
create table Item (DTYPE varchar(31) not null, id bigint not null, description varchar(3000), title varchar(100), unit_cost float, isbn varchar(15), language varchar(255), nb_of_pages integer, publication_date date, genre varchar(255), music_company varchar(255), total_duration float, publisher_pk bigint, primary key (id))
create table Publisher (id bigint not null, name varchar(30), primary key (id))
alter table Item add constraint FKr52rbvg2k7xlxfxg487ocbgtg foreign key (publisher_pk) references Publisher
create sequence hibernate_sequence start with 1 increment by 1
create table Item (DTYPE varchar(31) not null, id bigint not null, description varchar(3000), title varchar(100), unit_cost float, genre varchar(255), music_company varchar(255), total_duration float, isbn varchar(15), language varchar(255), nb_of_pages integer, publication_date date, publisher_pk bigint, primary key (id))
create table Publisher (id bigint not null, name varchar(30), primary key (id))
alter table Item add constraint FKr52rbvg2k7xlxfxg487ocbgtg foreign key (publisher_pk) references Publisher
create sequence hibernate_sequence start with 1 increment by 1
create table Item (DTYPE varchar(31) not null, id bigint not null, description varchar(3000), title varchar(100), unit_cost float, isbn varchar(15), language varchar(255), nb_of_pages integer, publication_date date, genre varchar(255), music_company varchar(255), total_duration float, publisher_pk bigint, primary key (id))
create table Publisher (id bigint not null, name varchar(30), primary key (id))
alter table Item add constraint FKr52rbvg2k7xlxfxg487ocbgtg foreign key (publisher_pk) references Publisher
create sequence hibernate_sequence start with 1 increment by 1
create table Item (DTYPE varchar(31) not null, id bigint not null, description varchar(3000), title varchar(100), unit_cost float, genre varchar(255), music_company varchar(255), total_duration float, isbn varchar(15), language varchar(255), nb_of_pages integer, publication_date date, publisher_pk bigint, primary key (id))
create table Publisher (id bigint not null, name varchar(30), primary key (id))
alter table Item add constraint FKr52rbvg2k7xlxfxg487ocbgtg foreign key (publisher_pk) references Publisher
create sequence hibernate_sequence start with 1 increment by 1
create table Item (DTYPE varchar(31) not null, id bigint not null, description varchar(3000), title varchar(100), unit_cost float, isbn varchar(15), language varchar(255), nb_of_pages integer, publication_date date, genre varchar(255), music_company varchar(255), total_duration float, publisher_pk bigint, primary key (id))
create table Publisher (id bigint not null, name varchar(30), primary key (id))
alter table Item add constraint FKr52rbvg2k7xlxfxg487ocbgtg foreign key (publisher_pk) references Publisher
create sequence hibernate_sequence start with 1 increment by 1
create table Item (DTYPE varchar(31) not null, id bigint not null, description varchar(3000), title varchar(100), unit_cost float, isbn varchar(15), language varchar(255), nb_of_pages integer, publication_date date, genre varchar(255), music_company varchar(255), total_duration float, publisher_pk bigint, primary key (id))
create table Publisher (id bigint not null, name varchar(30), primary key (id))
alter table Item add constraint FKr52rbvg2k7xlxfxg487ocbgtg foreign key (publisher_pk) references Publisher
create sequence hibernate_sequence start with 1 increment by 1
create table Item (DTYPE varchar(31) not null, id bigint not null, description varchar(3000), title varchar(100), unit_cost float, isbn varchar(15), language varchar(255), nb_of_pages integer, publication_date date, genre varchar(255), music_company varchar(255), total_duration float, publisher_pk bigint, primary key (id))
create table Publisher (id bigint not null, name varchar(30), primary key (id))
alter table Item add constraint FKr52rbvg2k7xlxfxg487ocbgtg foreign key (publisher_pk) references Publisher
