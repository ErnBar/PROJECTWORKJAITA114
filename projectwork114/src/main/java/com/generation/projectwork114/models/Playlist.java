package com.generation.projectwork114.models;


import java.sql.Timestamp;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Playlist extends Entity {
    private String nome_playlist;

    private Account id_account;

    private Timestamp dataCreazione;
    
    private List<Canzone> canzoni;//Per la tabella associativa
}
