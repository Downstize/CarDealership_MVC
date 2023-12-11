package ru.rutmiit.models;


import jakarta.persistence.*;
import ru.rutmiit.models.BaseEntity.BaseEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User  extends BaseEntity implements Serializable {

    private List<UserRole> role;
    private Set<Offer> offer;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private boolean isActive = true;
    private String imageUrl;

    public User() {
        this.offer = new HashSet<>();
        this.role = new ArrayList<>();
    }

    public User(String username, String password, String email, String firstName, String lastName, int age) {
        this();

        this.userName = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public List<UserRole> getRole() {
        return role;
    }

    public void setRole(List<UserRole> role) {
        this.role = role;
    }

    @Column(name = "user_name", nullable = false)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "is_active", nullable = false)
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Column(name = "image_url", columnDefinition = "TEXT")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @OneToMany(mappedBy = "users", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    public Set<Offer> getOffer() {
        return offer;
    }

    public void setOffer(Set<Offer> offer) {
        this.offer = offer;
    }

    public String toString() {
        return  userName;
    }

    @Column(name = "email", unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
