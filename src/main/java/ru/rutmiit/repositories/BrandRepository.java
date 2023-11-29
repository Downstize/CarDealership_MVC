package ru.rutmiit.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.rutmiit.models.Brand;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String> {
    Optional<Brand> findByName(String name);

    @Modifying
    @Transactional
    void deleteByName(String name);
}