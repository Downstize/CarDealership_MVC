package ru.rutmiit.models.Enum;

public enum RoleEnum {
    User(0),
    Admin(1);

    private int num;

    RoleEnum(int num){
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
