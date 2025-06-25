package com.shreyansh.store.users;


/**
 * DTO for {@link com.shreyansh.store.users.User}
 */
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDto {
    private Long id;
    private String name;
    private String email;
}