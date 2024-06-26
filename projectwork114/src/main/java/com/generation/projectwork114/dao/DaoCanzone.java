package com.generation.projectwork114.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.generation.projectwork114.database.Database;
import com.generation.projectwork114.interfaces.IDao;
import com.generation.projectwork114.models.Canzone;
import com.generation.projectwork114.models.Entity;
import com.generation.projectwork114.models.Playlist;

import lombok.Data;

@Service
@Data
public class DaoCanzone implements IDao{

    private final Database database;
    private final ApplicationContext context;
    

    @Override
    public int add(Entity e) {
        int ris = 0;
        String query = "INSERT INTO canzoni (titolo_canzone, durata, id_album, percorso_canzone, testi, numero_ascolti) VALUES (?, ?, ?, ?, ?, ?)";
        
        if (e instanceof Canzone) {
            Canzone c = (Canzone) e;
            ris=database.executeUpdate(query, c.getTitolo_canzone(), c.getDurata(), 
                                    String.valueOf(c.getId_album().getId()), c.getPercorso_canzone(), c.getTesti(), 
                                    String.valueOf(c.getNumero_ascolti()));
            // query = "INSERT INTO canzoninellaplaylist (id_canzone, id_playlist) VALUES (?, ?)";
            // for(Playlist p : c.getPlaylist()) {
            //     database.executeUpdate(query, String.valueOf(c.getId()), String.valueOf(p.getId()));
            // }
        }
        return ris;
    }

    @Override
    public List<Map<String, String>> read() {
        String query = "SELECT * FROM canzoni";
        return database.executeQuery(query);
    }

    @Override
    public List<Entity> readAll() {
        List<Entity> ris=new ArrayList<>();
        Entity e;
        for(Map<String,String> m : read()) {
            e = context.getBean(Canzone.class, m);
            ris.add(e);
        }
        return ris;  
    }

    @Override
    public void update(Entity e) {
        String query = "UPDATE canzoni SET titolo_canzone = ?, durata = ?, id_album = ?, percorso_canzone = ?, testi = ?, numero_ascolti = ? WHERE id = ?";
        Canzone c = context.getBean("canzone",Canzone.class);
        if (e instanceof Canzone) {
            c = (Canzone) e;
            database.executeUpdate(query, c.getTitolo_canzone(), c.getDurata(), 
                                    String.valueOf(c.getId_album().getId()), c.getPercorso_canzone(), c.getTesti(), 
                                    String.valueOf(c.getNumero_ascolti()), String.valueOf(c.getId()));
        } 
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM canzoni WHERE id = ?";
        database.executeUpdate(query, String.valueOf(id));
        
    }

    @Override
    public Entity cercaPerId(Long id) {
        String query = "SELECT * FROM canzoni WHERE id = ?";
        List<Map<String, String>> ris = database.executeQuery(query, String.valueOf(id));
        Map<String, String> m = ris.get(0);
        Canzone c = context.getBean(Canzone.class, m);
        return c;
    }

    public Map<Long, Entity> readByIdPlaylist(Long idPlaylist) {
        String query = "select c.* from canzoni c join canzoninellaplaylist cnp on c.id = cnp.id_playlist where cnp.id_playlist=?";
        List<Map<String, String>> result = database.executeQuery(query, String.valueOf(idPlaylist));
        Map<Long, Entity> ris = new HashMap<>();

        for(Map<String, String> params : result){
            Canzone c = context.getBean(Canzone.class, params);
            ris.put(Long.parseLong(params.get("id")), c);
        }
        return ris;
    }

    public Map<Long, Entity> readByIdAlbum(Long idAlbum) {
        String query = "select * from canzoni where id_album=?";
        List<Map<String, String>> result = database.executeQuery(query, String.valueOf(idAlbum));
        Map<Long, Entity> ris = new HashMap<>();

        for(Map<String, String> params : result){
            Canzone c = context.getBean(Canzone.class, params);
            ris.put(Long.parseLong(params.get("id")), c);
        }
        return ris;
    }

    
    
}
