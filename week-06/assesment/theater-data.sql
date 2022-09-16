use theater;

-- customer
insert into customer(first_name, last_name, email, phone, address)

select distinct
	customer_first as first_name,
    customer_last as last_name,
    customer_email as email, 
    customer_phone as phone,
    customer_address as address
from raw_data;




-- theater
insert into theater(`name`, address, phone, email)

select distinct
	theater as `name`,
    theater_address as address,
    theater_phone as phone,
    theater_email as email
from raw_data;




-- play
insert into play(`name`)

select distinct `show` as `name`
from raw_data;




-- showing
insert into showing(`date`, ticket_price, play_id, theater_id)

select distinct
		`date`, 
		ticket_price, 
        play_id, 
        theater_id
from raw_data as r
inner join play as p 
	on r.`show` = p.`name`
inner join theater as t
	on r.theater = t.`name`;




-- reservation
insert into reservation(seat, customer_id, showing_id)

select distinct
	seat, 
    customer_id, 
    showing_id
from theater.raw_data as r
inner join theater as t
	on r.theater = t.`name`
inner join customer as c
	on r.customer_email = c.email
inner join showing as s
	on r.`date` = s.`date` and  t.theater_id = s.theater_id;
    
-- drop raw-data table
drop table raw_data;

-- Update #1
select *
from showing;

update showing set  
	ticket_price = 22.25
where showing_id = 5;




-- Update #2
select
	r.reservation_id,
    r.seat,
    r.customer_id,
    concat(c.first_name, ' ', c.last_name) CustomerName,
    r.showing_id
from reservation as r
inner join customer as c
	on r.customer_id = c.customer_id
where showing_id = 5;

-- Updated
update reservation set
    seat = 'B4'
where reservation_id = 98;

update reservation set
    seat = 'A4'
where reservation_id = 101;

update reservation set
    seat = 'C2'
where reservation_id = 100;




-- Update #3
select *
from customer
where first_name = 'Jammie';

update customer set  
	phone = '1-801-EAT-CAKE'
where customer_id = 48;




-- DELETE - Delete all single-ticket reservations at the 10 Pin. (You don't have to do it with one query.)
-- 10 pin theater_id = 1
select 
	r.reservation_id,
	count(*) as ReservationNumbers, 
	customer_id, 
    t.theater_id
from reservation as r
inner join showing as s
	on r.showing_id = s.showing_id
inner join theater as t
	on s.theater_id = t.theater_id
where t.theater_id = 1
group by customer_id
order by ReservationNumbers asc;

-- deletions
delete from reservation 
where reservation_id = 50;

delete from reservation 
where reservation_id = 51;

delete from reservation 
where reservation_id = 25;

delete from reservation 
where reservation_id = 26;

delete from reservation 
where reservation_id = 29;

delete from reservation 
where reservation_id = 41;

delete from reservation 
where reservation_id = 59;

delete from reservation 
where reservation_id = 67;

delete from reservation 
where reservation_id = 68;




-- DELETE # 2 the customer Liv Egle of Germany. It appears their reservations were an elaborate joke.
select * 
from customer 
where first_name = 'Liv';

select * 
from reservation
where customer_id = 65;

delete from reservation
where customer_id = 65;

delete from customer 
where customer_id = 65;