package com.generation.projectwork114.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.generation.projectwork114.models.Canzone;
import com.generation.projectwork114.services.ServiceAccount;
import com.generation.projectwork114.services.ServiceAlbum;
import com.generation.projectwork114.services.ServiceCanzone;

import jakarta.servlet.http.HttpSession;

@Controller
public class SongController {

    @Autowired
    private ServiceCanzone serviceCanzone;

    @Autowired
    private ServiceAccount serviceAccount;

    @Autowired
    private ServiceAlbum serviceAlbum;


    @GetMapping("/song-player")
    public String songPlayer(HttpSession session,Model model){
        List<Canzone> canzoni = serviceCanzone.findByAlbum(1L);
        model.addAttribute("canzoni", canzoni);
        return "songPlayer.html";
    }
    
}
