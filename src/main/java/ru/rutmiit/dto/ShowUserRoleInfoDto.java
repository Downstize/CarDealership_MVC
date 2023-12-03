package ru.rutmiit.dto;

import jakarta.validation.constraints.NotNull;
import ru.rutmiit.models.Enum.RoleEnum;

public class ShowUserRoleInfoDto {
    private String userRole;

    public String getRoleEnum() {
        return userRole;
    }

    public void setRoleEnum(String userRole) {
        this.userRole = userRole;
    }
}
