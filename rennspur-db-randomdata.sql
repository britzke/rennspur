INSERT INTO clubs (ABBREVIATION,DSV_NUMBER,NAME,URL) values ('GER','435','Die deutschen Segler','http://google.de');
INSERT INTO clubs (ABBREVIATION,DSV_NUMBER,NAME,URL) values ('SWE','534','Die schwedischen Segler','http://google.se');

INSERT INTO TEAMS (COUNTRY,EMAIL,HANDYCAP_FAKTOR,HASH,NAME,CLUBS_ID) VALUES ('DE','me@deutschland.de',1,'asdh832','sip',1);
INSERT INTO TEAMS (COUNTRY,EMAIL,HANDYCAP_FAKTOR,HASH,NAME,CLUBS_ID) VALUES ('SE','me@schweden.se',2,'534234qasd','SWEDEN',2);

INSERT INTO events (end_date, handicap, name, start_date, clubs_id) VALUES ('1970-01-01 23:03:20','G', 'SegelClub HD', '1980-01-01 23:03:20', 1);
INSERT INTO events (end_date, handicap, name, start_date, clubs_id) VALUES ('1970-01-01 23:03:20','B', 'ClubSegler', '1980-01-01 23:03:20', 2);

INSERT INTO Team_positions (LATITUDE,LONGITUDE,TIME,RACES_ID,TEAMS_ID) VALUES (43234,3421,'1970-01-01 23:03:20',1,1);
INSERT INTO Team_positions (LATITUDE,LONGITUDE,TIME,RACES_ID,TEAMS_ID) VALUES (347723,342412	1,'1970-01-01 23:03:20',2,2);