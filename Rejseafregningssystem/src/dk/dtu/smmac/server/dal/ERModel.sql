SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS Konto;
DROP TABLE IF EXISTS Bilag;
DROP TABLE IF EXISTS Rejse;
DROP TABLE IF EXISTS RejseDag;
DROP TABLE IF EXISTS Rejseafregning;
DROP TABLE IF EXISTS Ansatte;
DROP TABLE IF EXISTS Afdeling;
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
	Fornavn varchar(50) NOT NULL,
	Anviser tinyint NOT NULL,
	Godkender tinyint NOT NULL,
	Efternavn varchar(50) NOT NULL,
	Telefon int(20),
	Email varchar(100) NOT NULL,
	Vejnavn varchar(50),
	Husnr varchar(10),
	Etage varchar(10),
	Doer varbinary(10),
	Postnummer int(4),
	PRIMARY KEY (Id)
);


CREATE TABLE Bilag
(
	BilagsNo int NOT NULL,
	Nummer int(10) NOT NULL,
	Forklaring varchar(200),
	PRIMARY KEY (BilagsNo)
);


CREATE TABLE Konto
(
	Id int NOT NULL,
	RegNo int(4) NOT NULL,
	KontoNo int(10) NOT NULL,
	PRIMARY KEY (Id),
	UNIQUE (Id)
);


CREATE TABLE Opgave
(
	Opgave varchar(50) NOT NULL,
	Projekt varchar(50) NOT NULL,
	PRIMARY KEY (Opgave)
);


CREATE TABLE Projekt
(
	Projekt varchar(50) NOT NULL,
	PRIMARY KEY (Projekt),
	UNIQUE (Projekt)
);


CREATE TABLE Rejse
(
	RejseID int NOT NULL AUTO_INCREMENT,
	Nummer int(10) NOT NULL,
	Land varchar(100),
	Byen varchar(100),
	DatoTil date,
	DatoFra date,
	Projekt varchar(50),
	Opgave varchar(50),
	PRIMARY KEY (RejseID)
);


CREATE TABLE Rejseafregning
(
	Nummer int(10) NOT NULL,
	Id int NOT NULL,
	Starttid int NOT NULL,
	Sluttid int NOT NULL,
	PRIMARY KEY (Nummer)
);


CREATE TABLE RejseDag
(
	Dato date NOT NULL,
	Nummer int(10) NOT NULL,
	Nattilaeg tinyint NOT NULL,
	Morgenmad tinyint NOT NULL,
	Frokost tinyint NOT NULL,
	Middag tinyint NOT NULL,
	Refunderes tinyint NOT NULL,
	RejseAfbrudt tinyint NOT NULL,
	UdokNat tinyint NOT NULL,
	PRIMARY KEY (Dato, Nummer)
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


ALTER TABLE Rejse
	ADD FOREIGN KEY (Opgave)
	REFERENCES Opgave (Opgave)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Opgave
	ADD FOREIGN KEY (Projekt)
	REFERENCES Projekt (Projekt)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Rejse
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


ALTER TABLE Rejse
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



