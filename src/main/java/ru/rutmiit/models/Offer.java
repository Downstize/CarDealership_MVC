package ru.rutmiit.models;


import jakarta.persistence.*;
import ru.rutmiit.models.BaseEntity.BaseEntity;
import ru.rutmiit.models.Enum.EngineEnum;
import ru.rutmiit.models.Enum.TransmissionEnum;

import java.math.BigDecimal;

@Entity
@Table(name = "offer")
public class Offer extends BaseEntity {

    private Model model;
    private User users;
    private String description;
    private EngineEnum engineEnum;
    private String imageURL;
    private int mileage;
    private Double price;
    private TransmissionEnum transmissionEnum;
    private int year;
    private String seller;

    @ManyToOne
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @ManyToOne
    public User getUsers(){return users;}

    public void setUsers(User users){ this.users = users;}

    @Column(name = "description", nullable = false ,columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "engine", nullable = false)
    public EngineEnum getEngineEnum() {
        return engineEnum;
    }

    public void setEngineEnum(EngineEnum engineEnum) {
        this.engineEnum = engineEnum;
    }

    @Column(name = "imagine_url", nullable = false, columnDefinition = "TEXT")
    public String getImageUrl() {
        return imageURL;
    }

    public void setImageUrl(String imageUrl) {
        this.imageURL = imageUrl;
    }

    @Column(name = "mileage", nullable = false, columnDefinition = "INT")
    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Column(name = "price", nullable = false, columnDefinition = "DECIMAL(19,2)")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "transmission", nullable = false)
    public TransmissionEnum getTransmissionEnum() {
        return transmissionEnum;
    }

    public void setTransmissionEnum(TransmissionEnum transmissionEnum) {
        this.transmissionEnum = transmissionEnum;
    }

    @Column(name = "year", nullable = false, columnDefinition = "INT")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Column(name = "seller", nullable = false)
    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }


}