package com.generation.projectwork114.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.generation.projectwork114.dao.DaoPlaylist;
import com.generation.projectwork114.interfaces.IServicePlaylist;

import com.generation.projectwork114.models.Entity;
import com.generation.projectwork114.models.Playlist;

import lombok.Data;

@Service
@Data
public class ServicePlaylist implements IServicePlaylist{

    @Qualifier("daoPlaylist")
    private final DaoPlaylist daoAPlaylist;

    private final ApplicationContext applicationContext;

    @Override
    public List<Playlist> getPlaylist() {
        List<Entity> ris = daoAPlaylist.readAll();
        List<Playlist> playlist = new ArrayList<>();
        for(Entity e : ris) {
            if(e instanceof Playlist)
                playlist.add((Playlist)e);
        }
        return playlist;
    }

    @Override
    public void add(Map<String, String> params) {
        Playlist p = applicationContext.getBean(Playlist.class,params);
        daoAPlaylist.add(p);
    }

    @Override
    public Playlist findById(Long id) {
        Entity e = daoAPlaylist.cercaPerId(id);
        if(e instanceof Playlist)
            return (Playlist) e;
            return null;
    }

    @Override
    public void update(Map<String, String> params) {
        Playlist p = applicationContext.getBean(Playlist.class,params);
        daoAPlaylist.update(p); //forse .add()
    }

    @Override
    public void delete(Long id) {
      daoAPlaylist.delete(id);
    }

    @Override
    public List<Playlist> findByNomePlaylist(String nomePlaylist) {
        List<Entity> ris = daoAPlaylist.readAll();
        List<Playlist> playlist = new ArrayList<>();
        for (Entity row : ris) {
            Playlist p= (Playlist) row;
            if (p.getNome_playlist().equalsIgnoreCase(nomePlaylist)) {
                playlist.add(p);
            }
        }return playlist;  
    }
    

}
    
    

