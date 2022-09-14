drop database if exists solar_test;

create database solar_test;

use solar_test;


create table panel(
    panel_id int primary key auto_increment,
    section varchar(75) not null,
    `row` int not null,
    col int not null,
    year_installed int not null,
    material varchar(7) not null,
    isTracking boolean
);

-- 1. MySQL's default statement terminator is `;`.
-- Since we include `;` inside our procedure, we temporarily change
-- the statement terminator to `//`.
-- That way, the SQL inside is treated as text.
delimiter //
create procedure set_known_good_state()
begin 
    -- 2. Throws out all records without executing deletes.
    -- Resets the auto_increment value.
    truncate table panel;
    
    -- 3. Add test data.
   insert into panel (section, `row`, col, year_installed, material, isTracking)
	values('Main House', 50, 50, 2000, 'POLY_SI', false),
		('Hill', 51, 51, 2012, 'A_SI', true),
        ('Main House', 1, 1, 2020, 'CIGS', true);
end //
-- 4. Change the statement terminator back to the original.
delimiter ;

CALL set_known_good_state();
