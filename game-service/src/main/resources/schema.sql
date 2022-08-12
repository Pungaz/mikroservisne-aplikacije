drop table if exists game cascade;
drop table if exists player cascade;

create table player
(
    id          serial  not null primary key,
    full_name   varchar not null,
    external_id int     not null
);

create table game
(
    id                    serial  not null primary key,
    file_name             varchar not null unique,
    started_parsing       boolean not null,
    finished_parsing      boolean not null,
    last_parsed_play_id   int,
    last_parsed_play_time int
);

--
--
-- create table play(
-- 	id serial not null primary key,
-- 	-- ...
-- );
--
-- create table play_player(
-- 	-- ...
-- );
