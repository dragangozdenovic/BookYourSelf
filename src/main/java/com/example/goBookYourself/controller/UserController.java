package com.example.goBookYourself.controller;

import com.example.goBookYourself.DTO.AuthorityDTO;
import com.example.goBookYourself.DTO.UserDTO;
import com.example.goBookYourself.model.Authority;
import com.example.goBookYourself.model.User;
import com.example.goBookYourself.model.VerificationToken;
import com.example.goBookYourself.security.TokenUtils;
import com.example.goBookYourself.service.UserService;
import com.example.goBookYourself.service.VerificationTokenService;
import com.example.goBookYourself.model.*;
import com.example.goBookYourself.security.TokenUtils;
import com.example.goBookYourself.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Autowired
    TokenUtils tokenUtils;
    @Autowired
    private AuthorityService authService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private AvioCompanyService avioService;
    @Autowired
    private RentACarService rentaService;

    Map<String,String> errors;

    @RequestMapping(value = "/completeRegister", method = RequestMethod.GET)
    public ResponseEntity<Void> CompleteRegistration(String token, HttpServletResponse httpServletResponse) throws IOException {
        VerificationToken verificationToken = verificationTokenService.findByToken(token);

        User user = service.getUserById(verificationToken.getUser());
        user.getAuthorities().add(new Authority("USER"));
        user.setEnabled(true);
        service.saveWithoutEncode(user);

        httpServletResponse.sendRedirect("http://localhost:4200");

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = service.findAll();
        List<UserDTO> usersDTO = new ArrayList<>();

        for (User u : users) {
            usersDTO.add(new UserDTO(u));
        }

        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }



    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = "/registerUser")
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {

        System.out.println(userDTO.getNumber());

        User user = new User();

        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setCity(userDTO.getCity());
        user.setNumber(userDTO.getNumber());

        user = service.save(user);

        service.completeRegistration(user.getUsername());
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.CREATED);
    }

//    @Transactional
//    @RequestMapping(method = RequestMethod.DELETE, value = "/{username}")
//    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
//        User user = service.getUser(username);
//
//        if (user != null) {
//            service.remove(username);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @Transactional
    @RequestMapping(method = RequestMethod.DELETE, value = "/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        User user = service.getUser(username);

        if (user != null) {
            service.remove(username);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping("/whoami")
    @PreAuthorize("hasRole('USER')")
    public User user(Principal user) {
        System.out.println("usao sam ovde");
        return this.service.getUser(user.getName());
    }

    @RequestMapping(value="/changeAuth",method = RequestMethod.PUT)
    public ResponseEntity<Void> changeAuthority(HttpServletRequest request, @RequestBody UserDTO userDTO)
    {
        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        User admin = service.getUser(username);

        if (admin == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        boolean validAuthority = false;

        for (Authority a : admin.getAuthorities())
            if (a.getName().equals("ADMIN_ADMIN")) {
                validAuthority = true;
                break;
            }

        if (!validAuthority) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        User user = this.service.getUser(userDTO.getUsername());
        System.out.println(user.getUsername());
        List<Authority> auth = user.getAuthorities();
        for(AuthorityDTO au : userDTO.getAuthority())
        {
            auth.add(authService.findByName(au.getName()));
        }

        user.setAuthorities(auth);

        this.service.saveWithoutEncode(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/delAuth",method = RequestMethod.POST)
    public ResponseEntity<Void> dellAuthority(HttpServletRequest request, @RequestBody UserDTO userDTO)
    {
        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        User admin = service.getUser(username);

        if (admin == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        boolean validAuthority = false;

        for (Authority a : admin.getAuthorities())
            if (a.getName().equals("ADMIN_ADMIN")) {
                validAuthority = true;
                break;
            }

        if (!validAuthority) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        User user = this.service.getUser(userDTO.getUsername());

        List<Authority> auth = user.getAuthorities();
        for(AuthorityDTO au : userDTO.getAuthority())
        {
            int index = auth.indexOf(authService.findByName(au.getName()));
            auth.remove(index);
        }
        for(Authority a : auth)
        {
            System.out.println(a.getName());
        }
        user.setAuthorities(auth);

        this.service.saveWithoutEncode(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/changePriv",method = RequestMethod.PUT)
    public ResponseEntity<Void> addPriv (HttpServletRequest request,@RequestBody UserDTO userDTO)
    {
        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        User admin = service.getUser(username);

        if (admin == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        boolean validAuthority = false;

        for (Authority a : admin.getAuthorities())
            if (a.getName().equals("ADMIN_ADMIN")) {
                validAuthority = true;
                break;
            }

        if (!validAuthority) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        User user = this.service.getUser(userDTO.getUsername());
        System.out.println(user.getUsername());
        Hotel h = this.hotelService.findByName(userDTO.getName());
        AvioCompany av = this.avioService.findByName(userDTO.getName());
        //RentACar r = this.rentaService.findByName(userDTO.getName());

        if(h!=null)
        {
            user.setAdmin_hotel(h);

        }
        if(av!=null)
        {
            user.setAvioCompany(av);
        }

        this.service.saveWithoutEncode(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
