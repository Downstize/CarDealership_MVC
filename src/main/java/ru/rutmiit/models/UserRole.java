package ru.rutmiit.models;


import jakarta.persistence.*;
import ru.rutmiit.models.BaseEntity.BaseEntityUUID;
import ru.rutmiit.models.Enum.RoleEnum;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_role")
public class UserRole extends BaseEntityUUID {

    private Set<User> users;
    private RoleEnum roleEnum;

    public UserRole() {
        users = new HashSet<>();
    }

    @OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }
}
