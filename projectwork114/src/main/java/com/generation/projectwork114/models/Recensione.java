package com.generation.projectwork114.models;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Recensione extends Entity {
    private Account id_utente;
    private Album id_album;
    private int valutazione;
}