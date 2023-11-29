package ru.rutmiit.services.serv;


import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.rutmiit.dto.dtooo.AddOfferDto;
import ru.rutmiit.dto.dtooo.ShowDetailedBrandInfoDto;
import ru.rutmiit.dto.dtooo.ShowDetailedOfferInfoDto;
import ru.rutmiit.models.Brand;
import ru.rutmiit.models.Offer;
import ru.rutmiit.repositories.repo.OfferRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private final ModelMapper modelMapper;
    private final OfferRepository offerRepository;

    public OfferService(ModelMapper modelMapper, OfferRepository offerRepository) {
        this.modelMapper = modelMapper;
        this.offerRepository = offerRepository;
    }


    public void register(AddOfferDto offer) {

                Offer b = modelMapper.map(offer, Offer.class);
            if (b.getId() == null || findOffer(b.getId()).isEmpty()) {
                 modelMapper.map(offerRepository.save(b), AddOfferDto.class);
            }

    }


    public List<AddOfferDto> getAll() {
        return offerRepository.findAll().stream().map((s) -> modelMapper.map(s, AddOfferDto.class)).collect(Collectors.toList());
    }


    public Optional<AddOfferDto> findOffer(String id) {
        return Optional.ofNullable(modelMapper.map(offerRepository.findById(id), AddOfferDto.class));
    }

    public ShowDetailedOfferInfoDto offerDetails(int yearOfOffer) {
        return modelMapper.map(offerRepository.findByYear(yearOfOffer).orElse(null), ShowDetailedOfferInfoDto.class);
    }

    public void removeOffer(Double price) {
        offerRepository.deleteByPrice(price);
    }


    public AddOfferDto update(AddOfferDto offer) {
        Optional<Offer> existingOffer = offerRepository.findByYear(offer.getYear());
        if (existingOffer.isPresent()) {
            return modelMapper.map(offerRepository.save(modelMapper.map(offer, Offer.class)), AddOfferDto.class);
        } else {
            throw new DataIntegrityViolationException("Exception of update");
        }
    }
}