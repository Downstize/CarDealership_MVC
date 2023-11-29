package ru.rutmiit.models;


import jakarta.persistence.*;
import ru.rutmiit.models.BaseEntity.BaseEntityUUID;
import ru.rutmiit.models.Enum.RoleEnum;

import java.util.List;

@Entity
@Table(name = "user_role")
public class UserRole extends BaseEntityUUID {

    private List<User> users;
    private RoleEnum roleEnum;

    protected UserRole() {
    }

    @OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role", nullable = false)
    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }
}
