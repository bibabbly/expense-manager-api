package com.theo.expense_manager.service;

import com.theo.expense_manager.dto.SignupRequest;
import com.theo.expense_manager.entity.User;
import com.theo.expense_manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private  final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public  String registerUser(SignupRequest request){
        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            return "Username already taken.";
        }
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "Email already registered.";
        }


        User user= User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("USER")//default role
                .build();
        userRepository.save(user);

        return "User registered successfully.";
    }


}
