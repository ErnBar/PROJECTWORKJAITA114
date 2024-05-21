package com.generation.projectwork114.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.generation.projectwork114.database.Database;
import com.generation.projectwork114.interfaces.IDao;
import com.generation.projectwork114.models.Account;
//import com.generation.projectwork114.models.Artista;
import com.generation.projectwork114.models.Entity;

import lombok.Data;


@Service
@Data
public class DaoAccount implements IDao{

    private final Database database;

    private final ApplicationContext context;

    @Override
    public int add(Entity e) {
        int ris = 0;
        String query = "INSERT INTO account (username, nome, email, password, ruolo) VALUES (?, ?, ?, ?, ?)";
        Account a = context.getBean("account",Account.class);
        if (e instanceof Account) {
            a = (Account) e;
            database.executeUpdate(query, a.getUsername(), a.getNome(), a.getEmail(),
             a.getPassword(), a.getRuolo());
        }
        return ris;
    }

    @Override
    public List<Map<String, String>> read() {
        String query = "SELECT * FROM account";
        return database.executeQuery(query);
    }

    @Override
    public List<Entity> readAll() {
        List<Entity> ris=new ArrayList<>();
        Entity e;
        for(Map<String,String> m : read()) {
            e = context.getBean(Account.class, m);
            ris.add(e);
        }
        return ris;
    }

    @Override
    public void update(Entity e) {
        String query = "UPDATE account SET username = ?, nome = ?, email = ?, password = ?, ruolo = ? WHERE id = ?";
        if (e instanceof Account) {
            Account a = (Account) e;
            database.executeUpdate(query, a.getUsername(), a.getNome(), a.getEmail(),
             a.getPassword(), a.getRuolo(), String.valueOf(a.getId()));
        }
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM account WHERE id = ?";
        database.executeUpdate(query, String.valueOf(id));
    }

    @Override
    public Entity cercaPerId(Long id) {
        String query = "SELECT * FROM account WHERE id = ?";
        List<Map<String, String>> ris = database.executeQuery(query, String.valueOf(id));
        Map<String, String> m = ris.get(0);
        Account a = context.getBean(Account.class, m);
        return a;
    }


    public Map<String, String> autentica(String username, String password) {
        String query = "SELECT * FROM account WHERE username = ? AND password = ?";
        List<Map<String, String>> ris = database.executeQuery(query, username, password);
        if (ris.size() == 0) {
            return null;
        }
        return ris.get(0);
    }



     public boolean userExists(String username) {
        String query = "select * from account where username = ?";
        List<Map<String, String>> ris = database.executeQuery(query, username);
        return ris.size() > 0;
    }


    public boolean emailExists(String email) {
        String query = "select * from account where email = ?";
        List<Map<String, String>> ris = database.executeQuery(query, email);
        return ris.size() > 0;
    }
    
}
