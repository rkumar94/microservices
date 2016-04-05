package juja.microservices.gamification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.juja.gamification.model.entity.User;
import ua.com.juja.gamification.model.repository.UserRepository;

import java.util.List;

@Component
public class ServiceImpl implements Service {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String createUser(String userName){
        return userRepository.createUser(userName);
    }

    @Override
    public User getUser(String UUID) {
        return userRepository.getUser(UUID);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers();
    }


}
