package ru.rutmiit.repositories.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.rutmiit.models.Model;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ModelRepository extends JpaRepository<Model, String> {
    @Query("SELECT m FROM Model m WHERE m.startYear = :startYear")
    Set<Model> findModelsFromStartYear(@Param("startYear") int startYear);

    Optional<Model> findByName(String name);

    @Modifying
    @Transactional
    void deleteByName(String name);
}
