package ru.rutmiit.dto;

import ru.rutmiit.models.enums.EducationLevel;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class AddEmployeeDto {

    private String firstName;

    private String lastName;
    private EducationLevel educationLevel;
    private String companyName;
    private String jobTitle;
    private LocalDate birthDate;
    private Double salary;

    @NotEmpty(message = "First name cannot be null or empty!")
    @Size(min = 2, message = "First name should be at least 2 characters long!")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @NotEmpty(message = "Last name cannot be null or empty!")
    @Size(min = 2, message = "Last name should be at least 2 characters long!")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @NotNull(message = "Please choose an education level!")
    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }
    @NotEmpty(message = "Please choose a company!")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    @NotEmpty(message = "Job title cannot be null or empty!")
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    @NotNull(message = "Birth date cannot be null or empty!")
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    @NotNull(message = "Salary cannot be null or empty!")
    @Min(value = 1, message = "Salary must be a positive number!")
    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
