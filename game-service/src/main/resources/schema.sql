create table player(
	id serial not null primary key, 
	external_id int not null unique,
	first_name varchar(20) not null,
	last_name varchar(20) not null,
	short_name varchar(20) not null unique);

-- TODO game, play
