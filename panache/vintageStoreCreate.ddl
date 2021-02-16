create table t_items
(
    id               bigint      not null,
    DTYPE            varchar(31) not null,
    title            varchar(255),
    price            decimal(19, 2),

    isbn             varchar(15),
    language         varchar(255),
    nb_of_pages      integer,
    publication_date date,
    publisher_fk     bigint,
    description      varchar(3000),
    genre            varchar(255),
    music_company    varchar(255),
    primary key (id)
)
create sequence hibernate_sequence start with 1 increment by 1
create table t_authors
(
    id         bigint      not null,
    first_name varchar(50) not null,
    last_name  varchar(50) not null,
    bio        varchar(5000),
    primary key (id)
)
create table t_musicians
(
    id                   bigint      not null,
    first_name           varchar(50) not null,
    last_name            varchar(50) not null,
    preferred_instrument varchar(255),
    primary key (id)
)
create table t_publishers
(
    id   bigint not null,
    name varchar(30),
    primary key (id)
)
create table t_tracks
(
    id       bigint not null,
    duration bigint,
    title    varchar(255),
    cd_fk    bigint,
    primary key (id)
)
alter table t_items
    add constraint FKi6lqpcqfnc4dtsp9w473p5kkj foreign key (publisher_fk) references t_publishers
alter table t_tracks
    add constraint FK23u6r10m0dkp0m8t5hr40ilux foreign key (cd_fk) references t_items
INSERT INTO T_PUBLISHERS (ID, NAME)
VALUES (1000, 'AGoncal Fascicles')
INSERT INTO T_PUBLISHERS (ID, NAME)
VALUES (1001, 'APress')
INSERT INTO T_PUBLISHERS (ID, NAME)
VALUES (1002, 'Manning')
INSERT INTO T_PUBLISHERS (ID, NAME)
VALUES (1003, 'O Reilly')
INSERT INTO T_PUBLISHERS (ID, NAME)
VALUES (1004, 'Addison-Wesley')
INSERT INTO T_PUBLISHERS (ID, NAME)
VALUES (1005, 'John Wiley')
INSERT INTO T_PUBLISHERS (ID, NAME)
VALUES (1006, 'MIT Press')
INSERT INTO T_PUBLISHERS (ID, NAME)
VALUES (1007, 'Packt')
INSERT INTO T_PUBLISHERS (ID, NAME)
VALUES (1008, 'The Pragmatic Programmer')
INSERT INTO T_PUBLISHERS (ID, NAME)
VALUES (1009, 'Prentice Hall')
INSERT INTO T_PUBLISHERS (ID, NAME)
VALUES (1010, 'Wrox Press')
