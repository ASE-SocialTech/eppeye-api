package com.socialtech.eppeye.account.application.services;

import com.socialtech.eppeye.account.infrastructure.repositories.IUserRepository;
import com.socialtech.eppeye.shared.util.Utilities;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final IUserRepository userRepository;

    public CustomUserDetailsService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No se encontró al usuario con el email: " + email));

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(Utilities.mapRoles(user.getRoles()))
                .disabled(user.getStatus().equals("disabled"))
                .build();
    }
}
