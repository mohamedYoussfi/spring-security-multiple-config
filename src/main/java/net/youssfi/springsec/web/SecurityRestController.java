package net.youssfi.springsec.web;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SecurityRestController {
    @RequestMapping("/profile")
    public Authentication authentication(Authentication authentication){
        return authentication;
    }
}
