package com.example.registirationapp.service;

import com.example.registirationapp.entity.User;
import com.example.registirationapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailChecking {
@Autowired
    UserRepository userRepository;
public Optional<User> chcekEmail(User user){
    Optional<User> user1=userRepository.findByEmail(user.getEmail());
    return user1;
}

}
