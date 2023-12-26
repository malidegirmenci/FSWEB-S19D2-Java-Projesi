package com.workintech.controller;

import com.workintech.entity.Member;
import com.workintech.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public Member register(@RequestBody Member member){
        return authenticationService.register(member.getEmail(),member.getPassword());
    }
}
