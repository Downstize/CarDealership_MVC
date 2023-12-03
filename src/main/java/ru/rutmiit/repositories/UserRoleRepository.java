package ru.rutmiit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.rutmiit.models.Enum.RoleEnum;
import ru.rutmiit.models.UserRole;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {
    Optional<UserRole> findByRoleEnum(RoleEnum roleEnum);

    @Modifying
    @Transactional
    void deleteByRole(RoleEnum roleEnum);
}