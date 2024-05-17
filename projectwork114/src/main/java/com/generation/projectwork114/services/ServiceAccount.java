package com.generation.projectwork114.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.generation.projectwork114.dao.DaoAccount;
import com.generation.projectwork114.interfaces.IServiceAccount;
import com.generation.projectwork114.models.Account;
import com.generation.projectwork114.models.Entity;

import lombok.Data;
@Service
@Data
public class ServiceAccount implements IServiceAccount{

    @Qualifier("daoAccount")
    private final DaoAccount daoAccount;

    
    private final ApplicationContext applicationContext;

    @Override
    public List<Account> getAccounts() {
        List<Entity> ris = daoAccount.readAll();
        List<Account> utenti = new ArrayList<>();
        for(Entity e : ris) {
            if(e instanceof Account)
                utenti.add((Account)e);
        }
        return utenti;
    }

    @Override
    public void add(Map<String, String> params) {
        Account u = applicationContext.getBean(Account.class,params);
        daoAccount.add(u);
    }

    @Override
    public Account findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public void update(Map<String, String> params) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public boolean findByUserName(String username) {
        return daoAccount.userExists(username);
    }

    @Override
    public Account findByUsernameAndPassword(String username, String password) {
        Map<String,String> map= daoAccount.autentica(username, password);
        if (map!=null) {
            return applicationContext.getBean(Account.class,map);
        }
        return null;
    }
    
}
