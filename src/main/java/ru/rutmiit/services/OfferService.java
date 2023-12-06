package ru.rutmiit.services;


import org.modelmapper.ModelMapper;
import org.springframework.cglib.core.Local;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.rutmiit.dto.AddModelDto;
import ru.rutmiit.dto.AddOfferDto;
import ru.rutmiit.dto.ShowDetailedOfferInfoDto;
import ru.rutmiit.dto.ShowOfferInfoDto;
import ru.rutmiit.models.Brand;
import ru.rutmiit.models.Model;
import ru.rutmiit.models.Offer;
import ru.rutmiit.models.User;
import ru.rutmiit.repositories.ModelRepository;
import ru.rutmiit.repositories.OfferRepository;
import ru.rutmiit.repositories.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private final ModelMapper modelMapper;
    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;

    private final UserRepository userRepository;

    public OfferService(ModelMapper modelMapper, OfferRepository offerRepository, ModelRepository modelRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
        this.userRepository = userRepository;
    }


    public void register(AddOfferDto offer) {
        Offer of = modelMapper.map(offer, Offer.class);
        of.setCreated(LocalDate.now());
        of.setModel(modelRepository.findByName(offer.getModel()).orElse(null));
        of.setUsers(userRepository.findByUserName(offer.getUsers()).orElse(null));
        offerRepository.saveAndFlush(of);
    }

    public List<ShowOfferInfoDto> getAll() {
        return offerRepository.findAll().stream().map((offer) -> modelMapper.map(offer, ShowOfferInfoDto.class)).collect(Collectors.toList());
    }


    public Optional<AddOfferDto> findOffer(String id) {
        return Optional.ofNullable(modelMapper.map(offerRepository.findById(id), AddOfferDto.class));
    }

    public ShowDetailedOfferInfoDto offerDetails(String id) {
        return modelMapper.map(offerRepository.findById(id).orElse(null), ShowDetailedOfferInfoDto.class);
    }

    public void removeOffer(String id) {
        offerRepository.deleteById(id);
    }


    public void editOffer(String originalOfferName, AddOfferDto offerDto) {
        Optional<Offer> existingOfferOptional = offerRepository.findById(originalOfferName);

        if (existingOfferOptional.isPresent()) {
            Offer existingOffer = existingOfferOptional.get();

            // Update existing fields
            existingOffer.setDescription(offerDto.getDescription());
            existingOffer.setEngineEnum(offerDto.getEngineEnum());
            existingOffer.setImageUrl(offerDto.getImageURL());
            existingOffer.setMileage(offerDto.getMileage());
            existingOffer.setPrice(offerDto.getPrice());
            existingOffer.setTransmissionEnum(offerDto.getTransmissionEnum());
            existingOffer.setYear(offerDto.getYear());
            existingOffer.setSeller(offerDto.getSeller());
            existingOffer.setModified(LocalDate.now());

            // Set Model
            Optional<Model> modelOptional = modelRepository.findByName(offerDto.getModel());
            if (modelOptional.isPresent()) {
                existingOffer.setModel(modelOptional.get());
            } else {
                throw new NoSuchElementException("Model not found: " + offerDto.getModel());
            }

            // Set User
            Optional<User> userOptional = userRepository.findByUserName(offerDto.getUsers());
            if (userOptional.isPresent()) {
                existingOffer.setUsers(userOptional.get());
            } else {
                throw new NoSuchElementException("User not found: " + offerDto.getUsers());
            }

            offerRepository.saveAndFlush(existingOffer);
        } else {
            throw new NoSuchElementException("Offer not found for update: " + originalOfferName);
        }
    }

}