package com.generation.projectwork114.interfaces;

import java.util.List;
import java.util.Map;

import com.generation.projectwork114.models.Canzone;

public interface IServiceCanzone {
    public List<Canzone> getCanzone();

    public void addCanzone(Map<String,String> params);

    public Canzone findById(Long id);

    public void updateCanzone(Map<String,String> params);

    public void deleteCanzone(Long id);

    public List<Canzone> findByNomeCanzone(String nomeCanzone);

    List<Canzone> findByAlbum(Long idAlbum);
}
