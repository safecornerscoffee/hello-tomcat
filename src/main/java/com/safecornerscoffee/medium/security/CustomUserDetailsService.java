package com.safecornerscoffee.medium.security;

import com.safecornerscoffee.medium.domain.User;
import com.safecornerscoffee.medium.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        CustomUserDetails userDetails = new CustomUserDetails(user);
        return userDetails;
    }
}
