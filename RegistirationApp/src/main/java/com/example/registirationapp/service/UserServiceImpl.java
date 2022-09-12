package com.example.registirationapp.service;

import com.example.registirationapp.entity.Role;
import com.example.registirationapp.entity.User;
import com.example.registirationapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

private  final UserRepository userRepository;
    @Override
    public User save(User user) {

 User u=new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), List.of(new Role("USER")));
        return userRepository.save(u);
    }
public Optional<User> login(String email,String password){
        Optional<User> optional=userRepository.findByEmailAndPassword(email, password);
        return optional;
}

}
