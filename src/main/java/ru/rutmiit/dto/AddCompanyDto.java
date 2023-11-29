package ru.rutmiit.dto;

import ru.rutmiit.utils.validation.UniqueCompanyName;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class AddCompanyDto {

    @UniqueCompanyName
    private String name;
    private String town;
    private String description;
    private Double budget;

    @NotEmpty(message = "Company name must not be null or empty!")
    @Size(min = 2, max = 10, message = "Company name must be between 2 and 10 characters!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty(message = "Town name must not be null or empty!")
    @Size(min = 2, max = 10, message = "Town name must be between 2 and 10 characters!")
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @NotEmpty(message = "Description must not be null or empty!")
    @Size(min = 10, message = "Description must be at least 10 characters!")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Min(value = 1, message = "Budget must be a positive number!")
    @NotNull(message = "Budget must not be null or empty!")
    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }
}
