package ru.rutmiit.models.Enum;

public enum EngineEnum {
    GASOLINE(0),
    DIESEL(1),
    ELECTRIC(2),
    HYBRID(3);

    private int num;

    EngineEnum(int num){
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
