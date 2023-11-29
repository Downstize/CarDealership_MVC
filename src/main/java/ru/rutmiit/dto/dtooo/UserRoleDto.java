package ru.rutmiit.dto.dtooo;


import ru.rutmiit.models.Enum.RoleEnum;

public class UserRoleDto {
    private String id;
    private RoleEnum roleEnum;

    protected UserRoleDto() {};

    public UserRoleDto(String id, RoleEnum roleEnum) {
        this.id = id;
        this.roleEnum = roleEnum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }
}