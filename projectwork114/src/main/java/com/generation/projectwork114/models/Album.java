package com.generation.projectwork114.models;



import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Album extends Entity{
    
    private String titolo_album;
    private int anno_pubblicazione;
    private Artista id_artista;
    
}
