package ru.rutmiit.services;


import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.rutmiit.dto.AddModelDto;
import ru.rutmiit.dto.ShowDetailedModelInfoDto;
import ru.rutmiit.dto.ShowModelInfoDto;
import ru.rutmiit.models.Model;
import ru.rutmiit.repositories.BrandRepository;
import ru.rutmiit.repositories.ModelRepository;

import java.util.List;
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
        model.setBrand(brandRepository.findByName(addModelDto.getBrand()).orElse(null));
        modelRepository.saveAndFlush(model);
    }

    public List<ShowModelInfoDto> getAllModels() {
        return modelRepository.findAll().stream().map(model -> modelMapper.map(model, ShowModelInfoDto.class))
                .collect(Collectors.toList());
    }

    public Optional<AddModelDto> findModel(String id) {
        return Optional.ofNullable(modelMapper.map(modelRepository.findById(id), AddModelDto.class));
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

}