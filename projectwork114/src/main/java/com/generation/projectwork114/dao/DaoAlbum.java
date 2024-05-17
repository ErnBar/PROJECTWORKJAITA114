package com.generation.projectwork114.dao;

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
    public void add(Entity e) {
       String query="INSERT INTO album(titolo_album,anno_pubblicazione,id_artista) VALUES(?,?,?)";
       Album a = context.getBean("album",Album.class);
         if(e instanceof Album) {
              a = (Album) e;
              database.executeUpdate(query, a.getTitolo_album(),String.valueOf(a.getAnno_pubblicazione()),
              String.valueOf(a.getId_artista().getId()));
         }  
    }

    @Override
    public List<Map<String, String>> read() {
        String query="SELECT * FROM album";
        return database.executeQuery(query);
    }

    @Override
    public List<Entity> readAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readAll'");
    }

    @Override
    public void update(Entity e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Entity cercaPerId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cercaPerId'");
    }
    
    
}
