package ru.rutmiit.models.entities;

import ru.rutmiit.models.enums.EducationLevel;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "employees")

public class Employee extends ru.rutmiit.models.entities.BaseEntit {

    private LocalDate birthDate;

    private EducationLevel educationLevel;

    private String firstName;

    private String jobTitle;

    private String lastName;

    @Column(columnDefinition = "DECIMAL(19,2)", nullable = false)
    private Double salary;

    @ManyToOne
    private Company company;

    @Column(nullable = false)
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    @Column(nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(nullable = false)
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Column(nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(columnDefinition = "DECIMAL(19,2)", nullable = false)
    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @ManyToOne
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
