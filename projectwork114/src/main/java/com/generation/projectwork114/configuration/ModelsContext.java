package com.generation.projectwork114.configuration;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.generation.projectwork114.dao.DaoAccount;
import com.generation.projectwork114.models.Account;
import com.generation.projectwork114.models.Artista;

@Configuration
public class ModelsContext {

    @Autowired
    private DaoAccount daoAccount;


    @Bean
    @Scope("prototype")
    public Account newAccount(Map<String, String> mappa) {
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
        return a; 
    }  

    @Bean
    @Scope("prototype")
    public Artista newArtista(Map<String,String> mappa){
        Artista a = new Artista();
        Long id = 0L;
        Long cantante = 0L;

        if(mappa.containsKey("id")) {
            id = Long.parseLong(mappa.get("id"));
        }

        a.setId(id);
        a.setNome_artista(mappa.get("nome_artista"));
        a.setGenere_musicale(mappa.get("genere_musicale"));
        a.setBiografia(mappa.get("biografia"));
        a.setId_cantante((Artista)daoAccount.cercaPerId(cantante));
        return a;
        
    }
}
