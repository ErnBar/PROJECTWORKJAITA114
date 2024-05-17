package com.generation.projectwork114.interfaces;

import java.util.List;
import java.util.Map;

import com.generation.projectwork114.models.Entity;

public interface IDaoArtista {
    
    List<Map<String,String>> read();
    List<Entity> readAll();
    void update(Entity e);
    void delete(Long id);
    Entity cercaPerId(Long id);

}
