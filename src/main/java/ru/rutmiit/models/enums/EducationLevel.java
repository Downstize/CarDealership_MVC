package ru.rutmiit.models.enums;

public enum EducationLevel {
    Master(1), Bachelor(2), Secondary(3);

    private int value;

    EducationLevel(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
