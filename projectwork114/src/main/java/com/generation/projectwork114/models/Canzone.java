package com.generation.projectwork114.models;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Canzone extends Entity{
    
    private String titolo_canzone;
    private String durata;
    private Album id_album;
    private String percorso_canzone;
    private String testi;
    private int numero_ascolti;
    private List<Playlist> playlist;//Per la tabella associativa
}
