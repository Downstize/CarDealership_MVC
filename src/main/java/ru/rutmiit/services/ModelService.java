package ru.rutmiit.services;


import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.rutmiit.dto.AddBrandDto;
import ru.rutmiit.dto.AddModelDto;
import ru.rutmiit.dto.ShowDetailedModelInfoDto;
import ru.rutmiit.dto.ShowModelInfoDto;
import ru.rutmiit.models.Brand;
import ru.rutmiit.models.Model;
import ru.rutmiit.repositories.BrandRepository;
import ru.rutmiit.repositories.ModelRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModelService {

    private final ModelMapper modelMapper;
    private final ModelRepository modelRepository;

    private final BrandRepository brandRepository;

    public ModelService(ModelMapper modelMapper, ModelRepository modelRepository, BrandRepository brandRepository) {
        this.modelMapper = modelMapper;
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
    }


    public void addModel(AddModelDto addModelDto) {
        Model model = modelMapper.map(addModelDto, Model.class);
        model.setCreated(LocalDate.now());
        model.setBrand(brandRepository.findByName(addModelDto.getBrand()).orElse(null));
        modelRepository.saveAndFlush(model);
    }

    public List<ShowModelInfoDto> getAllModels() {
        return modelRepository.findAll().stream().map(model -> modelMapper.map(model, ShowModelInfoDto.class))
                .collect(Collectors.toList());
    }

    public AddModelDto findModelByName(String modelName) {
        return modelRepository.findByName(modelName)
                .map(model -> modelMapper.map(model, AddModelDto.class))
                .orElse(null);
    }

    public ShowDetailedModelInfoDto modelDetails(String modelName) {
        return modelMapper.map(modelRepository.findByName(modelName), ShowDetailedModelInfoDto.class);
    }

    public void removeModel(String modelName) {
        modelRepository.deleteByName(modelName);
    }


    public AddModelDto update(AddModelDto model) {
        Optional<Model> existingModel = modelRepository.findByName(model.getName());
        if (existingModel.isPresent()) {
            return modelMapper.map(modelRepository.save(modelMapper.map(model, Model.class)), AddModelDto.class);
        } else {
            throw new DataIntegrityViolationException("Exception of update!");
        }
    }


    public List<AddModelDto> findModelsFromStartYear(int startYear) {
        return modelRepository.findModelsFromStartYear(startYear).stream()
                .map(model -> modelMapper.map(model, AddModelDto.class))
                .collect(Collectors.toList());
    }

    public void editModel(String originalModelName, AddModelDto modelDto) {
        Optional<Model> existingModelOptional = modelRepository.findByName(originalModelName);

        if (existingModelOptional.isPresent()) {
            Model existingModel = existingModelOptional.get();

            // Assuming you have a brandRepository to fetch the Brand entity
            Optional<Brand> brandOptional = brandRepository.findByName(modelDto.getBrand());
            if (brandOptional.isPresent()) {
                existingModel.setBrand(brandOptional.get());
            } else {
                throw new NoSuchElementException("Brand not found: " + modelDto.getBrand());
            }

            existingModel.setName(modelDto.getName());
            existingModel.setCategoryEnum(modelDto.getCategoryEnum());
            existingModel.setImageUrl(modelDto.getImageURL());
            existingModel.setStartYear(modelDto.getStartYear());
            existingModel.setEndYear(modelDto.getEndYear());
            existingModel.setModified(LocalDate.now());

            modelRepository.saveAndFlush(existingModel);
        } else {
            throw new NoSuchElementException("Model not found for update: " + originalModelName);
        }
    }


}