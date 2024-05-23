package com.generation.projectwork114.interfaces;

import java.util.List;


import com.generation.projectwork114.models.Album;

public interface IServiceAlbum {
    List<Album> getAlbum();

    public void addAlbum(Album album);

    public Album getAlbumById(Long id);

    public void updateAlbum(Album album);

    public void deleteAlbum(Long id);

    List<Album> findByTitolo(String titolo);

    List<Album> findByIdArtista(Long idArtista);

    
    
}
