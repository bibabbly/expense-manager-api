package com.theo.expense_manager.controller;

import com.theo.expense_manager.dto.SignupRequest;
import com.theo.expense_manager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    private ResponseEntity<String> register(@RequestBody SignupRequest request){
        String response= userService.registerUser(request);
        return ResponseEntity.ok(response);
    }
}
