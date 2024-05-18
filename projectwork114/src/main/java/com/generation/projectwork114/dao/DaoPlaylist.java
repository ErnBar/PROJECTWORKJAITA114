package com.generation.projectwork114.dao;

import java.util.ArrayList;
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
public class DaoPlaylist implements IDao {

    private final Database database;

    private final ApplicationContext context;

    @Override
    public int add(Entity e) {
        int ris = 0;
        String query="INSERT INTO playlist (nome_playlist, id_account, data_creazione)VALLUES(?,?,?)";
        Playlist p = context.getBean("playlist", Playlist.class);
        if (e instanceof Playlist) {
            p = (Playlist) e;
            database.executeUpdate(query, p.getNome_playlist(), String.valueOf(p.getId_account()), String.valueOf(p.getDataCreazione()));     
            query = "INSERT INTO canzoninellaplaylist (id_playlist, id_canzone) VALUES(?,?)";
            for (Canzone c: p.getCanzoni()) {
                database.executeUpdate(query, String.valueOf(p.getId()), String.valueOf(c.getId()));
            }

        }
        return ris;
   }

    @Override
    public List<Map<String, String>> read() {
        String query = "SELECT * FROM playlist";
        return database.executeQuery(query);
    }


    @Override
    public List<Entity> readAll() {
        List<Entity> ris = new ArrayList<>();
        Entity e;
        for (Map<String, String> m : read()) {
            e = context.getBean(Playlist.class, m);
            ris.add(e);
        }
        return ris;
    }


    @Override
    public void update(Entity e) {
        String query= "UPDATE playlist SET nome_playlist = ?, id_account = ?, data_creazione = ? WHERE id = ?";
        Playlist p = context.getBean("playlist", Playlist.class);
        if( e instanceof Playlist) {
            p = (Playlist) e;
            database.executeUpdate(query, p.getNome_playlist(), String.valueOf(p.getId_account()), String.valueOf(p.getDataCreazione()), String.valueOf(p.getId()));
       }
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM playlist WHERE id = ?";
        database.executeUpdate(query, String.valueOf(id));
   }

    @Override
    public Entity cercaPerId(Long id) {
        String query = "SELECT * FROM playlist WHERE id = ?";
        List<Map<String, String>> righe = database.executeQuery(query, String.valueOf(id));
        if (righe.size() == 0) {
            return null;
        }
        return context.getBean(Playlist.class, righe.get(0));
  }

}
