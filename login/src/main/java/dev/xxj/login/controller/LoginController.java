package dev.xxj.login.controller;

import dev.xxj.login.model.LoginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @PostMapping
    public ResponseEntity<String> login(@RequestBody @Validated LoginDto user) {
        if (user.username().equals("admin") && user.password().equals("admin")) {
            return ResponseEntity.ok("Login successfully!");
        }
        return ResponseEntity.badRequest().body("Login failed!!!");
    }
}
