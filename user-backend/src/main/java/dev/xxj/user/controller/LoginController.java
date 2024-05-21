package dev.xxj.user.controller;

import dev.xxj.user.model.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@CrossOrigin
public class LoginController {
    @PostMapping
    public ResponseEntity<String> login(@RequestBody UserDto user) {
        if (user.username().equals("admin") && user.password().equals("admin")) {
            return ResponseEntity.ok("Login successfully!");
        }
        return ResponseEntity.badRequest().body("Login failed!!!");
    }
}
