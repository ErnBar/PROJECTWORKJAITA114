package com.generation.projectwork114.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.generation.projectwork114.dao.DaoArtista;
import com.generation.projectwork114.interfaces.IServiceArtista;
import com.generation.projectwork114.models.Artista;
import com.generation.projectwork114.models.Entity;

import lombok.Data;
@Service
@Data
public class ServiceArtista implements IServiceArtista{
    @Qualifier("daoArtista")
    private final DaoArtista daoArtista;

    
    private final ApplicationContext applicationContext;

    @Override
    public List<Artista> getAccounts() {
        List<Entity> ris = daoArtista.readAll();
        List<Artista> utenti = new ArrayList<>();
        for(Entity e : ris) {
            if(e instanceof Artista)
                utenti.add((Artista)e);
        }
        return utenti;
    }

    @Override
    public void add(Map<String, String> params) {
        Artista a = applicationContext.getBean(Artista.class,params);
        daoArtista.add(a);
    }

    @Override
    public Artista findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public void update(Map<String, String> params) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
