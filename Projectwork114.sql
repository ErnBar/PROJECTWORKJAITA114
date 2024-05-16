create database projectwork114;
use projectwork114;

CREATE TABLE Account (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100),
    nome VARCHAR(100),
    email VARCHAR(100),
    password VARCHAR(100),
    ruolo VARCHAR(100),
    data_registrazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_account_username UNIQUE (username),
    CONSTRAINT fk_account_email UNIQUE (email)
);

select*from account;

CREATE TABLE Artisti (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_artista VARCHAR(100),
    genere_musicale VARCHAR(50),
    biografia TEXT,
    id_cantante INT,
    foreign key(id_cantante) references Account(id) on delete cascade on update cascade
);

CREATE TABLE Album (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titolo_album VARCHAR(100),
    anno_pubblicazione INT,
    id_artista INT,
    FOREIGN KEY (id_artista) REFERENCES Artisti(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Canzoni (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titolo_canzone VARCHAR(100),
    durata TIME,
    id_album INT,
    percorso_canzone VARCHAR(255),
    testi TEXT,
    FOREIGN KEY (id_album) REFERENCES Album(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Playlist (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_playlist VARCHAR(100),
    id_account INT,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_account) REFERENCES Account(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE CanzoniNellaPlaylist (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_playlist INT,
    id_canzione INT,
    FOREIGN KEY (id_playlist) REFERENCES Playlist(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_canzione) REFERENCES Canzoni(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Ascolti (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_canzione INT,
    numero_ascolti INT,
    FOREIGN KEY (id_canzione) REFERENCES Canzoni(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Recensioni (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_utente INT,
    id_album INT,
    valutazione INT,
    FOREIGN KEY (id_utente) REFERENCES Account(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_album) REFERENCES Album(id) ON DELETE CASCADE ON UPDATE CASCADE
);

drop database projectwork114;