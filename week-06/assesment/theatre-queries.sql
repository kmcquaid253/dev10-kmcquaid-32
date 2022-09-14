use theater;

-- Find all performances in the last quarter of 2021 (Oct. 1, 2021 - Dec. 31 2021).
-- performances = showing
select 
		s.showing_id,
        s.`date`,
        p.`name` as ShowName
from showing as s
inner join play as p
	on s.play_id = p.play_id
where `date` between '2021-10-01' and '2021-12-31';




-- List customers without duplication.
select distinct * 
from customer;




-- Find all customers without a .com email address.
select 
	customer_id,
	concat(first_name, ' ', last_name) CustomerName,
    email
from customer
where email not like ("%.com");




-- Find the three cheapest shows.
select 
		s.showing_id,
        s.`date`,
        s.ticket_price,
        p.`name` as ShowName,
        s.theater_id
from showing as s
inner join play as p
	on s.play_id = p.play_id
order by ticket_price asc
limit 3;




-- List customers and the show they're attending with no duplication.
select distinct
	c.customer_id,
     concat(c.first_name, ' ', c.last_name) CustomerName,
     p.`name`,
     s.`date`,
    s.showing_id
from reservation as r 
inner join customer as c
	on r.customer_id = c.customer_id
inner join showing as s
	on r.showing_id = s.showing_id
inner join play as p 
	on s.play_id = p.play_id
order by c.customer_id asc;




-- List customer, show, theater, and seat number in one query.
select distinct
	concat(c.first_name, ' ', c.last_name) CustomerName,
    p.`name` as ShowName,
    t.`name` as TheaterName,
    r.seat as Seat
from customer as c 
inner join reservation as r
	on r.customer_id = c.customer_id
inner join showing as s
	on r.showing_id  = s.showing_id
inner join play as p
	on s.play_id = p.play_id
inner join theater as t
	on s.theater_id = t.theater_id;




-- Find customers without an address.
select 
	customer_id as CustomerID,
    concat(c.first_name, ' ', c.last_name) CustomerName,
    address as Address
from customer as c
where address = '';




-- Recreate the spreadsheet data with a single query.
select 
	c.first_name as customer_first,
    c.last_name as customer_last,
    c.email as customer_email,
    c.phone as customer_phone,
    c.address as cutomer_address,
    r.seat,
    p.`name` as `show`,
    s.ticket_price,
    s.`date`,
    t.`name` as theater,
    t.address as theater_address,
    t.phone as theater_phone,
    t.email as theater_email
from customer as c
inner join reservation as r
	on c.customer_id = r.customer_id
inner join showing as s
	on r.showing_id = s.showing_id
inner join play as p
	on s.play_id = p.play_id
inner join theater as t
	on s.theater_id = t.theater_id;


-- Count total tickets purchased per customer.
select 
	c.customer_id,
	concat(c.first_name, ' ', c.last_name) CustomerName,
	count(reservation_id) as TotalTickets
from reservation
inner join customer as c
	on reservation.customer_id = c.customer_id
group by customer_id;




-- Calculate the total revenue per show based on tickets sold.
select 
	s.showing_id,
    p.`name` as ShowName,
    s.`date`,
	count(reservation_id) as TicketsSold, 
    sum(ticket_price) as TotalRevenue
from reservation 
inner join showing as s
	on reservation.showing_id = s.showing_id
inner join play as p
	on s.play_id = p.play_id
group by showing_id;




-- Calculate the total revenue per theater based on tickets sold.
select 
	s.theater_id, 
    t.`name`,
	count(reservation_id) as TicketsSold, 
    sum(ticket_price) as TotalRevenue
from reservation 
inner join showing as s
	on reservation.showing_id = s.showing_id
inner join theater as t
	on s. theater_id = t.theater_id
group by theater_id;




-- Who is the biggest supporter of RCTTC? Who spent the most in 2021?
select 
	count(r.reservation_id) as TicketsBought, 
    c.customer_id,
    concat(c.first_name, ' ', c.last_name) CustomerName,
    sum(ticket_price) as Total
from customer as c
inner join reservation as r
	on c.customer_id = r.customer_id
inner join showing as s
	on r.showing_id = s.showing_id
group by customer_id
order by Total desc
limit 1;