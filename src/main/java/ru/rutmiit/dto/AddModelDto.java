package ru.rutmiit.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import ru.rutmiit.models.Enum.CategoryEnum;
import ru.rutmiit.utils.validation.UniqueModelName;

import java.time.LocalDate;

public class AddModelDto {

    private String brand;
    @UniqueModelName
    private String name;
    private CategoryEnum categoryEnum;
    private String imageURL;
    private int startYear;
    private int endYear;
//    private LocalDate created;


    @NotEmpty(message = "Name of brand cannot be null or empty!")
    @Size(min = 2, message = "Name of brand should be at least 2 characters long!")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @NotEmpty(message = "Model name cannot be null or empty!")
    @Size(min = 2, message = "Model name should be at least 2 characters long!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "Please choose a category!")
    public CategoryEnum getCategoryEnum() {
        return categoryEnum;
    }

    public void setCategoryEnum(CategoryEnum categoryEnum) {
        this.categoryEnum = categoryEnum;
    }

    @NotEmpty(message = "Last name cannot be null or empty!")
    @Size(min = 10, message = "Image URL must be minimum two characters!")
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @NotNull(message = "Start year cannot be null or empty!")
    @Min(value = 1, message = "Start year must be real!")
    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    @NotNull(message = "End year cannot be null or empty!")
    @Min(value = 1, message = "End year must be real!")
    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }
//
//    @NotNull(message = "Date of model create cannot be null or empty!")
//    public LocalDate getCreated() {
//        return created;
//    }
//
//    public void setCreated(LocalDate created) {
//        this.created = created;
//    }


}