package com.inboxapp.inboxapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class InboxappApplication {

    public static void main(String[] args) {
        SpringApplication.run(InboxappApplication.class, args);
    }

    @RestController
    public class UserController {

        @GetMapping("/user")
        public String user(@AuthenticationPrincipal OAuth2User principal) {
            if (principal == null) {
                return "User not authenticated";
            }
            System.out.println(principal);
            return principal.getAttribute("name"); // Returns the user's name
        }
    }
}
