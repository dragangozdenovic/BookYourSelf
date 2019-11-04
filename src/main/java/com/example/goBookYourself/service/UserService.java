package com.example.goBookYourself.service;

import com.example.goBookYourself.model.User;
import com.example.goBookYourself.model.VerificationToken;
import com.example.goBookYourself.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Autowired
    EmailService emailService;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User getUser(String username){
        return repository.findByUsernameAndEnabled(username, true);
    }

    public User getUserById(Integer id){
        return repository.findById(id);
    }

    public void remove(String username) {
        repository.deleteByUsernameIgnoreCase(username);
    }

    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public User saveWithoutEncode(User user){
        return repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsernameAndEnabled(username, true);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return user;
        }
    }

    public void changePassword(String oldPassword, String newPassword, String username) {
        User user = (User) loadUserByUsername(username);

        if(passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            repository.save(user);
        }
    }

    public void completeRegistration(String username){

        User user = (User) loadUserByUsername(username);

        VerificationToken token = new VerificationToken();
        token.setUser(user.getId());
        token.setExpiryDate(token.calculateExpiryDate((int)new Date().getTime()));
        token.setToken(UUID.randomUUID().toString());

        verificationTokenService.save(token);

        String tokenValue = token.getToken();

        try {
            emailService.sendMail(user, tokenValue);
        } catch (Exception e) {
            System.out.println("Greska prilikom slanja emaila: " + e.getMessage());
        }
    }
}
