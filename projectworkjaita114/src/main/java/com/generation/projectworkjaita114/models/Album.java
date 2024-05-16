package com.generation.projectworkjaita114.models;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
@Entity
@Data
@Table(name = "Album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "titolo_album")
    private String titolo_album;
    @Column(name = "anno_pubblicazione")
    private int anno_pubblicazione;
    @ManyToOne
    @JoinColumn(name = "id_artista")
    private Artista artista;
    
    @OneToMany(mappedBy = "album")
    private ArrayList<Canzone> canzone;
}
