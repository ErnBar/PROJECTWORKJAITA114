package com.generation.projectwork114.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.generation.projectwork114.database.Database;
import com.generation.projectwork114.interfaces.IDao;
import com.generation.projectwork114.models.Album;
import com.generation.projectwork114.models.Entity;

import lombok.Data;
@Service
@Data
public class DaoAlbum implements IDao{

    private final Database database;

    private final ApplicationContext context;

    @Override
    public int add(Entity e) {
        int ris=0;
       String query="INSERT INTO album(titolo_album,anno_pubblicazione,id_artista) VALUES(?,?,?)";
       Album a = context.getBean("album",Album.class);
         if(e instanceof Album) {
              a = (Album) e;
              database.executeUpdate(query, a.getTitolo_album(),String.valueOf(a.getAnno_pubblicazione()),
              String.valueOf(a.getId_artista().getId()));
         } 
        return ris; 
         
    }

    @Override
    public List<Map<String, String>> read() {
        String query="SELECT * FROM album";
        return database.executeQuery(query);
    }

    @Override
    public List<Entity> readAll() {
        List<Entity> ris=new ArrayList<>();
        Entity e;
        for(Map<String,String> a : read()) {
            e = context.getBean(Album.class, a);
            ris.add(e);
        }
        return ris;
    }

    @Override
    public void update(Entity e) {
        String query = "UPDATE album SET titolo_album = ?, anno_pubblicazione = ? WHERE id = ?";
        Album a = context.getBean("album",Album.class);
        if(e instanceof Album) {
            a = (Album) e;
            database.executeUpdate(query, a.getTitolo_album(), String.valueOf(a.getAnno_pubblicazione()), String.valueOf(a.getId()));
        }    
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM album WHERE id = ?";
        database.executeUpdate(query, String.valueOf(id));
    }

    @Override
    public Entity cercaPerId(Long id) {
        String query = "SELECT * FROM album WHERE id = ?";
        List<Map<String,String>> ris = database.executeQuery(query, String.valueOf(id));
        Map<String,String> m = ris.get(0);
        Album a = context.getBean(Album.class, m);
        return a;
    }
    
    
}
