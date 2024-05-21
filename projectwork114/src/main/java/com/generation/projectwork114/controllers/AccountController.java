package com.generation.projectwork114.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.generation.projectwork114.models.Account;
import com.generation.projectwork114.models.Artista;
import com.generation.projectwork114.services.ServiceAccount;
import com.generation.projectwork114.services.ServiceArtista;

import jakarta.servlet.http.HttpSession;

@Controller
public class AccountController {

    @Autowired
    private ServiceAccount serviceAccount;

    @Autowired
    ApplicationContext applicationContext;

    
    @Autowired
    private ServiceArtista serviceArtista;


    @GetMapping("/formRegistrazione")
    public String register(Model model) {
        Account a= applicationContext.getBean("account",Account.class);
        model.addAttribute("utente", a);
        return "registrazioneUtente.html";
    }
    

    //metodo per registrare un utente
    @PostMapping("/register")
    public String registerUser(
        Model model,
        @RequestParam("confermaPassword")String confermaPassword, 
        @ModelAttribute Account utente,
        HttpSession session,
        @RequestParam Map<String, String> allParams){
            //Verifico che lo username non sia già presente nel database
            if(serviceAccount.findByUserName(utente.getUsername())) {
                model.addAttribute("error", "Username già presente");
                return "formLogin.html";
            }

            if (serviceAccount.findByEmail(utente.getEmail())) {
                model.addAttribute("error", "Email già presente");
                return "formLogin.html";
                
            }
            

            //Verifico che la password sia lunga almeno 8 caratteri
            if (utente.getPassword().length() < 8) {
                model.addAttribute("error", "La password deve essere lunga almeno 8 caratteri");
                return "registrazioneUtente.html";
            }


            if (!utente.getPassword().equals(confermaPassword)) {
                model.addAttribute("error", "Le password non coincidono");
                return "registrazioneUtente.html";
            }
            //salvo l'utente nel database
            serviceAccount.add(allParams);
            //salvo l'utente in sessione
            session.setAttribute("utente", utente);
            return "confermaRegistrazione.html";
    }
    
    
    @GetMapping("/formregistrazione-artista")
    public String registerArtista(Model model) {
        Artista a= applicationContext.getBean("artista",Artista.class);
        model.addAttribute("cantante", a);
        return "registrazioneArtista.html";
    }
    

    //metodo per registrare un artista
    @PostMapping("/register-artista")
    public String registerArtista(
        Model model,
        @RequestParam("confermaPassword")String confermaPassword, 
        @ModelAttribute Artista utente,
        HttpSession session,
        @RequestParam Map<String, String> allParams){
            if(serviceAccount.findByUserName(utente.getUsername())) {
                model.addAttribute("error", "Username già presente");
                return "formLogin.html";
            }


            if (serviceAccount.findByEmail(utente.getEmail())) {
                model.addAttribute("error", "Email già presente");
                return "formLogin.html";
                
            }

            
            if (utente.getPassword().length() < 8) {
                model.addAttribute("error", "La password deve essere lunga almeno 8 caratteri");
                return "registrazioneArtista.html";
            }


            if (!utente.getPassword().equals(confermaPassword)) {
                model.addAttribute("error", "Le password non coincidono");
                return "registrazioneArtista.html";
            }

            
            
            serviceArtista.add(allParams);
           
            session.setAttribute("utente", utente);
            return "confermaRegistrazione.html";
    }

    @GetMapping("/pannello-controllo")
    public String pannelloControlloUtente(HttpSession session,Model model){
        List<Account> ris = serviceAccount.getAccounts();
        model.addAttribute("accounts", ris);
        boolean isAdmin = false;
        Object utente = session.getAttribute("utente");
        
        if (utente != null && utente instanceof Account) {
            Account u = (Account) utente;
            if (u.getRuolo().equalsIgnoreCase("admin")) {
                isAdmin = true;
            }
        }
        model.addAttribute("isAdmin", isAdmin);
        return "PannelloControlloUtente.html";
    }
    
    @PostMapping("/modifica-account")
    public String modificaAccount(@RequestParam Map<String, String> params){

        serviceAccount.update(params);
        return "redirect:/pannello-controllo";

    }

    @GetMapping("/elimina-account")
    public String eliminaAccount(@RequestParam (name = "idAccount", defaultValue = "0")
    Long idAccount, Model model){

        if(idAccount == 0){
            model.addAttribute("error", "Account non trovato");

            return "redirect:/pannello-controllo";
        }

        serviceAccount.delete(idAccount);
        return "redirect:/pannello-controllo";
    }



}
