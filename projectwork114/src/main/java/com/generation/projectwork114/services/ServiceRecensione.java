package com.generation.projectwork114.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;


import com.generation.projectwork114.dao.DaoRecensioni;
import com.generation.projectwork114.interfaces.IServiceRecensioni;
import com.generation.projectwork114.models.Entity;
import com.generation.projectwork114.models.Recensione;

import lombok.Data;

@Service
@Data
public class ServiceRecensione implements IServiceRecensioni{
    @Qualifier("daoRecensioni")
    private final DaoRecensioni daoRecensione;

    private final ApplicationContext applicationContext;

    @Override
    public List<Recensione> getRecensione() {
        List<Entity> ris = daoRecensione.readAll();
        List<Recensione> recensione = new ArrayList<>();
        for(Entity e : ris) {
            if(e instanceof Recensione)
                recensione.add((Recensione)e);
        }
        return recensione;
    }

    @Override
    public void addRecensione(Map<String, String> params) {
      Recensione recensione = applicationContext.getBean(Recensione.class,params);
        daoRecensione.add(recensione);  
    }

    @Override
    public Recensione findById(Long id) {
        Entity e = daoRecensione.cercaPerId(id);
        if(e instanceof Recensione)
            return (Recensione) e;
            return null;
    }

    @Override
    public void update(Map<String, String> params) {
        Recensione recensione = applicationContext.getBean(Recensione.class,params);
        daoRecensione.update(recensione);
    }

    @Override
    public void delete(Long id) {
        daoRecensione.delete(id);
    }

    
    














}
