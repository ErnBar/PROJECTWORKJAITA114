package com.generation.projectwork114.configuration;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import com.generation.projectwork114.dao.DaoAccount;
import com.generation.projectwork114.dao.DaoAlbum;
import com.generation.projectwork114.dao.DaoArtista;
import com.generation.projectwork114.dao.DaoPlaylist;
import com.generation.projectwork114.models.Account;
import com.generation.projectwork114.models.Album;
import com.generation.projectwork114.models.Artista;
import com.generation.projectwork114.models.Canzone;
import com.generation.projectwork114.models.Entity;
import com.generation.projectwork114.models.Playlist;

@Configuration
public class ModelsContext {

    @Autowired
    private DaoAccount daoAccount;

    @Autowired
    private DaoArtista daoArtista;

    @Autowired
    private DaoAlbum daoAlbum;

    @Autowired
    private DaoPlaylist daoPlaylist;


    @Bean
    @Scope("prototype")
    @Primary
    public Account account(Map<String, String> mappa) {
        Account a=new Account();
        Long id=0L;
        if (mappa.containsKey("id")) {
            id = Long.parseLong(mappa.get("id"));
        }
        a.setId(id);
        a.setUsername(mappa.get("username"));
        a.setNome(mappa.get("nome"));
        a.setEmail(mappa.get("email"));
        a.setPassword(mappa.get("password"));
        a.setRuolo(mappa.get("ruolo"));

        if(mappa.containsKey("data_registrazione")){
        a.setData_registrazione(Timestamp.valueOf(mappa.get("data_registrazione")));
        }
        return a; 
    }  

    @Bean
    @Scope("prototype")
    public Artista artista(Map<String,String> mappa){
        Artista a = new Artista();
        Long id = 0L;

        if(mappa.containsKey("id")) {
            id = Long.parseLong(mappa.get("id"));
        }
        a.setId(id);
        a.setUsername(mappa.get("username"));
        a.setNome(mappa.get("nome"));
        a.setEmail(mappa.get("email"));
        a.setPassword(mappa.get("password"));
        a.setRuolo(mappa.get("ruolo"));
        a.setNome_artista(mappa.get("nome_artista"));
        a.setGenere_musicale(mappa.get("genere_musicale"));
        a.setBiografia(mappa.get("biografia"));
        return a;
        
    }

    @Bean
    @Scope("prototype")
    public Album album(Map<String,String> mappa){
        Album a = new Album();
        Long id = 0L;
        Long id_artista = 0L;

        if(mappa.containsKey("id")) {
            id = Long.parseLong(mappa.get("id"));
        }
        a.setId(id);
        a.setTitolo_album(mappa.get("titolo_album"));
        a.setAnno_pubblicazione(Integer.parseInt(mappa.get("anno_pubblicazione")));
        if(mappa.containsKey("id_artista")) {
            id_artista = Long.parseLong(mappa.get("id_artista"));
        }
        a.setId_artista((Artista)daoArtista.cercaPerId(id_artista));
        return a;
    }

    @Bean
    @Scope("prototype")
    public Canzone canzone(Map<String, String> mappa){

        Canzone c = new Canzone();
        Long id = -1L;
        Long id_album = 0L;

        if(mappa.containsKey("id")) {
            id = Long.parseLong(mappa.get("id"));
        }
        c.setId(id);
        c.setTitolo_canzone(mappa.get("titolo_canzone"));
        c.setDurata(mappa.get("durata"));
        if(mappa.containsKey("id_album")) {
            id_album = Long.parseLong(mappa.get("id_album"));
        }
        c.setId_album((Album)daoAlbum.cercaPerId(id_album));
        c.setPercorso_canzone(mappa.get("percorso_canzone"));
        c.setTesti(mappa.get("testi"));
        c.setNumero_ascolti(Integer.parseInt(mappa.get("numero_ascolti")));
        
        List<Playlist> listaPlaylists = new ArrayList<>();
        Map<Long, Entity> result = daoPlaylist.readByIdCanzone(c.getId());
            for(Entity e : result.values()){
                if(e instanceof Playlist){
                    listaPlaylists.add((Playlist)e);
            }
        }
        c.setPlaylist(listaPlaylists);
        
        return c;
    }


    // @Bean
    // @Scope("prototype")
    // public Playlist playlist(Map<String, String> mappa){
    //     Playlist p = new Playlist();
    //     Long id = 0L;
    //     Long id_account = 0L;

    //     if(mappa.containsKey("id")) {
    //         id = Long.parseLong(mappa.get("id"));
    //     }
    //     p.setId(id);
    //     p.setNome_playlist(mappa.get("nome_playlist"));
    //     if(mappa.containsKey("id_account")) {
    //         id_account = Long.parseLong(mappa.get("id_account"));
    //     }
    //     p.setId_account((Account)daoAccount.cercaPerId(id_account));
        //p.setDataCreazione(Timestamp.valueOf(mappa.get("data_creazione")));
        //List<Canzone> listaCanzoni = new ArrayList<>();
        //Map<Long, Entity> result = daoPlaylist.readByIdPlaylist(p.getId());
       // for(Entity e : result.values()){
         //   if(e instanceof Canzone){
                //listaCanzoni.add((Canzone)e);
         //   }
       // }
       // p.setCanzoni(listaCanzoni);
       // return p;

    //}

        
}
