SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS Konto;
DROP TABLE IF EXISTS Regninger;
DROP TABLE IF EXISTS Bilag;
DROP TABLE IF EXISTS RejseDag;
DROP TABLE IF EXISTS Rejseafregning;
DROP TABLE IF EXISTS Ansatte;
DROP TABLE IF EXISTS Afdeling;
DROP TABLE IF EXISTS Byer;
DROP TABLE IF EXISTS Opgave;
DROP TABLE IF EXISTS Projekt;




/* Create Tables */

CREATE TABLE Afdeling
(
	Afdeling varchar(100) NOT NULL,
	PRIMARY KEY (Afdeling)
);


CREATE TABLE Ansatte
(
	Id int NOT NULL,
	Afdeling varchar(100),
	Postnummer int(4),
	Fornavn varchar(50) NOT NULL,
	Anviser tinyint NOT NULL,
	Godkender tinyint NOT NULL,
	Efternavn varchar(50) NOT NULL,
	Telefon int(20),
	Email varchar(100) NOT NULL,
	Vejanvn varchar(50),
	Husnr varchar(10),
	Etage varchar(10),
	Doer varbinary(10),
	PRIMARY KEY (Id)
);


CREATE TABLE Bilag
(
	BilagsNo int NOT NULL,
	Nummer int(10) NOT NULL,
	PRIMARY KEY (BilagsNo)
);


CREATE TABLE Byer
(
	Postnummer int(4) NOT NULL,
	Byen varchar(50),
	PRIMARY KEY (Postnummer)
);


CREATE TABLE Konto
(
	Id int NOT NULL,
	RegNo int(4) NOT NULL,
	KontoNo int(10) NOT NULL,
	Bank varchar(50),
	PRIMARY KEY (Id),
	UNIQUE (Id)
);


CREATE TABLE Opgave
(
	Opgave varchar(50) NOT NULL,
	Projekt varchar(50) NOT NULL,
	UNIQUE (Projekt)
);


CREATE TABLE Projekt
(
	Projekt varchar(50) NOT NULL,
	PRIMARY KEY (Projekt),
	UNIQUE (Projekt)
);


CREATE TABLE Regninger
(
	BilagsNo int NOT NULL
);


CREATE TABLE Rejseafregning
(
	Nummer int(10) NOT NULL,
	Projekt varchar(50) NOT NULL,
	Id int NOT NULL,
	Opgave varchar(50) NOT NULL,
	PRIMARY KEY (Nummer)
);


CREATE TABLE RejseDag
(
	Starttid int(4) NOT NULL,
	Sluttid int(4) NOT NULL,
	Byen varchar(50) NOT NULL,
	Land varchar(30) NOT NULL,
	Dato int(4) NOT NULL,
	RejseFormaal varchar(30) NOT NULL,
	Forklaring varchar(1000),
	Nattilaeg tinyint NOT NULL,
	Morgenmad tinyint NOT NULL,
	Frokost tinyint NOT NULL,
	Middag tinyint NOT NULL,
	Refunderes tinyint NOT NULL,
	RejseAfbrudt tinyint NOT NULL,
	Nummer int(10) NOT NULL
);



/* Create Foreign Keys */

ALTER TABLE Ansatte
	ADD FOREIGN KEY (Afdeling)
	REFERENCES Afdeling (Afdeling)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Konto
	ADD FOREIGN KEY (Id)
	REFERENCES Ansatte (Id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Rejseafregning
	ADD FOREIGN KEY (Id)
	REFERENCES Ansatte (Id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Regninger
	ADD FOREIGN KEY (BilagsNo)
	REFERENCES Bilag (BilagsNo)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Ansatte
	ADD FOREIGN KEY (Postnummer)
	REFERENCES Byer (Postnummer)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Opgave
	ADD FOREIGN KEY (Projekt)
	REFERENCES Projekt (Projekt)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Rejseafregning
	ADD FOREIGN KEY (Projekt)
	REFERENCES Projekt (Projekt)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Bilag
	ADD FOREIGN KEY (Nummer)
	REFERENCES Rejseafregning (Nummer)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE RejseDag
	ADD FOREIGN KEY (Nummer)
	REFERENCES Rejseafregning (Nummer)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



