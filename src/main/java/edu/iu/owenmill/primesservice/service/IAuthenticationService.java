package edu.iu.rajdhake.primesservice.service;

import java.io.IOException;

import edu.iu.rajdhake.primesservice.model.Customer;

public interface IAuthenticationService {
    boolean register(Customer customer) throws IOException;
    boolean login(String username, String password) throws IOException;
}
