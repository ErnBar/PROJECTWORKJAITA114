package com.generation.projectwork114.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.generation.projectwork114.dao.DaoCanzone;
import com.generation.projectwork114.interfaces.IServiceCanzone;
import com.generation.projectwork114.models.Canzone;
import com.generation.projectwork114.models.Entity;


import lombok.Data;


@Service
@Data
public class ServiceCanzone implements IServiceCanzone{
    @Qualifier("daoCanzone")
    private final DaoCanzone daoCanzone;

    private final ApplicationContext applicationContext;


    @Override
    public List<Canzone> getCanzone() {
        List<Entity> ris = daoCanzone.readAll();
        List<Canzone> canzoni = new ArrayList<>();
        for(Entity e : ris) {
            if(e instanceof Canzone)
                canzoni.add((Canzone)e);
        }
        return canzoni;
    }

    @Override
    public void addCanzone(Map<String, String> params) {
       Canzone c = applicationContext.getBean(Canzone.class,params);
        daoCanzone.add(c);
    }

    @Override
    public Canzone findById(Long id) {
        Entity e = daoCanzone.cercaPerId(id);
        if(e instanceof Canzone)
            return (Canzone) e;
            return null;
    }

    @Override
    public void updateCanzone(Map<String, String> params) {
        Canzone c = applicationContext.getBean(Canzone.class,params);
        daoCanzone.update(c); 
    }

    @Override
    public void deleteCanzone(Long id) {
        daoCanzone.delete(id);
    }

    @Override
    public List<Canzone> findByNomeCanzone(String nomeCanzone) {
        List<Entity> ris = daoCanzone.readAll();
        List<Canzone> canzone = new ArrayList<>();
        for (Entity row : ris) {
            Canzone c= (Canzone) row;
            if (c.getTitolo_canzone().equalsIgnoreCase(nomeCanzone)) {
                canzone.add(c);
            }
        } return canzone; 
    }

    @Override
    public List<Canzone> findByAlbum(Long idAlbum) {
        Map<Long, Entity> ris = daoCanzone.readByIdAlbum(idAlbum);
        List<Canzone> canzoni = new ArrayList<>();
        for (Entity e : ris.values()) {
            if (e instanceof Canzone) {
                canzoni.add((Canzone) e);
            }
        }
        return canzoni;
    }



}
