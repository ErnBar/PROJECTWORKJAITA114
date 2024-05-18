package com.generation.projectwork114.models;



import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Artista extends Account{

    private String nome_artista;
    private String genere_musicale;
    private String biografia;
    private Long id_cantante;
    //private Account id_cantante; 
    
    
}
