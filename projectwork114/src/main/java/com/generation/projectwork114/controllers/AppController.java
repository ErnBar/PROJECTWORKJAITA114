package com.generation.projectwork114.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.generation.projectwork114.models.Account;
import com.generation.projectwork114.services.ServiceAccount;

import jakarta.servlet.http.HttpSession;

@Controller
public class AppController {
    @Autowired
    private ServiceAccount serviceAccount;
    
    @GetMapping("/")
    public String home(HttpSession session,Model model){
        if (session.getAttribute("loggato")==null) {
            return "redirect:/formLogin";
        }else {
            Object utente = session.getAttribute("utente");
            String actualUser="";
            if (utente!=null && utente instanceof Account) {
                Account u = (Account) utente;
                actualUser += u.getUsername();
                // if (u.getRuolo().equalsIgnoreCase("admin")) {
                //     actualUser = "Admin: "+u.getUsername(); 
                // }
                // else if (u.getRuolo().equalsIgnoreCase("artista")) {
                //     actualUser = "Artista: "+u.getUsername();
                // }
            }
        boolean isAdmin = false;
        boolean isUtente = false;
        
        if (utente != null && utente instanceof Account) {
            Account u = (Account) utente;
            if (u.getRuolo().equalsIgnoreCase("admin")) {
                isAdmin = true;
            }
            if (u.getRuolo().equalsIgnoreCase("user")||u.getRuolo().equalsIgnoreCase("artista")) {
                isUtente = true;
            }
        }
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isUtente", isUtente);
            
        model.addAttribute("actualUser", actualUser);
        return "main.html";
        }
    }

    @GetMapping("/formLogin")
    public String login(){
        return "formLogin.html";
    }

    

    @PostMapping("/login")
    public String login(
        @RequestParam("username") String username,
        @RequestParam("password") String password,
        Model model,
        HttpSession session){
            Account utenteLoggato= serviceAccount.findByUsernameAndPassword(username, password);
            if (utenteLoggato==null) {
                model.addAttribute("error", "Credenziali non valide");
                return "formLogin.html";
            }
            else{
                session.setAttribute("loggato", "ok");
                session.setAttribute("utente", utenteLoggato);
                String ruolo= utenteLoggato.getRuolo();
                if (ruolo.equalsIgnoreCase("admin")) {
                    return "redirect:/";
                }else if (ruolo.equalsIgnoreCase("user")){
                    return "redirect:/";
                }else if (ruolo.equalsIgnoreCase("artista")) {
                    return "redirect:/";
                }
                else{
                    session.setAttribute("loggato", null);
                    return "redirect:/";
                }

            }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.setAttribute("loggato", null);
        session.setAttribute("utente", null);
        return "redirect:/";
    }
}
