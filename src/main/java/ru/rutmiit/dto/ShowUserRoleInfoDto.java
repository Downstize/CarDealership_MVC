package ru.rutmiit.dto;

import jakarta.validation.constraints.NotNull;
import ru.rutmiit.models.Enum.RoleEnum;

public class ShowUserRoleInfoDto {
    private String roleEnum;

    public String getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(String roleEnum) {
        this.roleEnum = roleEnum;
    }
}
