package com.generation.projectwork114.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.generation.projectwork114.models.Account;
import com.generation.projectwork114.models.Album;
import com.generation.projectwork114.models.Artista;
import com.generation.projectwork114.models.Canzone;
import com.generation.projectwork114.services.ServiceAlbum;
import com.generation.projectwork114.services.ServiceArtista;
import com.generation.projectwork114.services.ServiceCanzone;

import jakarta.servlet.http.HttpSession;

@Controller
public class StefanoController {

    @Autowired
    private ServiceArtista serviceArtista;

    @Autowired
    private ServiceCanzone serviceCanzone;

    @Autowired
    private ServiceAlbum serviceAlbum;

    @GetMapping("/artista-bynome")
    public String getArtistaByNome(@RequestParam(name="nome", defaultValue = "") String nome, Model model,HttpSession session) {
        Object utente = session.getAttribute("utente");
        boolean isAdmin = false;
        
        Artista artista = serviceArtista.findByNome(nome);
        if (artista == null) {
            return "errore.html";
        }
        if (utente != null && utente instanceof Account) {
            Account u = (Account) utente;
            if (u.getId()==artista.getId()||u.getRuolo().equalsIgnoreCase("admin")) {
                isAdmin = true;
            }
        }
        model.addAttribute("isAdmin", isAdmin);
        boolean isStarry = false;
        if (artista.getNome_artista().equalsIgnoreCase("starry")) {
            List<Album> album = serviceAlbum.findByIdArtista(artista.getId());
            List<Canzone> canzoni = serviceCanzone.findByAlbum(album.get(0).getId());
            model.addAttribute("albumSongs", canzoni);
            isStarry = true;
        }
        model.addAttribute("isStarry", isStarry);
        model.addAttribute("artista", artista);
        return "stefano.html";
    }

    @PostMapping("/modifica-artista")
    public String modificaAccount(@RequestParam Map<String, String> params){
        serviceArtista.update(params);
        return "redirect:/artista-bynome?nome="+params.get("nome_artista");
    }

    @PostMapping("/aggiungi-canzone")
    public String aggiungiCanzone(@RequestParam Map<String, String> params){
        serviceCanzone.addCanzone(params);
        return "redirect:/artista-bynome?nome=Starry";
    }

    @PostMapping("/modifica-canzone")
    public String modificaCanzone(@RequestParam Map<String, String> params){
        serviceCanzone.updateCanzone(params);
        return "redirect:/artista-bynome?nome=Starry";
    }

    @GetMapping("/elimina-canzone")
    public String eliminaCanzone(@RequestParam(name="idCanzone", defaultValue = "0") Long idCanzone, Model model){
        Canzone canzone = serviceCanzone.findById(idCanzone);
        serviceCanzone.deleteCanzone(canzone.getId());
        return "redirect:/artista-bynome?nome=Starry";
    }
        
    
}
