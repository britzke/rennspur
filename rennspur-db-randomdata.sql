SET SCHEMA RENNSPUR;

INSERT INTO CLUBS (ABBREVIATION,DSV_NUMBER,NAME,URL) VALUES ('DSC','435','Deutscher Segler Club','http://dsc.de');
INSERT INTO CLUBS (ABBREVIATION,DSV_NUMBER,NAME,URL) VALUES ('KSS','534','Karlskrona Segelsällskap','www.knss.nu');

INSERT INTO COUNTRIES (NAME) values ('Germany');
INSERT INTO COUNTRIES (NAME) values ('Sweden');

INSERT INTO TEAMS (COUNTRIES_ID,EMAIL,HANDICAP_FACTOR,HASH,NAME,CLUBS_ID) VALUES (1,'me@deutschland.de',93,'asdh832','GER 72',1);
INSERT INTO TEAMS (COUNTRIES_ID,EMAIL,HANDICAP_FACTOR,HASH,NAME,CLUBS_ID) VALUES (1,'bubi@charmides.in-berlin.de',91,'asdh83r','GER 146',1);
INSERT INTO TEAMS (COUNTRIES_ID,EMAIL,HANDICAP_FACTOR,HASH,NAME,CLUBS_ID) VALUES (1,'bubi@britzke.berlin',114,'asdh83g','GER 574',1);
INSERT INTO TEAMS (COUNTRIES_ID,EMAIL,HANDICAP_FACTOR,HASH,NAME,CLUBS_ID) VALUES (2,'me@schweden.se',114,'534234qasd','SWE 743',2);

INSERT INTO EVENTS (NAME,START_DATE,END_DATE,CLUBS_ID,LONGITUDE,LATITUDE,HANDICAP,RESOLUTION,ROTATION) VALUES ('DSC Yardstick-Cup', '2017-01-05 23:03:20', '2017-01-07 23:03:20', 1, 13.19573, 52.50151,'Y',200,30);
INSERT INTO EVENTS (NAME,START_DATE,END_DATE,CLUBS_ID,LONGITUDE,LATITUDE,HANDICAP,RESOLUTION,ROTATION) VALUES ('505er Lette-Cup 2016', '2017-01-18 11:03:20', '2017-01-19 11:03:20', 2, 13.34148, 52.49603,'N',2000,45);

INSERT INTO TEAM_EVENTS (TEAMS_ID,EVENTS_ID) VALUES (1,1);
INSERT INTO TEAM_EVENTS (TEAMS_ID,EVENTS_ID) VALUES (2,1);
INSERT INTO TEAM_EVENTS (TEAMS_ID,EVENTS_ID) VALUES (3,1);
INSERT INTO TEAM_EVENTS (TEAMS_ID,EVENTS_ID) VALUES (4,1);

INSERT INTO RACES (NUMBER,FINISHED,EVENTS_ID) VALUES (1,1,1);
INSERT INTO RACES (NUMBER,FINISHED,EVENTS_ID) VALUES (2,0,1);
INSERT INTO RACES (NUMBER,FINISHED,EVENTS_ID) VALUES (3,0,1);
INSERT INTO RACES (NUMBER,FINISHED,EVENTS_ID) VALUES (1,0,2);
INSERT INTO RACES (NUMBER,FINISHED,EVENTS_ID) VALUES (2,0,2);


INSERT INTO WAYPOINTS (NAME, RACES_ID) VALUES ('1',1);
INSERT INTO WAYPOINTS (NAME, RACES_ID) VALUES ('2',1);
INSERT INTO WAYPOINTS (NAME, RACES_ID) VALUES ('3',1);
INSERT INTO WAYPOINTS (NAME, RACES_ID) VALUES ('4',1);

INSERT INTO WAYPOINT_POSITIONS (LATITUDE,LONGITUDE,TIME, RACES_ID, WAYPOINTS_ID) VALUES (52.4976487727133,13.187288157641888,'2017-01-10 00:23:26',1,1);
INSERT INTO WAYPOINT_POSITIONS (LATITUDE,LONGITUDE,TIME, RACES_ID, WAYPOINTS_ID) VALUES (52.4973613791362,13.188930004835129,'2017-01-10 00:23:26',1,2);
INSERT INTO WAYPOINT_POSITIONS (LATITUDE,LONGITUDE,TIME, RACES_ID, WAYPOINTS_ID) VALUES (52.49809292272843,13.200431317090988,'2017-01-10 00:23:26',1,3);
INSERT INTO WAYPOINT_POSITIONS (LATITUDE,LONGITUDE,TIME, RACES_ID, WAYPOINTS_ID) VALUES (52.498517473695834,13.200396951287985,'2017-01-10 00:23:26',1,4);
