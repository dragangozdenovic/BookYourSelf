package com.example.goBookYourself.controller;

import com.example.goBookYourself.DTO.FriendshipDTO;
import com.example.goBookYourself.DTO.UserDTO;
import com.example.goBookYourself.model.Friendship;
import com.example.goBookYourself.model.User;
import com.example.goBookYourself.security.TokenUtils;
import com.example.goBookYourself.service.FriendshipService;
import com.example.goBookYourself.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/friendships")
public class FriendshipController {

    @Autowired
    private FriendshipService service;

    @Autowired
    private UserService userService;

    @Autowired
    TokenUtils tokenUtils;


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<FriendshipDTO>> getAllFlights(HttpServletRequest request) {
        List<Friendship> friendships = service.findAll();
        List<FriendshipDTO> friendshipsDTO = new ArrayList<>();

        for (Friendship f : friendships)
            friendshipsDTO.add(new FriendshipDTO(f));
        return new ResponseEntity<>(friendshipsDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/friends", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAllFriends(HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        User user = userService.getUser(username);

        List<Friendship> friendships = service.findMyFriendships(user);
        List<String> friends = new ArrayList<>();

        for (Friendship f : friendships)
            if (f.isValid()) {
                if (!(f.getFirstFriend().getUsername()).equals(username))
                    friends.add(f.getFirstFriend().getUsername());
                else
                    friends.add(f.getSecondFriend().getUsername());
            }

        return new ResponseEntity<>(friends, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Void> addFriend(HttpServletRequest request, @RequestBody String friendUsername) {
        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        User me = userService.getUser(username);
        User friend = userService.getUser(friendUsername);

        Friendship friendship = new Friendship(me, friend);

        if (service.save(friendship) != null)
            return new ResponseEntity<>(HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/accept", method = RequestMethod.PUT)
    public ResponseEntity<Void> acceptFriend(HttpServletRequest request, @RequestBody String friendUsername){
        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        User me = userService.getUser(username);
        User friend = userService.getUser(friendUsername);

        Friendship friendship = service.findFriendship(friend, me);
        friendship.setValid(true);
        service.save(friendship);


        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(value = "/remove", method = RequestMethod.PUT)
    public ResponseEntity<Void> removeFriend(HttpServletRequest request, @RequestBody String friendUsername){
        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        System.out.println(friendUsername);

        User me = userService.getUser(username);
        User friend = userService.getUser(friendUsername);


        if(service.findFriendship(friend, me) != null)
            service.deleteFriendship(friend, me);
        else if(service.findFriendship(me, friend) != null)
            service.deleteFriendship(me, friend);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/friendRequests", method = RequestMethod.GET)
    public ResponseEntity<List<FriendshipDTO>> getRequests(HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        User user = userService.getUser(username);

        List<Friendship> friendships = service.findAllFriendshipRequests(user);
        List<FriendshipDTO> friendshipsDTO = new ArrayList<>();

        for (Friendship f : friendships) {
            friendshipsDTO.add(new FriendshipDTO(f));
        }

        return new ResponseEntity<>(friendshipsDTO, HttpStatus.OK);
    }

}
