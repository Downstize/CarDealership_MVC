package ru.rutmiit.models;

import jakarta.persistence.*;
import ru.rutmiit.models.BaseEntity.BaseEntity;
import ru.rutmiit.models.Enum.CategoryEnum;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "model")
public class Model extends BaseEntity {
    @ManyToOne
    private Brand brand;
    private Set<Offer> offers;
    private String name;
    private CategoryEnum categoryEnum;
    private String imageUrl;
    private int startYear;
    private int endYear;

    public Model() {
        offers = new HashSet<>();
    }


    @ManyToOne
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @OneToMany(mappedBy = "model", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }

    @Column(name = "model_name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    public CategoryEnum getCategoryEnum() {
        return categoryEnum;
    }

    public void setCategoryEnum(CategoryEnum categoryEnum) {
        this.categoryEnum = categoryEnum;
    }

    @Column(name = "imageurl", nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(name = "startyear", nullable = false)
    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    @Column(name = "endyear", nullable = false)
    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public String toString() {
        return name;
    }
}
