package ru.rutmiit.services;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.rutmiit.repositories.UserRepository;

import java.util.stream.Collectors;

public class AppUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username)
                .map(u -> new User(
                        u.getUserName(),
                        u.getPassword(),
                        u.getRole().stream()
                                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRoleEnum().name()))
                                .collect(Collectors.toList())
                )).orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }
}
