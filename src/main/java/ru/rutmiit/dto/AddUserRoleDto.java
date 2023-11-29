package ru.rutmiit.dto;


import jakarta.validation.constraints.NotNull;
import ru.rutmiit.models.Enum.RoleEnum;

public class AddUserRoleDto {
    private RoleEnum roleEnum;

    @NotNull(message = "Please choose a role!")
    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }
}