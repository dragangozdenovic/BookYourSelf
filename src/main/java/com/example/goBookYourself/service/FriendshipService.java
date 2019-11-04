package com.example.goBookYourself.service;

import com.example.goBookYourself.model.Friendship;
import com.example.goBookYourself.model.User;
import com.example.goBookYourself.repository.FriendshipRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FriendshipService {

    @Autowired
    FriendshipRepository repository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Friendship> findAll() {
        return repository.findAll();
    }

    public List<Friendship> findMyFriendships(User user) {
       return repository.findAllByFirstFriendOrSecondFriend(user, user);

    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public Friendship save(Friendship friendship){
        Friendship  friendship1 = repository.save(friendship);
        return friendship1;
    }

    public List<Friendship> findAllFriendshipRequests(User receiver){
        return repository.findAllBySecondFriendAndValid(receiver, false);
    }

    public Friendship findFriendship(User sender, User receiver){
        return repository.findByFirstFriendAndSecondFriend(sender, receiver);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public Integer deleteFriendship(User sender, User receiver){
        logger.info("> remove");
        Integer integer = repository.deleteByFirstFriendAndSecondFriend(sender, receiver);
        logger.info("< remove");
        return integer;
    }

}
