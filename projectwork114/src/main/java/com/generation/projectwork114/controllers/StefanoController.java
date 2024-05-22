package com.generation.projectwork114.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.generation.projectwork114.models.Artista;
import com.generation.projectwork114.services.ServiceAlbum;
import com.generation.projectwork114.services.ServiceArtista;
import com.generation.projectwork114.services.ServiceCanzone;

@Controller
public class StefanoController {

    @Autowired
    private ServiceArtista serviceArtista;

    @Autowired
    private ServiceCanzone serviceCanzone;

    @Autowired
    private ServiceAlbum serviceAlbum;

    @GetMapping("/artistaByNome")
    public String getArtistaByNome(@RequestParam(name="nome", defaultValue = "") String nome, Model model) {
        Artista artista = serviceArtista.findByNome(nome);
        model.addAttribute("artista", artista);
        return "stefano.html";
    }
        
    
}
