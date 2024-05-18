package com.generation.projectwork114.interfaces;

import java.util.List;
import java.util.Map;

import com.generation.projectwork114.models.Artista;

public interface IServiceArtista {
    List<Artista> getAccounts();

    public void add(Map<String,String> params);

    public Artista findById(Long id);

    public void update(Map<String,String> params);

    public void delete(Long id);
}
