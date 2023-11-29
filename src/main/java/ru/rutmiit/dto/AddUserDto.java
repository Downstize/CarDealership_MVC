package ru.rutmiit.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public class AddUserDto {
    private String role;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String imageURL;
    private LocalDate created;


    @NotNull(message = "UserRole cannot be null or empty!")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @NotEmpty(message = "Model name cannot be null or empty!")
    @Size(min = 2, message = "Model name should be at least 2 characters long!")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @NotEmpty(message = "Password name cannot be null or empty!")
    @Size(min = 2, message = "Password must be minimum two characters!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    @NotEmpty(message = "Last name cannot be null or empty!")
    @Size(min = 10, message = "Image URL must be minimum two characters!")
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageUrl) {
        this.imageURL = imageUrl;
    }

    @NotNull(message = "Date of model create cannot be null or empty!")
    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }


}