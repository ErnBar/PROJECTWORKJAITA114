package com.generation.projectwork114.interfaces;

import java.util.List;
import java.util.Map;

import com.generation.projectwork114.models.Account;

public interface IServiceAccount {
    List<Account> getAccounts();

    public void add(Map<String,String> params);

    public Account findById(Long id);

    public void update(Map<String,String> params);

    public void delete(Long id);

    public boolean findByUserName(String username);

    public Account findByUsernameAndPassword(String username, String password);

    public boolean findByEmail(String email);
}
