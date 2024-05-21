package com.generation.projectwork114.interfaces;

import java.util.List;
import java.util.Map;


import com.generation.projectwork114.models.Recensione;

public interface IServiceRecensioni {
    public List<Recensione> getRecensione();

    public void addRecensione(Map<String,String> params);

    public Recensione findById(Long id);

    public void update(Map<String,String> params);

    public void delete(Long id);

}
