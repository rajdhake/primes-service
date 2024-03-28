package edu.iu.rajdhake.primesservice.service;

import java.io.IOException;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.iu.rajdhake.primesservice.model.Customer;
import edu.iu.rajdhake.primesservice.repository.IAuthenticationRepository;

@Service
public class AuthenticationService implements IAuthenticationService, UserDetailsService {
    IAuthenticationRepository authenticationRepository;

    public AuthenticationService(IAuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public boolean register(Customer customer) throws IOException {
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        String passwordEncoded = bc.encode(customer.getPassword());
        customer.setPassword(passwordEncoded);
        return authenticationRepository.save(customer);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Customer customer = authenticationRepository.findByUsername(username);
            if(customer == null) {
                throw new UsernameNotFoundException("");
            }
            return User.withUsername(username).password(customer.getPassword()).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean login(String username, String password) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }
}
