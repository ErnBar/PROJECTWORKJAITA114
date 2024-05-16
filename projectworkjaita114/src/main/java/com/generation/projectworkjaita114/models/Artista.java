package com.generation.projectworkjaita114.models;

import java.util.ArrayList;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "Artisti")
@Data
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "nome_artista")
    private String nome_artista;


    @Column(name = "genere_musicale")
    private String genere_musicale;


    @Column(name = "biografia")
    private String biografia;


    @OneToOne 
    @JoinColumn(name = "id_cantante")
    private Account account;

    @OneToMany(mappedBy = "artista")
    private ArrayList<Album> album;
  
}
