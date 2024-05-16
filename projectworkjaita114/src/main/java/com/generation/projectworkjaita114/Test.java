package com.generation.projectworkjaita114;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.generation.projectworkjaita114.models.Account;
import com.generation.projectworkjaita114.repository.AccountRepository;

@Component
public class Test implements CommandLineRunner{


    private Logger logger = LoggerFactory.getLogger(Test.class);

    @Autowired
    private AccountRepository accountRepository;



    @Override
    public void run(String... args) throws Exception {
        Account account = new Account();
        account.setUsername("admin");
        account.setNome("pino");
        account.setEmail("email@example1.com");
        account.setPassword("password");
        account.setRuolo("admin");
        account.setData_registrazione(new java.sql.Timestamp(System.currentTimeMillis()));
        accountRepository.save(account);

        ArrayList<Account> accounts= new ArrayList<>();
        accountRepository.findAll().forEach(accounts::add);
        for(Account a: accounts){
            logger.info("Account :"+a.toString());
        }
    }

    
    
}
