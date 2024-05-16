package com.generation.projectworkjaita114.models;

import jakarta.persistence.JoinTable;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.sql.Timestamp;
import java.util.ArrayList;

import jakarta.persistence.Column;

import jakarta.persistence.GenerationType;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Data;

import jakarta.persistence.Table;

import jakarta.persistence.Entity;

@Entity
@Table(name = "Playlist")
@Data
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_playlist")
    private String nomePlaylist;

    @ManyToOne
    @JoinColumn(name = "id_account")
    private Account account;

    @Column(name = "data_creazione")
    private Timestamp data_creazione;

    @ManyToMany
    @JoinTable(
        name = "canzoninellaplaylist",
        joinColumns = @JoinColumn(name = "id_playlist"),
        inverseJoinColumns = @JoinColumn(name = "id_canzione")
    )
    private ArrayList<Canzone> canzoni;


    
}
