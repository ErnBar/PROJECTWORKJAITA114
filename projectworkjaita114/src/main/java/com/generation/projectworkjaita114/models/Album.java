package com.generation.projectworkjaita114.models;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
@Entity
@Data
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titoloAlbum;
    private int annoPubblicazione;
    @ManyToOne
    @JoinColumn(name = "id_artista")
    private Artista artista;
    
    @OneToMany(mappedBy = "album")
    private ArrayList<Canzone> canzone;
}
