package ru.rutmiit.dto.dtooo;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import ru.rutmiit.models.Enum.CategoryEnum;

import java.time.LocalDateTime;

public class AddModelDto {
    private String id;
    private AddBrandDto brand;
    private String name;
    private CategoryEnum categoryEnum;
    private String imageURL;
    private int startYear;
    private int endYear;
    private LocalDateTime created;
    private LocalDateTime modified;

    protected AddModelDto() {};

    public AddModelDto(String id, AddBrandDto brand, String name, CategoryEnum categoryEnum, String imageURL, int startYear,
                       int endYear, LocalDateTime created, LocalDateTime modified) {
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.categoryEnum = categoryEnum;
        this.imageURL = imageURL;
        this.startYear = startYear;
        this.endYear = endYear;
        this.created = created;
        this.modified = modified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AddBrandDto getBrand() {
        return brand;
    }

    public void setBrand(AddBrandDto brand) {
        this.brand = brand;
    }

    @NotNull
    @NotEmpty
    @Length(min = 2, message = "Name must be minimum two characters!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEnum getCategoryEnum() {
        return categoryEnum;
    }

    public void setCategoryEnum(CategoryEnum categoryEnum) {
        this.categoryEnum = categoryEnum;
    }

    @NotNull
    @NotEmpty
    @Length(min = 10, message = "Image URL must be minimum two characters!")
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

}