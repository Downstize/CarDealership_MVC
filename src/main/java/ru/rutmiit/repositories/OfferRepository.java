package ru.rutmiit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.rutmiit.models.Offer;

import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, String> {

    Optional<Offer> findByYear(int year);

    @Modifying
    @Transactional
    void deleteByPrice(Double price);
}
