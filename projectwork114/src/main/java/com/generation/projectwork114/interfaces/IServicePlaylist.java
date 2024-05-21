package com.generation.projectwork114.interfaces;

import java.util.List;
import java.util.Map;



import com.generation.projectwork114.models.Playlist;

public interface IServicePlaylist {

    public List<Playlist> getPlaylist();

    public void add(Map<String,String> params);

    public Playlist findById(Long id);

    public void update(Map<String,String> params);

    public void delete(Long id);

    public List<Playlist> findByNomePlaylist(String nomePlaylist);
}
