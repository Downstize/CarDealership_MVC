package ru.rutmiit.models.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "companies")

public class Company extends ru.rutmiit.models.entities.BaseEntit {
    private Double budget;
    private String description;
    private String name;
    private String town;
    private Set<Employee> employees;

    public Company() {
        employees = new HashSet<>();
    }

    @Column(columnDefinition = "DECIMAL(19,2)", nullable = false)
    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    @Column(columnDefinition = "TEXT", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @OneToMany(mappedBy = "company", targetEntity = Employee.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
