package ru.rutmiit.dto;

import jakarta.validation.constraints.*;
import ru.rutmiit.utils.validation.UniqueEmail;
import ru.rutmiit.utils.validation.UniqueUserName;

public class UserRegistrationDto {

    @UniqueUserName
    private String userName;

    private String firstName;

    private String lastName;

    @UniqueEmail
    private String email;


    private int age;


    private String password;


    private String confirmPassword;

    public UserRegistrationDto() {}

    @NotEmpty(message = "User name cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @NotEmpty(message = "First name cannot be null or empty!")
    @Size(min = 5, max = 30)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @NotEmpty(message = "Last name cannot be null or empty!")
    @Size(min = 5, max = 30)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @NotEmpty(message = "Email cannot be null or empty!")
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Min(0)
    @Max(90)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @NotEmpty(message = "Password cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @NotEmpty(message = "Confirm Password cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "UserRegistrationDTO{" +
                "userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
