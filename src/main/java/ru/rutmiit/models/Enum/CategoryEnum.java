package ru.rutmiit.models.Enum;

public enum CategoryEnum {
    Car(0),
    Buss(1),
    Truck(2),
    Motorcycle(3);

    private int num;

    CategoryEnum(int num){
        this.num = num;
    }

    public int getNum() {
        return num;
    }


}


