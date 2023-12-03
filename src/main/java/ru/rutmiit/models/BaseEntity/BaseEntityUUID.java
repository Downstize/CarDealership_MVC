package ru.rutmiit.models.BaseEntity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class BaseEntityUUID {

    protected String id;


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    public String getId() {
        return id;
    }
    protected void setId(String id) {
        this.id = id;
    }
}
