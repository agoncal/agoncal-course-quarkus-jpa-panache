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
create sequence hibernate_sequence start with 1 increment by 1
create table Item (DTYPE varchar(31) not null, id bigint not null, description varchar(3000), title varchar(100), unit_cost float, genre varchar(255), music_company varchar(255), total_duration float, isbn varchar(15), language varchar(255), nb_of_pages integer, publication_date date, publisher_pk bigint, primary key (id))
create table Publisher (id bigint not null, name varchar(30), primary key (id))
alter table Item add constraint FKr52rbvg2k7xlxfxg487ocbgtg foreign key (publisher_pk) references Publisher
INSERT INTO PUBLISHER (ID, NAME) VALUES (1000, 'AGoncal Fascicles')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1001, 'APress')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1002, 'Manning')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1003, 'O Reilly')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1004, 'Addison-Wesley')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1005, 'John Wiley')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1006, 'MIT Press')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1007, 'Packt')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1008, 'The Pragmatic Programmer')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1009, 'Prentice Hall')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1010, 'Wrox Press')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, ISBN, LANGUAGE, NB_OF_PAGES, PUBLICATION_DATE, PUBLISHER_PK, DESCRIPTION) VALUES (1000, 'Book', 'Beginning Java EE 7', 49.99, '143024626X', 'ENGLISH', 608, '2014-02-05', 1001, 'Java Enterprise Edition (Java EE) continues to be one of the leading Java technologies and platforms. Beginning Java EE 7 is the first tutorial book on Java EE 7. Step by step and easy to follow, this book describes many of the Java EE 7 specifications...')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, ISBN, LANGUAGE, NB_OF_PAGES, PUBLICATION_DATE, PUBLISHER_PK, DESCRIPTION) VALUES (2000, 'Book', 'Java EE 7 Essentials', 39.99, '1449370179', 'ENGLISH', 362, '2014-02-05', 1002, 'Get up to speed on the principal technologies in the Java Platform, Enterprise Edition 7, and learn how the latest version embraces HTML5, focuses on higher productivity, and provides functionality to meet enterprise demands. Written by Arun Gupta, a k...')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, ISBN, LANGUAGE, NB_OF_PAGES, PUBLICATION_DATE, PUBLISHER_PK, DESCRIPTION) VALUES (3000, 'Book', 'Java EE 7 Recipes: A Problem-Solution Approach', 49.99, '1430244259', 'ENGLISH', 748, '2014-02-05', 1003, 'Java EE 7 Recipes takes an example-based approach in showing how to program Enterprise Java applications in many different scenarios. Be it a small-business web application, or an enterprise database application, Java EE 7 Recipes provides effective an...')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, ISBN, LANGUAGE, NB_OF_PAGES, PUBLICATION_DATE, PUBLISHER_PK, DESCRIPTION) VALUES (4000, 'Book', 'Introducing Java EE 7: A Look at What''s New', 29.99, '1430258489', 'ENGLISH', 240, '2014-02-05', 1004, 'Introducing Java EE 7:  A Look at What’s New&lt;/em&gt; guides you through the new features and enhancements in each of the technologies comprising the Java EE platform.  Readers of this book will not have to wade through introductory material or infor...')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, ISBN, LANGUAGE, NB_OF_PAGES, PUBLICATION_DATE, PUBLISHER_PK, DESCRIPTION) VALUES (5000, 'Book', 'Oracle Certified Master Java Enterprise Architect Java EE 7: Certification Guide', 49.99, '1430250011', 'ENGLISH', 700, '2014-02-05', 1005, 'Oracle Certified Master, Java Enterprise Architect Java EE 7 Certification Guide is a practical hands on guide for those looking to achieve the Master certification. It deals with the different technological aspects necessary to prop up the understandi...')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, GENRE, MUSIC_COMPANY, TOTAL_DURATION, DESCRIPTION) VALUES (100, 'CD', 'Let It Be', 12.99, 'Pop Rock', 'EMI', 123, 'Let It Be is the 12th and final studio album released by the English rock band the Beatles. It was released on 8 May 1970 by the band''s Apple Records label shortly after the group announced their break-up.')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, GENRE, MUSIC_COMPANY, TOTAL_DURATION, DESCRIPTION) VALUES (101, 'CD', 'Abbey Road', 15.99, 'Pop Rock', 'Apple', 76, 'Abbey Road is the 11th studio album released by the English rock band the Beatles. It is their last recorded album, although Let It Be was the last album released before the band''s dissolution in 1970. Work on Abbey Road began in April 1969, and the album was released on 26 September 1969 in the United Kingdom, and 1 October 1969 in the United States.')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, GENRE, MUSIC_COMPANY, TOTAL_DURATION, DESCRIPTION) VALUES (102, 'CD', 'Yellow Submarine', 15.99, 'Pop Rock', 'EMI', 64, 'Yellow Submarine is the tenth studio album by the Beatles in the United Kingdom, released on Apple Records. It was issued as the soundtrack to the film of the same name, which premiered in the United Kingdom seven months prior to the album''s release.')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, GENRE, MUSIC_COMPANY, TOTAL_DURATION, DESCRIPTION) VALUES (103, 'CD', 'Magical Mystery Tour', 14.99, 'Pop Rock', 'Parlophone', 78, 'Magical Mystery Tour is a double EP and LP by the English rock group the Beatles, produced by George Martin, both including the six-song soundtrack to the 1967 film of the same name. The material was released in the United Kingdom on 8 December 1967 as a six-track double EP on the Parlophone label; in the United States the record, released on 27 November 1967, was an eleven-track LP compiled by Capitol Records, adding the band''s 1967 single releases. The EP was also released in Germany, France, Spain, Yugoslavia, Australia and Japan. [1] The first official release of the recordings in the UK as an eleven-track LP did not occur until 1976')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, GENRE, MUSIC_COMPANY, TOTAL_DURATION, DESCRIPTION) VALUES (104, 'CD', 'Sgt. Pepper''s Lonely Hearts Club Band', 24.99, 'Pop Rock', 'Parlophone', 48, 'Sgt. Pepper''s Lonely Hearts Club Band (often shortened to Sgt. Pepper) is the eighth studio album by English rock band the Beatles. Released in June 1967, the album, which included songs such as "With a Little Help from My Friends", "Lucy in the Sky with Diamonds", and "A Day in the Life", now has over 30 million albums sold. Continuing the artistic maturation seen on the band''s album Revolver (1966), Sgt. Pepper further departed from the conventional pop rock idiom of the time and incorporated balladry, psychedelic, music hall, and symphonic influences')
create sequence hibernate_sequence start with 1 increment by 1
create table Author (id bigint not null, first_name varchar(50) not null, last_name varchar(50) not null, bio varchar(5000), primary key (id))
create table Item (DTYPE varchar(31) not null, id bigint not null, description varchar(3000), title varchar(100), unit_cost float, genre varchar(255), music_company varchar(255), total_duration float, isbn varchar(15), language varchar(255), nb_of_pages integer, publication_date date, publisher_pk bigint, primary key (id))
create table Musician (id bigint not null, first_name varchar(50) not null, last_name varchar(50) not null, preferred_instrument varchar(255), primary key (id))
create table Publisher (id bigint not null, name varchar(30), primary key (id))
alter table Item add constraint FKr52rbvg2k7xlxfxg487ocbgtg foreign key (publisher_pk) references Publisher
INSERT INTO PUBLISHER (ID, NAME) VALUES (1000, 'AGoncal Fascicles')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1001, 'APress')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1002, 'Manning')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1003, 'O Reilly')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1004, 'Addison-Wesley')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1005, 'John Wiley')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1006, 'MIT Press')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1007, 'Packt')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1008, 'The Pragmatic Programmer')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1009, 'Prentice Hall')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1010, 'Wrox Press')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, ISBN, LANGUAGE, NB_OF_PAGES, PUBLICATION_DATE, PUBLISHER_PK, DESCRIPTION) VALUES (1000, 'Book', 'Beginning Java EE 7', 49.99, '143024626X', 'ENGLISH', 608, '2014-02-05', 1001, 'Java Enterprise Edition (Java EE) continues to be one of the leading Java technologies and platforms. Beginning Java EE 7 is the first tutorial book on Java EE 7. Step by step and easy to follow, this book describes many of the Java EE 7 specifications...')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, ISBN, LANGUAGE, NB_OF_PAGES, PUBLICATION_DATE, PUBLISHER_PK, DESCRIPTION) VALUES (2000, 'Book', 'Java EE 7 Essentials', 39.99, '1449370179', 'ENGLISH', 362, '2014-02-05', 1002, 'Get up to speed on the principal technologies in the Java Platform, Enterprise Edition 7, and learn how the latest version embraces HTML5, focuses on higher productivity, and provides functionality to meet enterprise demands. Written by Arun Gupta, a k...')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, ISBN, LANGUAGE, NB_OF_PAGES, PUBLICATION_DATE, PUBLISHER_PK, DESCRIPTION) VALUES (3000, 'Book', 'Java EE 7 Recipes: A Problem-Solution Approach', 49.99, '1430244259', 'ENGLISH', 748, '2014-02-05', 1003, 'Java EE 7 Recipes takes an example-based approach in showing how to program Enterprise Java applications in many different scenarios. Be it a small-business web application, or an enterprise database application, Java EE 7 Recipes provides effective an...')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, ISBN, LANGUAGE, NB_OF_PAGES, PUBLICATION_DATE, PUBLISHER_PK, DESCRIPTION) VALUES (4000, 'Book', 'Introducing Java EE 7: A Look at What''s New', 29.99, '1430258489', 'ENGLISH', 240, '2014-02-05', 1004, 'Introducing Java EE 7:  A Look at What’s New&lt;/em&gt; guides you through the new features and enhancements in each of the technologies comprising the Java EE platform.  Readers of this book will not have to wade through introductory material or infor...')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, ISBN, LANGUAGE, NB_OF_PAGES, PUBLICATION_DATE, PUBLISHER_PK, DESCRIPTION) VALUES (5000, 'Book', 'Oracle Certified Master Java Enterprise Architect Java EE 7: Certification Guide', 49.99, '1430250011', 'ENGLISH', 700, '2014-02-05', 1005, 'Oracle Certified Master, Java Enterprise Architect Java EE 7 Certification Guide is a practical hands on guide for those looking to achieve the Master certification. It deals with the different technological aspects necessary to prop up the understandi...')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, GENRE, MUSIC_COMPANY, TOTAL_DURATION, DESCRIPTION) VALUES (100, 'CD', 'Let It Be', 12.99, 'Pop Rock', 'EMI', 123, 'Let It Be is the 12th and final studio album released by the English rock band the Beatles. It was released on 8 May 1970 by the band''s Apple Records label shortly after the group announced their break-up.')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, GENRE, MUSIC_COMPANY, TOTAL_DURATION, DESCRIPTION) VALUES (101, 'CD', 'Abbey Road', 15.99, 'Pop Rock', 'Apple', 76, 'Abbey Road is the 11th studio album released by the English rock band the Beatles. It is their last recorded album, although Let It Be was the last album released before the band''s dissolution in 1970. Work on Abbey Road began in April 1969, and the album was released on 26 September 1969 in the United Kingdom, and 1 October 1969 in the United States.')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, GENRE, MUSIC_COMPANY, TOTAL_DURATION, DESCRIPTION) VALUES (102, 'CD', 'Yellow Submarine', 15.99, 'Pop Rock', 'EMI', 64, 'Yellow Submarine is the tenth studio album by the Beatles in the United Kingdom, released on Apple Records. It was issued as the soundtrack to the film of the same name, which premiered in the United Kingdom seven months prior to the album''s release.')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, GENRE, MUSIC_COMPANY, TOTAL_DURATION, DESCRIPTION) VALUES (103, 'CD', 'Magical Mystery Tour', 14.99, 'Pop Rock', 'Parlophone', 78, 'Magical Mystery Tour is a double EP and LP by the English rock group the Beatles, produced by George Martin, both including the six-song soundtrack to the 1967 film of the same name. The material was released in the United Kingdom on 8 December 1967 as a six-track double EP on the Parlophone label; in the United States the record, released on 27 November 1967, was an eleven-track LP compiled by Capitol Records, adding the band''s 1967 single releases. The EP was also released in Germany, France, Spain, Yugoslavia, Australia and Japan. [1] The first official release of the recordings in the UK as an eleven-track LP did not occur until 1976')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, GENRE, MUSIC_COMPANY, TOTAL_DURATION, DESCRIPTION) VALUES (104, 'CD', 'Sgt. Pepper''s Lonely Hearts Club Band', 24.99, 'Pop Rock', 'Parlophone', 48, 'Sgt. Pepper''s Lonely Hearts Club Band (often shortened to Sgt. Pepper) is the eighth studio album by English rock band the Beatles. Released in June 1967, the album, which included songs such as "With a Little Help from My Friends", "Lucy in the Sky with Diamonds", and "A Day in the Life", now has over 30 million albums sold. Continuing the artistic maturation seen on the band''s album Revolver (1966), Sgt. Pepper further departed from the conventional pop rock idiom of the time and incorporated balladry, psychedelic, music hall, and symphonic influences')
create sequence hibernate_sequence start with 1 increment by 1
create table Author (id bigint not null, first_name varchar(50) not null, last_name varchar(50) not null, bio varchar(5000), primary key (id))
create table Item (DTYPE varchar(31) not null, id bigint not null, description varchar(3000), title varchar(100), unit_cost float, isbn varchar(15), language varchar(255), nb_of_pages integer, publication_date date, genre varchar(255), music_company varchar(255), total_duration float, publisher_pk bigint, primary key (id))
create table Musician (id bigint not null, first_name varchar(50) not null, last_name varchar(50) not null, preferred_instrument varchar(255), primary key (id))
create table Publisher (id bigint not null, name varchar(30), primary key (id))
alter table Item add constraint FKr52rbvg2k7xlxfxg487ocbgtg foreign key (publisher_pk) references Publisher
INSERT INTO PUBLISHER (ID, NAME) VALUES (1000, 'AGoncal Fascicles')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1001, 'APress')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1002, 'Manning')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1003, 'O Reilly')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1004, 'Addison-Wesley')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1005, 'John Wiley')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1006, 'MIT Press')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1007, 'Packt')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1008, 'The Pragmatic Programmer')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1009, 'Prentice Hall')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1010, 'Wrox Press')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, ISBN, LANGUAGE, NB_OF_PAGES, PUBLICATION_DATE, PUBLISHER_PK, DESCRIPTION) VALUES (1000, 'Book', 'Beginning Java EE 7', 49.99, '143024626X', 'ENGLISH', 608, '2014-02-05', 1001, 'Java Enterprise Edition (Java EE) continues to be one of the leading Java technologies and platforms. Beginning Java EE 7 is the first tutorial book on Java EE 7. Step by step and easy to follow, this book describes many of the Java EE 7 specifications...')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, ISBN, LANGUAGE, NB_OF_PAGES, PUBLICATION_DATE, PUBLISHER_PK, DESCRIPTION) VALUES (2000, 'Book', 'Java EE 7 Essentials', 39.99, '1449370179', 'ENGLISH', 362, '2014-02-05', 1002, 'Get up to speed on the principal technologies in the Java Platform, Enterprise Edition 7, and learn how the latest version embraces HTML5, focuses on higher productivity, and provides functionality to meet enterprise demands. Written by Arun Gupta, a k...')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, ISBN, LANGUAGE, NB_OF_PAGES, PUBLICATION_DATE, PUBLISHER_PK, DESCRIPTION) VALUES (3000, 'Book', 'Java EE 7 Recipes: A Problem-Solution Approach', 49.99, '1430244259', 'ENGLISH', 748, '2014-02-05', 1003, 'Java EE 7 Recipes takes an example-based approach in showing how to program Enterprise Java applications in many different scenarios. Be it a small-business web application, or an enterprise database application, Java EE 7 Recipes provides effective an...')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, ISBN, LANGUAGE, NB_OF_PAGES, PUBLICATION_DATE, PUBLISHER_PK, DESCRIPTION) VALUES (4000, 'Book', 'Introducing Java EE 7: A Look at What''s New', 29.99, '1430258489', 'ENGLISH', 240, '2014-02-05', 1004, 'Introducing Java EE 7:  A Look at What’s New&lt;/em&gt; guides you through the new features and enhancements in each of the technologies comprising the Java EE platform.  Readers of this book will not have to wade through introductory material or infor...')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, ISBN, LANGUAGE, NB_OF_PAGES, PUBLICATION_DATE, PUBLISHER_PK, DESCRIPTION) VALUES (5000, 'Book', 'Oracle Certified Master Java Enterprise Architect Java EE 7: Certification Guide', 49.99, '1430250011', 'ENGLISH', 700, '2014-02-05', 1005, 'Oracle Certified Master, Java Enterprise Architect Java EE 7 Certification Guide is a practical hands on guide for those looking to achieve the Master certification. It deals with the different technological aspects necessary to prop up the understandi...')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, GENRE, MUSIC_COMPANY, TOTAL_DURATION, DESCRIPTION) VALUES (100, 'CD', 'Let It Be', 12.99, 'Pop Rock', 'EMI', 123, 'Let It Be is the 12th and final studio album released by the English rock band the Beatles. It was released on 8 May 1970 by the band''s Apple Records label shortly after the group announced their break-up.')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, GENRE, MUSIC_COMPANY, TOTAL_DURATION, DESCRIPTION) VALUES (101, 'CD', 'Abbey Road', 15.99, 'Pop Rock', 'Apple', 76, 'Abbey Road is the 11th studio album released by the English rock band the Beatles. It is their last recorded album, although Let It Be was the last album released before the band''s dissolution in 1970. Work on Abbey Road began in April 1969, and the album was released on 26 September 1969 in the United Kingdom, and 1 October 1969 in the United States.')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, GENRE, MUSIC_COMPANY, TOTAL_DURATION, DESCRIPTION) VALUES (102, 'CD', 'Yellow Submarine', 15.99, 'Pop Rock', 'EMI', 64, 'Yellow Submarine is the tenth studio album by the Beatles in the United Kingdom, released on Apple Records. It was issued as the soundtrack to the film of the same name, which premiered in the United Kingdom seven months prior to the album''s release.')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, GENRE, MUSIC_COMPANY, TOTAL_DURATION, DESCRIPTION) VALUES (103, 'CD', 'Magical Mystery Tour', 14.99, 'Pop Rock', 'Parlophone', 78, 'Magical Mystery Tour is a double EP and LP by the English rock group the Beatles, produced by George Martin, both including the six-song soundtrack to the 1967 film of the same name. The material was released in the United Kingdom on 8 December 1967 as a six-track double EP on the Parlophone label; in the United States the record, released on 27 November 1967, was an eleven-track LP compiled by Capitol Records, adding the band''s 1967 single releases. The EP was also released in Germany, France, Spain, Yugoslavia, Australia and Japan. [1] The first official release of the recordings in the UK as an eleven-track LP did not occur until 1976')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, GENRE, MUSIC_COMPANY, TOTAL_DURATION, DESCRIPTION) VALUES (104, 'CD', 'Sgt. Pepper''s Lonely Hearts Club Band', 24.99, 'Pop Rock', 'Parlophone', 48, 'Sgt. Pepper''s Lonely Hearts Club Band (often shortened to Sgt. Pepper) is the eighth studio album by English rock band the Beatles. Released in June 1967, the album, which included songs such as "With a Little Help from My Friends", "Lucy in the Sky with Diamonds", and "A Day in the Life", now has over 30 million albums sold. Continuing the artistic maturation seen on the band''s album Revolver (1966), Sgt. Pepper further departed from the conventional pop rock idiom of the time and incorporated balladry, psychedelic, music hall, and symphonic influences')
create sequence hibernate_sequence start with 1 increment by 1
create table Author (id bigint not null, first_name varchar(50) not null, last_name varchar(50) not null, bio varchar(5000), primary key (id))
create table Item (DTYPE varchar(31) not null, id bigint not null, description varchar(3000), title varchar(100), unit_cost float, isbn varchar(15), language varchar(255), nb_of_pages integer, publication_date date, genre varchar(255), music_company varchar(255), total_duration float, publisher_pk bigint, primary key (id))
create table Musician (id bigint not null, first_name varchar(50) not null, last_name varchar(50) not null, preferred_instrument varchar(255), primary key (id))
create table Publisher (id bigint not null, name varchar(30), primary key (id))
alter table Item add constraint FKr52rbvg2k7xlxfxg487ocbgtg foreign key (publisher_pk) references Publisher
INSERT INTO PUBLISHER (ID, NAME) VALUES (1000, 'AGoncal Fascicles')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1001, 'APress')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1002, 'Manning')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1003, 'O Reilly')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1004, 'Addison-Wesley')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1005, 'John Wiley')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1006, 'MIT Press')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1007, 'Packt')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1008, 'The Pragmatic Programmer')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1009, 'Prentice Hall')
INSERT INTO PUBLISHER (ID, NAME) VALUES (1010, 'Wrox Press')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, ISBN, LANGUAGE, NB_OF_PAGES, PUBLICATION_DATE, PUBLISHER_PK, DESCRIPTION) VALUES (1000, 'Book', 'Beginning Java EE 7', 49.99, '143024626X', 'ENGLISH', 608, '2014-02-05', 1001, 'Java Enterprise Edition (Java EE) continues to be one of the leading Java technologies and platforms. Beginning Java EE 7 is the first tutorial book on Java EE 7. Step by step and easy to follow, this book describes many of the Java EE 7 specifications...')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, ISBN, LANGUAGE, NB_OF_PAGES, PUBLICATION_DATE, PUBLISHER_PK, DESCRIPTION) VALUES (2000, 'Book', 'Java EE 7 Essentials', 39.99, '1449370179', 'ENGLISH', 362, '2014-02-05', 1002, 'Get up to speed on the principal technologies in the Java Platform, Enterprise Edition 7, and learn how the latest version embraces HTML5, focuses on higher productivity, and provides functionality to meet enterprise demands. Written by Arun Gupta, a k...')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, ISBN, LANGUAGE, NB_OF_PAGES, PUBLICATION_DATE, PUBLISHER_PK, DESCRIPTION) VALUES (3000, 'Book', 'Java EE 7 Recipes: A Problem-Solution Approach', 49.99, '1430244259', 'ENGLISH', 748, '2014-02-05', 1003, 'Java EE 7 Recipes takes an example-based approach in showing how to program Enterprise Java applications in many different scenarios. Be it a small-business web application, or an enterprise database application, Java EE 7 Recipes provides effective an...')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, ISBN, LANGUAGE, NB_OF_PAGES, PUBLICATION_DATE, PUBLISHER_PK, DESCRIPTION) VALUES (4000, 'Book', 'Introducing Java EE 7: A Look at What''s New', 29.99, '1430258489', 'ENGLISH', 240, '2014-02-05', 1004, 'Introducing Java EE 7:  A Look at What’s New&lt;/em&gt; guides you through the new features and enhancements in each of the technologies comprising the Java EE platform.  Readers of this book will not have to wade through introductory material or infor...')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, ISBN, LANGUAGE, NB_OF_PAGES, PUBLICATION_DATE, PUBLISHER_PK, DESCRIPTION) VALUES (5000, 'Book', 'Oracle Certified Master Java Enterprise Architect Java EE 7: Certification Guide', 49.99, '1430250011', 'ENGLISH', 700, '2014-02-05', 1005, 'Oracle Certified Master, Java Enterprise Architect Java EE 7 Certification Guide is a practical hands on guide for those looking to achieve the Master certification. It deals with the different technological aspects necessary to prop up the understandi...')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, GENRE, MUSIC_COMPANY, TOTAL_DURATION, DESCRIPTION) VALUES (100, 'CD', 'Let It Be', 12.99, 'Pop Rock', 'EMI', 123, 'Let It Be is the 12th and final studio album released by the English rock band the Beatles. It was released on 8 May 1970 by the band''s Apple Records label shortly after the group announced their break-up.')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, GENRE, MUSIC_COMPANY, TOTAL_DURATION, DESCRIPTION) VALUES (101, 'CD', 'Abbey Road', 15.99, 'Pop Rock', 'Apple', 76, 'Abbey Road is the 11th studio album released by the English rock band the Beatles. It is their last recorded album, although Let It Be was the last album released before the band''s dissolution in 1970. Work on Abbey Road began in April 1969, and the album was released on 26 September 1969 in the United Kingdom, and 1 October 1969 in the United States.')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, GENRE, MUSIC_COMPANY, TOTAL_DURATION, DESCRIPTION) VALUES (102, 'CD', 'Yellow Submarine', 15.99, 'Pop Rock', 'EMI', 64, 'Yellow Submarine is the tenth studio album by the Beatles in the United Kingdom, released on Apple Records. It was issued as the soundtrack to the film of the same name, which premiered in the United Kingdom seven months prior to the album''s release.')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, GENRE, MUSIC_COMPANY, TOTAL_DURATION, DESCRIPTION) VALUES (103, 'CD', 'Magical Mystery Tour', 14.99, 'Pop Rock', 'Parlophone', 78, 'Magical Mystery Tour is a double EP and LP by the English rock group the Beatles, produced by George Martin, both including the six-song soundtrack to the 1967 film of the same name. The material was released in the United Kingdom on 8 December 1967 as a six-track double EP on the Parlophone label; in the United States the record, released on 27 November 1967, was an eleven-track LP compiled by Capitol Records, adding the band''s 1967 single releases. The EP was also released in Germany, France, Spain, Yugoslavia, Australia and Japan. [1] The first official release of the recordings in the UK as an eleven-track LP did not occur until 1976')
INSERT INTO ITEM (ID, DTYPE, TITLE, UNIT_COST, GENRE, MUSIC_COMPANY, TOTAL_DURATION, DESCRIPTION) VALUES (104, 'CD', 'Sgt. Pepper''s Lonely Hearts Club Band', 24.99, 'Pop Rock', 'Parlophone', 48, 'Sgt. Pepper''s Lonely Hearts Club Band (often shortened to Sgt. Pepper) is the eighth studio album by English rock band the Beatles. Released in June 1967, the album, which included songs such as "With a Little Help from My Friends", "Lucy in the Sky with Diamonds", and "A Day in the Life", now has over 30 million albums sold. Continuing the artistic maturation seen on the band''s album Revolver (1966), Sgt. Pepper further departed from the conventional pop rock idiom of the time and incorporated balladry, psychedelic, music hall, and symphonic influences')
