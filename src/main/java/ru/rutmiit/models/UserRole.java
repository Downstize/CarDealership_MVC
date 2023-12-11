package ru.rutmiit.models;


import jakarta.persistence.*;
import ru.rutmiit.models.BaseEntity.BaseEntityUUID;
import ru.rutmiit.models.Enum.RoleEnum;


@Entity
@Table(name = "user_role")
public class UserRole extends BaseEntityUUID {

    private RoleEnum roleEnum;

    public UserRole (RoleEnum roleEnum){
        this.roleEnum = roleEnum;
    }

    public UserRole() {

    }


    @Enumerated(EnumType.STRING)
    @Column(name = "role", unique = true)
    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }

    public String toString() {
        return roleEnum.toString();
    }
}
