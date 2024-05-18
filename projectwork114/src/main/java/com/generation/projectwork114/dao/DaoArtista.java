package com.generation.projectwork114.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.generation.projectwork114.database.Database;
import com.generation.projectwork114.interfaces.IDao;
import com.generation.projectwork114.models.Artista;
import com.generation.projectwork114.models.Entity;

import lombok.Data;

@Service
@Data
public class DaoArtista implements IDao{

    private final Database database;

    private final ApplicationContext context;
    
    @Override
    public List<Map<String, String>> read() {
        String query = "SELECT * FROM artisti";
        return database.executeQuery(query);
        
    }

    @Override
    public List<Entity> readAll() {
       List<Entity> ris = new ArrayList<>();
        Entity e;
        for(Map<String,String> m : read()) {
            e = context.getBean(Artista.class, m);
            ris.add(e);
        }
        return ris;
    }

    @Override
    public void update(Entity e) {
        String query = "UPDATE artisti SET nome_artista = ?, genere_musicale = ?, biografia = ? WHERE id = ?";
        Artista a = context.getBean("artista",Artista.class);
        if(e instanceof Artista) {
            a = (Artista) e;
            database.executeUpdate(query, a.getNome_artista(), a.getGenere_musicale(), a.getBiografia(), String.valueOf(a.getId()));
        }    
    }

    @Override
    public void delete(Long id) {
       String query = "DELETE FROM artisti WHERE id = ?";
         database.executeUpdate(query, String.valueOf(id));
    }

    @Override
    public Entity cercaPerId(Long id) {
        String query = "SELECT * FROM artisti WHERE id = ?";
        List<Map<String,String>> ris = database.executeQuery(query, String.valueOf(id));
        Map<String,String> m = ris.get(0);
        Artista a = context.getBean(Artista.class, m);
        return a;
    }

    @Override
    public int add(Entity e) {
        int ris = 0;
        String query = "INSERT INTO account (username, nome, email, password, ruolo) VALUES (?, ?, ?, ?, ?)";
        String query2 = "INSERT INTO artisti(id,nome_artista,genere_musicale,biografia) VALUES(?,?,?,?)";
        Artista a = null;
        if (e!=null && e instanceof Artista) {
            a = (Artista) e;
            ris=database.executeUpdate(query, a.getUsername(), a.getNome(), a.getEmail(),
             a.getPassword(), a.getRuolo());
            database.executeUpdate(query2,String.valueOf(ris), a.getNome_artista(),a.getGenere_musicale(),a.getBiografia());
        }
        return ris;
    }
    
}
