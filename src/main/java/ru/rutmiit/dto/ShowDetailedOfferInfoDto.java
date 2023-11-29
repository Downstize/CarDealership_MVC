package ru.rutmiit.dto;

import ru.rutmiit.models.Enum.EngineEnum;
import ru.rutmiit.models.Enum.TransmissionEnum;

import java.time.LocalDate;

public class ShowDetailedOfferInfoDto {
    private String model;
    private String user;
    private String description;
    private EngineEnum engineEnum;
    private String imageUrl;
    private int mileage;
    private Double price;
    private TransmissionEnum transmissionEnum;
    private int year;
    private LocalDate created;
    private String seller;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUsers() {
        return user;
    }

    public void setUsers(String user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EngineEnum getEngineEnum() {
        return engineEnum;
    }

    public void setEngineEnum(EngineEnum engineEnum) {
        this.engineEnum = engineEnum;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public TransmissionEnum getTransmissionEnum() {
        return transmissionEnum;
    }

    public void setTransmissionEnum(TransmissionEnum transmissionEnum) {
        this.transmissionEnum = transmissionEnum;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

}
