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


CREATE TABLE Artisti (
    id INT,
    nome_artista VARCHAR(100),
    genere_musicale VARCHAR(50),
    biografia TEXT,
    foreign key(id) references Account(id) on delete cascade on update cascade
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
    durata time,
    id_album INT,
    percorso_canzone VARCHAR(255),
    testi TEXT,
    numero_ascolti int,
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
    id_canzone INT,
    FOREIGN KEY (id_playlist) REFERENCES Playlist(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_canzone) REFERENCES Canzoni(id) ON DELETE CASCADE ON UPDATE CASCADE
);




CREATE TABLE Recensioni (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_utente INT,
    id_album INT,
    valutazione INT,
    FOREIGN KEY (id_utente) REFERENCES Account(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_album) REFERENCES Album(id) ON DELETE CASCADE ON UPDATE CASCADE
);

select*from artisti;
select*from account;

INSERT INTO Account (username, nome, email, password, ruolo) 
VALUES ('admin1', 'Bob Daisy', 'daisybell@gmail.com', 'password123', 'admin');

INSERT INTO Account (username, nome, email, password, ruolo)
VALUES ('starry.being', 'Stefano Castelletti', 'starry@gmail.com', 'starrystarry7', 'artista');

INSERT INTO Artisti (id, nome_artista, genere_musicale, biografia)
VALUES (2, 'Starry', 'Pop', 'Starry canta da sempre, fin dall’infanzia. Ha fatto parte di alcuni cori, come quello del liceo Classico di Asti, in cui ha avuto anche qualche parte da solista. Comincia a scrivere canzoni in inglese nel 2016 circa, prendendo come ispirazione soprattutto dodie e Taylor Swift, per poi ampliare le sue influenze con gli anni. Nel 2020, durante la prima quarantena, ha cominciato a sperimentare con Ableton in casa, producendo e registrando cover e canzoni originali per un progetto che ha denominato Spectrum, con tre canzoni per ogni colore, dal rosso al rosa. Finito quello, ha raccolto i migliori inediti che aveva scritto dal 2016 in poi e li ha riarrangiati e ri-registrati, creando un album completamente self-produced chiamato Cosmosis, distribuito indipendentemente sulle piattaforme digitali, uscito il 19 giugno 2021. Dopo l’estate ha pubblicato anche un  EP di tre cover, sempre prodotto da sé, chiamato Dancing with ghosts. Starry ha poi provato a mandare le sue creazioni a qualche etichetta in giro per l’Italia e il Regno Unito ed è stato finalmente contattato dalla Gotham Dischi, con cui ha pubblicato tre singoli, The Haunting,  The Church e The Truth.');

INSERT INTO Album (titolo_album, anno_pubblicazione, id_artista)
VALUES ('The Haunting', 2022, 2);

INSERT INTO Canzoni (titolo_canzone, durata, id_album, percorso_canzone, testi, numero_ascolti) 
VALUES ('Starry - The Church', '00:02:52', 1, 'Starry.songs', 'Testo della canzone 1', 1000);


INSERT INTO Canzoni (titolo_canzone, durata, id_album, percorso_canzone, testi, numero_ascolti) 
VALUES ('Starry - The Haunting', '00:02:45', 1, 'Starry.songs', 'Testo della canzone 2', 1000);

INSERT INTO Canzoni (titolo_canzone, durata, id_album, percorso_canzone, testi, numero_ascolti) 
VALUES ('Starry - The Truth', '00:03:54', 1, 'Starry.songs', 'Testo della canzone 3', 1000);

INSERT INTO Account (username, nome, email, password, ruolo) 
VALUES ('numberOneFan', 'Gianni Rossi', 'n1fan@gmail.com', 'iLoveMusic123', 'user');

UPDATE Canzoni SET testi = 'You climb the stairs to my bed
Revere a new faith instead
An altar of two hip bones
Grinding mortars of lovers scorned

Already down on my knees
Begging “mercy, please”
So be it, our fatal mass
Grab my hair and carve my flesh
And when you’re done you can just lay onto my chest
No, we need nothing

You made me lose my mind
Divine, divine
You can make this church divine

You climb the steps to my heart
Destroy it and rip it apart
The same won’t happen to you
You’re not a religious fool
Greet you back with open arms
Shine the pews, ready my charms
‘Cause why wouldn’t you want to stay
When you can have all of me?

You made me lose my mind
Divine, divine
You can make this church divine

And i said i was yours
You said you were mine
Oh, it don’t mean a thing
When we’ve drunk all the wine

And i said i was yours
You said you were mine
Oh, it don’t mean a thing
When we’re all out of time

You made me lose my mind
Divine, divine
You can make this church divine

You made me lose my mind
Denied, denied
Don’t you leave this church denied' 
WHERE id = 1;

UPDATE Canzoni SET testi = 'There’s something ‘bout tonight, in the moonlight
Bodies writhing in the dim light
Something strange tonight, claustrophobic space
Yeah, dance the night away, dance the night away

I don’t know how it happens
I don’t know how it makes sense
It’s dark and I can’t see
But I feel your breath on me

I don’t know how it happens
I don’t know how it makes sense
It’s dark and I can’t see
But I feel your breath on me

I don’t want your touch on me
I don’t need you haunting me
I don’t want your touch on me
I don’t need you haunt-
I don’t want your touch on me
I don’t need you haunting me
I don’t want your touch on me
I don’t need you haunt-

There’s something ‘bout tonight, in the moonlight
Bodies kissing, years gone by
Something strange tonight, in this familiar place
Yeah, cry myself away, cry myself away

I don’t know how it happens
I don’t know how it makes sense
It’s dark and I can’t see
But I feel your breath on me

I don’t want your touch on me
I don’t need you haunting me
I don’t want your touch on me
I don’t need you haunt-
I don’t want your touch on me
I don’t need you haunting me
I don’t want your touch on me
I don’t need you haunt-

It’s a secondhand feeling
That so many have gone through
It’s a strange state of being
Surviving people like you

I don’t know how it happens
I don’t know how it makes sense
It’s dark and I can’t see
But I feel your breath on me

I don’t want your touch on me
I don’t need you haunting me
I don’t want your touch on me
I don’t need you haunt-
I don’t want your touch on me
I don’t need you haunting me
I don’t want your touch on me
I don’t need you haunt-'
WHERE id = 2;

UPDATE Canzoni SET testi = 'The truth is
I miss you like the stars miss the sun
The truth is
I think you might be the one

My youth is
Wasted on the ghosts, I am chained
And I guess
We’re dead and risen again

I can’t help but feel like every
word of mine is wrong
Dissonance, our failed romance resounds in every song

But in the end we grew as we went
Together or apart
And you know I can’t be your friend
When you’re locked inside my heart
And truthfully
I love you still

(Give me love, yeah
Give me love, yeah
Give me love, yeah
Give me love, yeah)

The truth is
Maybe you’re the saint
And I’m not charming anymore
But I’ll still praise your name

Will you soothe me?
Or will you shut me out?
So I take one step back
And leave it up to you now

But in the end we grew as we went
Together or apart
And you know I can’t be your friend
When you’re locked inside my heart
And truthfully
I love you still

Maybe it was meant to be this way
Watching from afar
And however we may change
We’ll still keep our scars
But your eyes are still the prettiest thing I’ve seen
And I’ll forgive everything if you come back to me

But in the end we grew as we went

And when the ghosts talk over me
And the world goes dark
I’ll cling to you, my sweet relief
Till I’m back into your arms
‘Cause truthfully
I love you still'
WHERE id = 3;

select  a.titolo_album ,c.*from canzoni c inner join album a on a.id=c.id_album where a.id=1;

select*from account a inner join artisti ar on a.id=ar.id;

select*from account;

select*from artisti;
select*from album;
select*from canzoni;

select a.* from album a inner join artisti ar on a.id_artista=ar.id where ar.id=2;

drop database projectwork114;