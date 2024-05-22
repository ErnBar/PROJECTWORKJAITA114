package com.generation.projectwork114.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
    
}
