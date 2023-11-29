package ru.rutmiit.dto.dtooo;


import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import ru.rutmiit.models.Enum.EngineEnum;
import ru.rutmiit.models.Enum.TransmissionEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AddOfferDto {
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

    @NotEmpty(message = "Name of model cannot be null or empty!")
    @Size(min = 2, message = "Name of model should be at least 2 characters long!")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @NotEmpty(message = "User name cannot be null or empty!")
    @Size(min = 2, message = "User name should be at least 2 characters long!")
    public String getUsers() {
        return user;
    }

    public void setUsers(String user) {
        this.user = user;
    }

    @NotEmpty(message = "Description must not be null or empty!")
    @Size(min = 10, message = "Description must be at least 10 characters!")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull(message = "Please choose an engine type!")
    public EngineEnum getEngineEnum() {
        return engineEnum;
    }

    public void setEngineEnum(EngineEnum engineEnum) {
        this.engineEnum = engineEnum;
    }

    @NotEmpty(message = "Last name cannot be null or empty!")
    @Size(min = 10, message = "Image URL must be minimum two characters!")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NotNull(message = "Mileage cannot be null or empty!")
    @Min(value = 1, message = "Mileage must be a positive number!")
    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @NotNull(message = "Price cannot be null or empty!")
    @Min(value = 1, message = "Price must be a positive number!")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @NotNull(message = "Please choose a transmission!")
    public TransmissionEnum getTransmissionEnum() {
        return transmissionEnum;
    }

    public void setTransmissionEnum(TransmissionEnum transmissionEnum) {
        this.transmissionEnum = transmissionEnum;
    }

    @NotNull(message = "Year cannot be null or empty!")
    @Min(value = 1, message = "Year must be a positive number!")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @NotNull(message = "Date of model create cannot be null or empty!")
    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    @NotEmpty(message = "Seller name must not be null or empty!")
    @Size(min = 3, message = "Seller name must be at least 3 characters!")
    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

}