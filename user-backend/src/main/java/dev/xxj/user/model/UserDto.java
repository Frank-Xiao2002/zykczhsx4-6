package dev.xxj.user.model;

import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
public record UserDto(@Size(max = 50) String username,
                      @Size(max = 50) String password) implements Serializable {
}