package ru.rutmiit.models;

import jakarta.persistence.*;
import ru.rutmiit.models.BaseEntity.BaseEntity;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "brands")
public class Brand extends BaseEntity {

    private Set<Model> models;
    private String name;

    public Brand() {
        models = new HashSet<>();
    }


    @OneToMany(mappedBy = "brand", targetEntity = Model.class, cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    public Set<Model> getModels() {
        return models;
    }

    public void setModels(Set<Model> models) {
        this.models = models;
    }

    @Column(unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
