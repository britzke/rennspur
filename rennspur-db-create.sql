create schema rennspur;
set schema rennspur;
create table clubs (
	id int not null primary key,
	name varchar(256) not null,
	abbreviation varchar (4) not null,
	dsv_number varchar (6)
);

create table events (
	id int not null primary key,
	name varchar(256) not null,
	yardstick char(1) not null,
	clubs_id int not null,
	constraint fk_events_clubs foreign key (clubs_id) references clubs
);

create table races (
	id int not null primary key,
	number int not null,
	events_id int not null,
	constraint fk_races_events foreign key (events_id) references events
);

create table teams (
	id int not null primary key,
	name varchar(10) not null,
	country varchar(3) not null,
	handycap_faktor int,
	clubs_id int not null,
	email varchar(256) not null,
	hash char(64) not null,
	constraint fk_teams_clubs foreign key (clubs_id) references clubs
);

create table team_members (
	id int not null primary key,
	name varchar (256),
	surname varchar (256),
	teams_id int not null,
	clubs_id int,
	constraint fk_team_members_teams foreign key (teams_id) references teams,	
	constraint fk_team_members_clubs foreign key (clubs_id) references clubs	
);


create table team_positions (
	id int not null primary key,
	latitude double not null,
	longitude double not null,
	time timestamp not null,
	teams_id int not null,
	races_id int not null,
	constraint fk_team_positions_teams foreign key (teams_id) references teams,
	constraint fk_team_positions_races foreign key (races_id) references races	
);

create table waypoints (
	id int not null primary key,
	name varchar(5) not null,
	events_id int not null,
	races_id int,
	constraint fk_waypoints_events foreign key (events_id) references events,
	constraint fk_waypoints_races  foreign key (races_id) references races
);

create table waypoint_positions (
	id int not null primary key,
	latitude double not null,
	longitude double not null,
	time timestamp not null,
	waypoints_id int not null,
	constraint waypoint_positions_waypoints foreign key (waypoints_id) references waypoints
);

