-- Makes it possible to rerun script withoug problems
drop database if exists theater;

create database theater;

use theater;

create table customer(
	customer_id int primary key auto_increment,
    first_name 	varchar(150) not null,
    last_name 	varchar(150) not null,
    email 		varchar(150) not null, 
    phone 		varchar(150) null,
    address 	varchar(150) null
);

create table theater(
	theater_id 	int primary key auto_increment,
	`name` 		varchar(150) not null,
    address 	varchar(150) not null,
    phone 		varchar(150) not null,
    email 		varchar(150) not null
);

create table play(
	play_id int primary key auto_increment,
    `name` 	varchar(250) not null
);

create table showing(
	showing_id 		int primary key auto_increment,
    `date` 			date not null,
    ticket_price 	decimal(8,2) not null,
    play_id 		int not null,
    theater_id 		int not null,
    
    constraint fk_showing_play_id
        foreign key (play_id)
        references play(play_id),
	constraint fk_showing_theater_id
        foreign key (theater_id)
        references theater(theater_id)
);

create table reservation(
	reservation_id int primary key auto_increment,
    seat varchar(50),
    customer_id int not null,
    showing_id int not null,
     
    constraint fk_reservation_customer_id
        foreign key (customer_id)
        references customer(customer_id),
	constraint fk_reservation_showing_id
        foreign key (showing_id)
        references showing(showing_id)
    
);

