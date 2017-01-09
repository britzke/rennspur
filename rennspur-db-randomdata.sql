INSERT INTO CLUBS (ABREVIATION,DSV_NUMBER,NAME,URL) VALUES ('DSC','435','Deutscher Segler Club','http://dsc.de');
INSERT INTO CLUBS (ABREVIATION,DSV_NUMBER,NAME,URL) VALUES ('KSS','534','Karlskrona Segelsällskap','www.knss.nu');

INSERT INTO TEAMS (COUNTRY,EMAIL,HANDICAP_FACTOR,HASH,NAME,CLUBS_ID) VALUES ('GER','me@deutschland.de',93,'asdh832','GER 72',1);
INSERT INTO TEAMS (COUNTRY,EMAIL,HANDICAP_FACTOR,HASH,NAME,CLUBS_ID) VALUES ('GER','bubi@charmides.in-berlin.de',91,'asdh83r','GER 146',1);
INSERT INTO TEAMS (COUNTRY,EMAIL,HANDICAP_FACTOR,HASH,NAME,CLUBS_ID) VALUES ('GER','bubi@britzke.berlin',114,'asdh83g','GER 574',1);
INSERT INTO TEAMS (COUNTRY,EMAIL,HANDICAP_FACTOR,HASH,NAME,CLUBS_ID) VALUES ('SWE','me@schweden.se',114,'534234qasd','SWE 743',2);

INSERT INTO EVENTS (NAME,START_DATE,END_DATE,CLUBS_ID,LONGITUDE,LATITUDE,HANDICAP) VALUES ('DSC Yardstick-Cup', '2017-01-05 23:03:20', '2017-01-07 23:03:20', 1, 13.19573, 52.50151,'Y');
INSERT INTO EVENTS (NAME,START_DATE,END_DATE,CLUBS_ID,LONGITUDE,LATITUDE,HANDICAP) VALUES ('505er Lette-Cup 2016', '2017-01-18 11:03:20', '2017-01-19 11:03:20', 2, 13.34148, 52.49603,'N');

INSERT INTO TEAM_EVENTS (TEAMS_ID,EVENTS_ID) VALUES (1,1);
INSERT INTO TEAM_EVENTS (TEAMS_ID,EVENTS_ID) VALUES (2,1);
INSERT INTO TEAM_EVENTS (TEAMS_ID,EVENTS_ID) VALUES (3,1);
INSERT INTO TEAM_EVENTS (TEAMS_ID,EVENTS_ID) VALUES (4,1);

INSERT INTO RACES (NUMBER,EVENTS_ID) VALUES (1,1);
INSERT INTO RACES (NUMBER,EVENTS_ID) VALUES (2,2);

-- team 1, race 1, event 1
INSERT INTO TEAM_POSITIONS (LATITUDE,LONGITUDE,TIME,RACES_ID,TEAMS_ID) VALUES (52.50108, 13.20197,'1970-01-01 23:03:20',1,1);
INSERT INTO TEAM_POSITIONS (LATITUDE,LONGITUDE,TIME,RACES_ID,TEAMS_ID) VALUES (52.50009, 13.19847,'1970-01-01 23:03:20',1,1);
INSERT INTO TEAM_POSITIONS (LATITUDE,LONGITUDE,TIME,RACES_ID,TEAMS_ID) VALUES (52.50142, 13.19501,'1970-01-01 23:03:20',1,1);
INSERT INTO TEAM_POSITIONS (LATITUDE,LONGITUDE,TIME,RACES_ID,TEAMS_ID) VALUES (52.50001, 13.18998,'1970-01-01 23:03:20',1,1);
INSERT INTO TEAM_POSITIONS (LATITUDE,LONGITUDE,TIME,RACES_ID,TEAMS_ID) VALUES (52.50145, 13.18822,'1970-01-01 23:03:20',1,1);
-- team 2, race 1, event 1
INSERT INTO TEAM_POSITIONS (LATITUDE,LONGITUDE,TIME,RACES_ID,TEAMS_ID) VALUES (52.50108 + 0.001, 13.20197 + 0.001,'1970-01-01 23:03:20',1,2);
INSERT INTO TEAM_POSITIONS (LATITUDE,LONGITUDE,TIME,RACES_ID,TEAMS_ID) VALUES (52.50009,         13.19847 + 0.001,'1970-01-01 23:03:20',1,2);
INSERT INTO TEAM_POSITIONS (LATITUDE,LONGITUDE,TIME,RACES_ID,TEAMS_ID) VALUES (52.50142 + 0.001, 13.19501 + 0.001,'1970-01-01 23:03:20',1,2);
INSERT INTO TEAM_POSITIONS (LATITUDE,LONGITUDE,TIME,RACES_ID,TEAMS_ID) VALUES (52.50001 + 0.001, 13.18998,'1970-01-01 23:03:20',1,2);
INSERT INTO TEAM_POSITIONS (LATITUDE,LONGITUDE,TIME,RACES_ID,TEAMS_ID) VALUES (52.50145 + 0.001, 13.18822 + 0.001,'1970-01-01 23:03:20',1,2);
