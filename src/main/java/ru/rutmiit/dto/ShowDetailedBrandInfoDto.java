package ru.rutmiit.dto;


import java.time.LocalDate;

public class ShowDetailedBrandInfoDto {
    private String name;
    private LocalDate created;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }
}
