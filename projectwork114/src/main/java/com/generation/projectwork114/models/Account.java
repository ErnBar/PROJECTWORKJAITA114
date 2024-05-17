package com.generation.projectwork114.models;

import java.sql.Timestamp;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Account extends Entity{

    private String username;
    private String nome;
    private String email;
    private String password;
    private String ruolo;
    private Timestamp data_registrazione;
    
}
