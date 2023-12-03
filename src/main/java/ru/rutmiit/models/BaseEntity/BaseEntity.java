package ru.rutmiit.models.BaseEntity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@MappedSuperclass
public abstract class BaseEntity {

    protected String id;

    protected LocalDate created;

    protected LocalDate modified;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    public String getId() {
        return id;
    }
    protected void setId(String id) {
        this.id = id;
    }

    @Column(name = "created")
    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    @Column(name = "modified")
    public LocalDate getModified() {
        return modified;
    }

    public void setModified(LocalDate modified) {
        this.modified = modified;
    }
}
