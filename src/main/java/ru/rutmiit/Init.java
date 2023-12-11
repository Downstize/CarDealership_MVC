package ru.rutmiit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.rutmiit.models.Enum.RoleEnum;
import ru.rutmiit.models.User;
import ru.rutmiit.models.UserRole;
import ru.rutmiit.repositories.UserRepository;
import ru.rutmiit.repositories.UserRoleRepository;

import java.util.HashSet;
import java.util.List;

@Component
public class Init implements CommandLineRunner {
    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final String defaultPassword;

    public Init(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, @Value("${app.default.password}") String defaultPassword) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.defaultPassword = defaultPassword;
    }

    @Override
    public void run(String... args) throws Exception {
        initRoles();
        initUsers();
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            var moderatorRole = new UserRole(RoleEnum.MODERATOR);
            var adminRole = new UserRole(RoleEnum.ADMIN);
            var normalUserRole = new UserRole(RoleEnum.USER);
            userRoleRepository.save(moderatorRole);
            userRoleRepository.save(adminRole);
            userRoleRepository.save(normalUserRole);
        }
    }

    private void initUsers() {
        if (userRepository.count() == 0) {
            initAdmin();
            initModerator();
            initNormalUser();
        }
    }

    private void initAdmin(){
       var adminRole = userRoleRepository.
                findByRoleEnum(RoleEnum.ADMIN).orElseThrow();

       var adminUser = new User("downstize", passwordEncoder.encode(defaultPassword), "swankydid@gmail.com", "Vyacheslav", "Terekhov", 21);
       adminUser.setRole(List.of(adminRole));

       userRepository.save(adminUser);
    }

    private void initModerator(){

        var moderatorRole = userRoleRepository.
                findByRoleEnum(RoleEnum.MODERATOR).orElseThrow();

        var moderatorUser = new User("venceslao", passwordEncoder.encode(defaultPassword), "venceslao@gmail.com", "Venceslao", "Moderation", 25);
        moderatorUser.setRole(List.of(moderatorRole));

        userRepository.save(moderatorUser);
    }

    private void initNormalUser(){
        var userRole = userRoleRepository.
                findByRoleEnum(RoleEnum.USER).orElseThrow();

        var normalUser = new User("user", "123456789", "user@gmail.com", "User", "Userovich", 34);
        normalUser.setRole(List.of(userRole));

        userRepository.save(normalUser);
    }
}
