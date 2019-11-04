package com.example.goBookYourself.controller;

import com.example.goBookYourself.DTO.UserDTO;
import com.example.goBookYourself.model.Friendship;
import com.example.goBookYourself.model.User;
import com.example.goBookYourself.model.UserTokenState;
import com.example.goBookYourself.security.TokenUtils;
import com.example.goBookYourself.security.auth.JwtAuthenticationRequest;
import com.example.goBookYourself.service.FriendshipService;
import com.example.goBookYourself.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/auth")
public class AuthenticationController {

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userDetailsService;

    @Autowired
    private FriendshipService friendshipService;

    @RequestMapping(method = RequestMethod.GET, value = "/profile", produces = "application/json")
    public ResponseEntity<UserDTO> getUser(HttpServletRequest request) {

        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        User me = userDetailsService.getUser(username);

        if(me == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(new UserDTO(me), HttpStatus.OK);
    }

    @RequestMapping(value = "/allNonFriends", method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> getAllUsersButMe(HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        User me = userDetailsService.getUser(username);

        List<Friendship> friendships = friendshipService.findMyFriendships(me);
        List<User> users = userDetailsService.findAll();
        List<UserDTO> usersDTO = new ArrayList<>();

        for (User u : users) {
            boolean valid = true;

            for (Friendship f : friendships) {
                if (f.getFirstFriend().getUsername().equals(u.getUsername()) ||
                        f.getSecondFriend().getUsername().equals(u.getUsername()))
                    valid = false;
            }
            if(!u.getUsername().equals(username) && valid)
                usersDTO.add(new UserDTO(u));
        }
        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException, IOException {

        System.out.println(authenticationRequest.getUsername() + " " + authenticationRequest.getPassword());

        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()));


        System.out.println("authorities " + authentication.getAuthorities());
        System.out.println("credentials " + authentication.getCredentials());
        // Ubaci username + password u kontext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        System.out.println("security context " + SecurityContextHolder.getContext().getAuthentication().getCredentials());

        // Kreiraj token
        User user = (User) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getUsername());
        int expiresIn = tokenUtils.getExpiredIn();

        // Vrati token kao odgovor na uspesno autentifikaciju
        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
        }catch(BadCredentialsException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

//    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
//    public ResponseEntity<?> refreshAuthenticationToken(HttpServletRequest request) {
//
//        String token = tokenUtils.getToken(request);
//        String username = this.tokenUtils.getUsernameFromToken(token);
//        User user = (User) this.userDetailsService.loadUserByUsername(username);
//
//        if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
//            String refreshedToken = tokenUtils.refreshToken(token);
//            int expiresIn = tokenUtils.getExpiredIn();
//
//            return ResponseEntity.ok(new UserTokenState(refreshedToken, expiresIn));
//        } else {
//            UserTokenState userTokenState = new UserTokenState();
//            return ResponseEntity.badRequest().body(userTokenState);
//        }
//    }

    @RequestMapping(value = "/changeInfo", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<UserDTO> changeUserInfo(HttpServletRequest request, @RequestBody UserDTO userDTO) {
        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        User user = userDetailsService.getUser(username);
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setCity(userDTO.getCity());
        user.setNumber(userDTO.getNumber());

        userDetailsService.saveWithoutEncode(user);

        return new ResponseEntity<>(new UserDTO(user),HttpStatus.OK);
    }

    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger, HttpServletRequest request) {

        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        System.out.println(passwordChanger.newPassword);
        System.out.println(passwordChanger.oldPassword);

        userDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword, username);

        System.out.println("USAO SAM OVDE");

        Map<String, String> result = new HashMap<>();
        result.put("result", "success");
        return ResponseEntity.accepted().body(result);
    }

    public static class PasswordChanger {
        public String oldPassword;
        public String newPassword;
    }
}