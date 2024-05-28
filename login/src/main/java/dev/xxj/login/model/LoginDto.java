package dev.xxj.login.model;

import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for user login
 */
public record LoginDto(@Size(max = 50) String username,
                       @Size(max = 50) String password) implements Serializable {
}