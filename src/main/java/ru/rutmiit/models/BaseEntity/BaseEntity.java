package ru.rutmiit.models.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@MappedSuperclass
public abstract class BaseEntity {

    protected String id;

    protected LocalDate created;

    protected LocalDate modified;

    @Id
    @GeneratedValue(generator = "uuid-string")
    @GenericGenerator(name = "uuid-string",
            strategy = "org.hibernate.id.UUIDGenerator")
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
