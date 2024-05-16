package com.generation.projectworkjaita114;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.generation.projectworkjaita114.models.Account;
import com.generation.projectworkjaita114.models.Album;
import com.generation.projectworkjaita114.models.Artista;
import com.generation.projectworkjaita114.models.Canzone;
import com.generation.projectworkjaita114.models.Playlist;
import com.generation.projectworkjaita114.models.Recensione;
import com.generation.projectworkjaita114.repository.AccountRepository;
import com.generation.projectworkjaita114.repository.AlbumRepository;
import com.generation.projectworkjaita114.repository.ArtistaRepository;
import com.generation.projectworkjaita114.repository.CanzoneRepository;
import com.generation.projectworkjaita114.repository.PlaylistRepository;
import com.generation.projectworkjaita114.repository.RecensioneRepository;

@Component
public class Test implements CommandLineRunner{


    private Logger logger = LoggerFactory.getLogger(Test.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ArtistaRepository artistiRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private CanzoneRepository canzoniRepository;

    @Autowired
    private PlaylistRepository playlistRepository;


    @Autowired
    private RecensioneRepository recensioniRepository;



    @Override
    public void run(String... args) throws Exception {
        // Inserimento di dati di prova per Account
        Account account = new Account();
        account.setUsername("admin2");
        account.setNome("pino1");
        account.setEmail("email@example2.com");
        account.setPassword("password");
        account.setRuolo("artista");
        account.setData_registrazione(new Timestamp(System.currentTimeMillis()));
        accountRepository.save(account);

        // Recupero e stampa dei dati di Account
        System.out.println("Dati degli Account:");
        ArrayList<Account> accounts = (ArrayList<Account>) accountRepository.findAll();
        for (Account a : accounts) {
            System.out.println(a);
        }

        // Inserimento di dati di prova per Artista
        Artista artista = new Artista();
        artista.setNome_artista("Nome Artista");
        artista.setGenere_musicale("Genere Musicale");
        artista.setBiografia("Biografia");
        artista.setAccount(account);
        artista.setAlbum(null);
        artistiRepository.save(artista);

        // Recupero e stampa dei dati di Artista
        System.out.println("Dati degli Artisti:");
        ArrayList<Artista> artisti = (ArrayList<Artista>) artistiRepository.findAll();
        for (Artista a : artisti) {
            System.out.println(a);
        }

        // Inserimento di dati di prova per Album
        Album album = new Album();
        album.setTitolo_album("Titolo Album");
        album.setAnno_pubblicazione(2024);
        album.setArtista(artista);
        album.setCanzone(null);
        albumRepository.save(album);

        // Recupero e stampa dei dati di Album
        System.out.println("Dati degli Album:");
        ArrayList<Album> albums = (ArrayList<Album>) albumRepository.findAll();
        for (Album a : albums) {
            System.out.println(a);
        }

        // Inserimento di dati di prova per Canzone
        Canzone canzone = new Canzone();
        canzone.setTitolo_canzone("Titolo Canzone");
        canzone.setDurata(Time.valueOf("03:30")); // Formato: hh:mm
        canzone.setPercorso_canzone("/percorso/canzone.mp3");
        canzoniRepository.save(canzone);

        // Recupero e stampa dei dati di Canzone
        System.out.println("Dati delle Canzoni:");
        ArrayList<Canzone> canzoni = (ArrayList<Canzone>) canzoniRepository.findAll();
        for (Canzone c : canzoni) {
            System.out.println(c);
        }

        // Inserimento di dati di prova per Playlist
        Playlist playlist = new Playlist();
        playlist.setNomePlaylist("Nome Playlist");
        playlistRepository.save(playlist);

        // Recupero e stampa dei dati di Playlist
        System.out.println("Dati delle Playlist:");
        ArrayList<Playlist> playlists = (ArrayList<Playlist>) playlistRepository.findAll();
        for (Playlist p : playlists) {
            System.out.println(p);
        }

        // Inserimento di dati di prova per Recensione
        Recensione recensione = new Recensione();
        recensione.setValutazione(5);
        recensioniRepository.save(recensione);

        // Recupero e stampa dei dati di Recensione
        System.out.println("Dati delle Recensioni:");
        ArrayList<Recensione> recensioni = (ArrayList<Recensione>) recensioniRepository.findAll();
        for (Recensione r : recensioni) {
            System.out.println(r);
        }
    }

    
    
}
