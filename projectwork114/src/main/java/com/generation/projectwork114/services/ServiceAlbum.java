package com.generation.projectwork114.services;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;


import com.generation.projectwork114.dao.DaoAlbum;
import com.generation.projectwork114.interfaces.IServiceAlbum;
import com.generation.projectwork114.models.Album;
import com.generation.projectwork114.models.Entity;

import lombok.Data;

@Service
@Data
public class ServiceAlbum implements IServiceAlbum{
    @Qualifier("daoAlbum")
    private final DaoAlbum daoAlbum;

    
    private final ApplicationContext applicationContext;

    @Override
    public List<Album> getAlbum() {
        List<Entity> ris = daoAlbum.readAll();
        List<Album> album = new ArrayList<>();
        for(Entity e : ris) {
            if(e instanceof Album)
                album.add((Album)e);
        }
        return album;
    }

    @Override
    public Album getAlbumById(Long id) {
        return (Album) daoAlbum.cercaPerId(id);
    }

    @Override
    public void addAlbum(Album album) {
        daoAlbum.add(album);
    }

    @Override
    public void updateAlbum(Album album) {
        daoAlbum.update(album);
    }

    @Override
    public void deleteAlbum(Long id) {
        daoAlbum.delete(id);
    }

    @Override
    public List<Album> findByTitolo(String titolo) {
        List<Entity> ris = daoAlbum.readAll();
        List<Album> albums = new ArrayList<>();
        for (Entity row : ris) {
            Album album= (Album) row;
            if (album.getTitolo_album().equalsIgnoreCase(titolo)) {
                albums.add(album);
            }
        }return albums;  
    }
    
}
