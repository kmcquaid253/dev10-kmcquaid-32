drop database if exists solar;

create database solar;

use solar;


create table panel(
    panel_id int primary key auto_increment,
    section varchar(75) not null,
    `row` int not null,
    col int not null,
    year_installed int not null,
    material varchar(7) not null,
    isTracking boolean
);