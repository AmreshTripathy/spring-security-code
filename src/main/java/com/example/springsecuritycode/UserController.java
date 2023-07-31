package com.example.springsecuritycode;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Amresh Tripathy
 */

@RestController
@RequestMapping("/home")
public class UserController {

    @GetMapping("/welcome")
    public String getWelcome() {
        return "Hi Welcome to Spring Security";
    }

    @GetMapping("/admin") // This api should be under the admin role only
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getAdmin() {
        return "Hi Admin, you have been authenticated successfully";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String getUser() {
        return "Hi User, You have logged in successfully";
    }

}
