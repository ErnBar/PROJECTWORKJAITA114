package com.generation.projectwork114.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.generation.projectwork114.database.Database;
import com.generation.projectwork114.interfaces.IDao;

import com.generation.projectwork114.models.Entity;

import com.generation.projectwork114.models.Recensione;

import lombok.Data;


@Service
@Data
public class DaoRecensioni implements IDao{

  
    private final ApplicationContext context;

    private final Database database;

    @Override
    public void add(Entity e) {
        String query = "INSERT INTO recensioni (id, id_utente, id_album, valutazione) VALUES (?, ?, ?, ?)";
        Recensione r = context.getBean("recensione",Recensione.class);
        if (e instanceof Recensione) {
            r = (Recensione) e;
            database.executeUpdate(query, String.valueOf(r.getId()), 
            String.valueOf(r.getId_utente()), String.valueOf(r.getId_album()),String.valueOf(r.getValutazione()));
        }
    }

    @Override
    public List<Map<String, String>> read() {
        String query="SELECT * FROM recensioni";
        return database.executeQuery(query);
    }

    @Override
    public List<Entity> readAll() {
        List<Entity> ris=new ArrayList<>();
        Entity e;
        for(Map<String,String> m : read()) {
            e = context.getBean(Recensione.class, m);
            ris.add(e);
        }
        return ris;  
    }

    @Override
    public void update(Entity e) {
        String query = "UPDATE recensioni SET id_utente = ?, id_album = ?, valutazioni= ? WHERE id = ?";
        Recensione r = context.getBean("recensione",Recensione.class);
        if (e instanceof Recensione) {
            r = (Recensione) e;
            database.executeUpdate(query, String.valueOf(r.getId()));
        } 
    }
    

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM recensioni WHERE id = ?";
        database.executeUpdate(query, String.valueOf(id));
    }

    @Override
    public Entity cercaPerId(Long id) {
        String query = "SELECT * FROM recensioni WHERE id = ?";
        List<Map<String,String>> righe = database.executeQuery(query, String.valueOf(id));
        Map<String, String> m = righe.get(0);
        Recensione r = context.getBean(Recensione.class, m);
        return r;
    }

    
}
