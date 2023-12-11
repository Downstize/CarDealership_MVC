package ru.rutmiit.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.rutmiit.dto.UserRegistrationDto;
import ru.rutmiit.models.Enum.RoleEnum;
import ru.rutmiit.models.User;
import ru.rutmiit.repositories.UserRepository;
import ru.rutmiit.repositories.UserRoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;


    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    public void register(UserRegistrationDto registrationDTO) {
        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            throw new RuntimeException("passwords.match");
        }

        Optional<User> byEmail = this.userRepository.findByEmail(registrationDTO.getEmail());

        if (byEmail.isPresent()) {
            throw new RuntimeException("email.used");
        }

        var userRole = userRoleRepository.
                findByRoleEnum(RoleEnum.USER).orElseThrow();

        User user = new User(
                registrationDTO.getUserName(),
                passwordEncoder.encode(registrationDTO.getPassword()),
                registrationDTO.getEmail(),
                registrationDTO.getFirstName(),
                registrationDTO.getLastName(),
                registrationDTO.getAge()
        );

        user.setRole(List.of(userRole));

        this.userRepository.save(user);
    }

    public User getUser(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName + " was not found!"));
    }
}
