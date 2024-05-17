package com.generation.projectwork114.controllers;

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
import com.generation.projectwork114.services.ServiceAccount;

import jakarta.servlet.http.HttpSession;

@Controller
public class UtenteController {

    @Autowired
    private ServiceAccount serviceAccount;

    @Autowired
    ApplicationContext applicationContext;


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
    
}
