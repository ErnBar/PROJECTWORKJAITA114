package com.generation.projectwork114.configuration;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.generation.projectwork114.models.Account;

@Configuration
public class ModelsContext {


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
}
