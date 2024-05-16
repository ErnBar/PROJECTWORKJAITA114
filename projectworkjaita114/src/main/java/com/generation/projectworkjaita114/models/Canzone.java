package com.generation.projectworkjaita114.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Time;
import java.util.ArrayList;

import jakarta.persistence.Column;  

@Entity 
@Table(name = "Canzoni")
@Data
public class Canzone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "titolo_canzone")
    private String titolo_canzone;
    
    @Column(name = "durata")
    private Time durata;

    @ManyToOne
    @JoinColumn(name = "id_album")
    private Album album;

    @Column(name = "percorso_canzone")
    private String percorso_canzone;

    @Column(name = "testi")
    private String testi;

    @ManyToMany(mappedBy = "canzoni")
    private ArrayList<Playlist> playlist;
    
    
   


}
