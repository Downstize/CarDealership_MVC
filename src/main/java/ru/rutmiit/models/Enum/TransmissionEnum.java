package ru.rutmiit.models.Enum;

public enum TransmissionEnum {
    MANUAL(0),
    AUTOMATIC(1);

    private int num;

    TransmissionEnum(int num){
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
