INSERT INTO Afdeling ( Afdeling ) VALUES ( 'Aqua' );
INSERT INTO Afdeling ( Afdeling ) VALUES ( 'Chemical Engineering' );
INSERT INTO Afdeling ( Afdeling ) VALUES ( 'Chemistry' );
INSERT INTO Afdeling ( Afdeling ) VALUES ( 'Civil Engineering' );
INSERT INTO Afdeling ( Afdeling ) VALUES ( 'Compute' );
INSERT INTO Afdeling ( Afdeling ) VALUES ( 'Electrical Engineering' );
INSERT INTO Afdeling ( Afdeling ) VALUES ( 'Energy' );
INSERT INTO Afdeling ( Afdeling ) VALUES ( 'Environment' );
INSERT INTO Afdeling ( Afdeling ) VALUES ( 'Food' );
INSERT INTO Afdeling ( Afdeling ) VALUES ( 'Fotonik' );
INSERT INTO Afdeling ( Afdeling ) VALUES ( 'Management Engineering' );
INSERT INTO Afdeling ( Afdeling ) VALUES ( 'Mechanical Engineering' );
INSERT INTO Afdeling ( Afdeling ) VALUES ( 'Nanotech' );
INSERT INTO Afdeling ( Afdeling ) VALUES ( 'Physics' );
INSERT INTO Afdeling ( Afdeling ) VALUES ( 'Space' );
INSERT INTO Afdeling ( Afdeling ) VALUES ( 'Systems Biology' );
INSERT INTO Afdeling ( Afdeling ) VALUES ( 'Transport' );
INSERT INTO Afdeling ( Afdeling ) VALUES ( 'Vet' );
INSERT INTO Afdeling ( Afdeling ) VALUES ( 'Wind Energy' );

INSERT INTO Projekt ( Projekt ) VALUES ( 'BASIS' );
INSERT INTO Projekt ( Projekt ) VALUES ( 'PROTEUS' );

INSERT INTO Opgave ( Opgave, Projekt ) VALUES ( 'Uddannelse', 'BASIS' );
INSERT INTO Opgave ( Opgave, Projekt ) VALUES ( 'Nano Connect', 'BASIS' );
INSERT INTO Opgave ( Opgave, Projekt ) VALUES ( 'Driftsudgifter', 'PROTEUS' );

INSERT INTO Ansatte ( Id, Fornavn, Efternavn, Email, Anviser, Godkender ) VALUES ( 0, 'Peter', 'Larsen', 'peter@larsen.dk', -1, -1 );

INSERT INTO Lande ( Land, Takst ) VALUES ( 'Danmark', 404 );
INSERT INTO Lande ( Land, Takst ) VALUES ( 'Sverige', 404 );
INSERT INTO Lande ( Land, Takst ) VALUES ( 'Sydafrika', 259 );
INSERT INTO Lande ( Land, Takst ) VALUES ( 'Bhutan', 196 );

SELECT * FROM Ansatte;

SELECT * FROM Rejseafregning;

SELECT * FROM Opgave;

SELECT * FROM Rejse;

SELECT * FROM Rejseafregning INNER JOIN Rejse ON Rejseafregning.Nummer=Rejse.Nummer WHERE Rejseafregning.Id = 3;

SELECT * FROM RejseDag;

SELECT * FROM Bilag;

SELECT * FROM Lande;

SELECT * FROM Udgifter;

SELECT COUNT(*) FROM Bilag;
DELETE FROM Bilag WHERE BilagsNo = 1;
DELETE FROM Bilag WHERE BilagsNo = 2;
DELETE FROM Bilag WHERE BilagsNo = 3;
DELETE FROM Bilag WHERE BilagsNo = 4;
DELETE FROM Bilag WHERE BilagsNo = 5;
DELETE FROM Bilag WHERE BilagsNo = 6;
DELETE FROM Bilag WHERE BilagsNo = 7;
DELETE FROM Bilag WHERE BilagsNo = 9;
DELETE FROM Bilag WHERE BilagsNo = 10;