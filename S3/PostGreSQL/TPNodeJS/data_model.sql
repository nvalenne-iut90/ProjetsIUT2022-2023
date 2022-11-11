DROP TABLE soutient;
DROP TABLE secompose;
DROP TABLE etudiant;
DROP TABLE jury;
DROP TABLE salle;
DROP TABLE professeur;
DROP TABLE entreprise;

CREATE TABLE entreprise(
    id_entreprise SERIAL,
    nom_entreprise VARCHAR(75),
    PRIMARY KEY(id_entreprise)
);

CREATE TABLE professeur(
    no_professeur SERIAL,
    nom_professeur VARCHAR(40),
    PRIMARY KEY(no_professeur)
);

CREATE TABLE salle(
    id_salle SERIAL,
    nom_salle VARCHAR(5),
    PRIMARY KEY(id_salle)
);

CREATE TABLE jury(
    id_jury SERIAL,
    nom_jury VARCHAR(15),
    id_salle INT,
    PRIMARY KEY(id_jury),
    CONSTRAINT fk_jury_id_salle FOREIGN KEY(id_salle) REFERENCES salle(id_salle)
);

CREATE TABLE etudiant(
    no_etudiant SERIAL,
    nom_etu VARCHAR(50),
    tuteur_present INT,
    id_entreprise INT,
    no_professeur INT,
    PRIMARY KEY(no_etudiant),
    CONSTRAINT fk_etudiant_id_entreprise FOREIGN KEY(id_entreprise) REFERENCES entreprise(id_entreprise),
    CONSTRAINT fk_etudiant_no_professeur FOREIGN KEY(no_professeur) REFERENCES professeur(no_professeur)
);

CREATE TABLE secompose(
    id_jury INT,
    no_professeur INT,
    PRIMARY KEY(id_jury, no_professeur) ,
    CONSTRAINT fk_secompose_id_jury FOREIGN KEY(id_jury) REFERENCES jury(id_jury),
    CONSTRAINT fk_secompose_no_professeur FOREIGN KEY(no_professeur) REFERENCES professeur(no_professeur)
);

CREATE TABLE soutient(
    no_etudiant INT,
    id_jury INT,
    date_sout DATE,
    note smallint,
    PRIMARY KEY(no_etudiant, id_jury, date_sout),
    CONSTRAINT fk_soutient_no_etudiant FOREIGN KEY(no_etudiant) REFERENCES etudiant(no_etudiant),
    CONSTRAINT fk_soutient_id_jury FOREIGN KEY(id_jury) REFERENCES jury(id_jury)
);

