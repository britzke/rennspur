--create schema rennspur;
set schema rennspur;

CREATE TABLE TEAM_POSITIONS (
		ID INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
		LATITUDE DOUBLE NOT NULL,
		LONGITUDE DOUBLE NOT NULL,
		TIME TIMESTAMP NOT NULL,
		RACES_ID INTEGER,
		TEAMS_ID INTEGER NOT NULL
	);

CREATE TABLE WAYPOINTS (
		ID INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
		NAME VARCHAR(255) NOT NULL,
		EVENTS_ID INTEGER,
		RACES_ID INTEGER NOT NULL
	);

CREATE TABLE TEAM_MEMBERS (
		ID INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
		NAME VARCHAR(255) NOT NULL,
		SURNAME VARCHAR(255),
		CLUBS_ID INTEGER,
		TEAMS_ID INTEGER
	);

CREATE TABLE TEAM_EVENTS (
		TEAMS_ID INTEGER NOT NULL,
		EVENTS_ID INTEGER NOT NULL
	);

CREATE TABLE WAYPOINT_POSITIONS (
		ID INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
		LATITUDE DOUBLE NOT NULL,
		LONGITUDE DOUBLE NOT NULL,
		TIME TIMESTAMP NOT NULL,
		RACES_ID INTEGER NOT NULL,
		WAYPOINTS_ID INTEGER NOT NULL
	);

CREATE TABLE EVENTS (
		ID INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
		END_DATE TIMESTAMP,
		LATITUDE DOUBLE,
		LONGITUDE DOUBLE,
		NAME VARCHAR(255) NOT NULL,
		START_DATE TIMESTAMP NOT NULL,
		CLUBS_ID INTEGER NOT NULL
	);

CREATE TABLE RACES (
		ID INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
		NUMBER INTEGER NOT NULL,
		EVENTS_ID INTEGER NOT NULL
	);

CREATE TABLE TEAMS (
		ID INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
		COUNTRY VARCHAR(3) NOT NULL,
		EMAIL VARCHAR(255) NOT NULL,
		HANDYCAP_FACTOR INTEGER NOT NULL,
		HASH VARCHAR(64) NOT NULL,
		NAME VARCHAR(10) NOT NULL,
		CLUBS_ID INTEGER NOT NULL
	);

CREATE TABLE CLUBS (
		ID INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
		ABBREVIATION VARCHAR(64) NOT NULL,
		DSV_NUMBER VARCHAR(6) NOT NULL,
		NAME VARCHAR(255) NOT NULL,
		URL VARCHAR(255) NOT NULL
	);

ALTER TABLE CLUBS ADD PRIMARY KEY (ID);

ALTER TABLE TEAM_MEMBERS ADD PRIMARY KEY (ID);

ALTER TABLE WAYPOINTS ADD PRIMARY KEY (ID);

ALTER TABLE TEAM_POSITIONS ADD PRIMARY KEY (ID);

ALTER TABLE WAYPOINT_POSITIONS ADD PRIMARY KEY (ID);

ALTER TABLE RACES ADD PRIMARY KEY (ID);

ALTER TABLE TEAM_EVENTS ADD PRIMARY KEY (TEAMS_ID, EVENTS_ID);

ALTER TABLE TEAMS ADD PRIMARY KEY (ID);

ALTER TABLE EVENTS ADD PRIMARY KEY (ID);

ALTER TABLE TEAM_EVENTS ADD CONSTRAINT TEAMEVENTSEVENTSID FOREIGN KEY (EVENTS_ID)
	REFERENCES EVENTS (ID);

ALTER TABLE WAYPOINT_POSITIONS ADD CONSTRAINT WYPNTPOSITIONSRCSD FOREIGN KEY (RACES_ID)
	REFERENCES RACES (ID);

ALTER TABLE TEAMS ADD CONSTRAINT FK_TEAMS_CLUBS_ID FOREIGN KEY (CLUBS_ID)
	REFERENCES CLUBS (ID);

ALTER TABLE WAYPOINT_POSITIONS ADD CONSTRAINT WYPNTPSTONSWYPNTSD FOREIGN KEY (WAYPOINTS_ID)
	REFERENCES WAYPOINTS (ID);

ALTER TABLE TEAM_EVENTS ADD CONSTRAINT TEAMEVENTSTEAMS_ID FOREIGN KEY (TEAMS_ID)
	REFERENCES TEAMS (ID);

ALTER TABLE WAYPOINTS ADD CONSTRAINT WAYPOINTS_RACES_ID FOREIGN KEY (RACES_ID)
	REFERENCES RACES (ID);

ALTER TABLE TEAM_POSITIONS ADD CONSTRAINT TAMPOSITIONSTAMSID FOREIGN KEY (TEAMS_ID)
	REFERENCES TEAMS (ID);

ALTER TABLE RACES ADD CONSTRAINT FK_RACES_EVENTS_ID FOREIGN KEY (EVENTS_ID)
	REFERENCES EVENTS (ID);

ALTER TABLE TEAM_MEMBERS ADD CONSTRAINT TEAMMEMBERSTEAMSID FOREIGN KEY (TEAMS_ID)
	REFERENCES TEAMS (ID);

ALTER TABLE TEAM_POSITIONS ADD CONSTRAINT TAMPOSITIONSRCESID FOREIGN KEY (RACES_ID)
	REFERENCES RACES (ID);

ALTER TABLE TEAM_MEMBERS ADD CONSTRAINT TEAMMEMBERSCLUBSID FOREIGN KEY (CLUBS_ID)
	REFERENCES CLUBS (ID);

ALTER TABLE WAYPOINTS ADD CONSTRAINT WAYPOINTSEVENTS_ID FOREIGN KEY (EVENTS_ID)
	REFERENCES EVENTS (ID);

ALTER TABLE EVENTS ADD CONSTRAINT FK_EVENTS_CLUBS_ID FOREIGN KEY (CLUBS_ID)
	REFERENCES CLUBS (ID);

