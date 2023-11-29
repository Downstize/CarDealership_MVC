package ru.rutmiit.services.serv;


import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.rutmiit.dto.dtooo.AddModelDto;
import ru.rutmiit.dto.dtooo.ShowDetailedBrandInfoDto;
import ru.rutmiit.dto.dtooo.ShowDetailedModelInfoDto;
import ru.rutmiit.models.Model;
import ru.rutmiit.models.Offer;
import ru.rutmiit.repositories.repo.ModelRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModelService {

    private final ModelMapper modelMapper;
    private final ModelRepository modelRepository;

    public ModelService(ModelMapper modelMapper, ModelRepository modelRepository) {
        this.modelMapper = modelMapper;
        this.modelRepository = modelRepository;
    }


    public void register(AddModelDto model) {
                Model b = modelMapper.map(model, Model.class);
                if (b.getId() == null || findModel(b.getId()).isEmpty()) {
                    modelMapper.map(modelRepository.saveAndFlush(b), AddModelDto.class);
                }

    }


    public List<AddModelDto> getAll() {
        return modelRepository.findAll().stream().map((s) -> modelMapper.map(s, AddModelDto.class)).collect(Collectors.toList());
    }


    public Optional<AddModelDto> findModel(String id) {
        return Optional.ofNullable(modelMapper.map(modelRepository.findById(id), AddModelDto.class));
    }

    public ShowDetailedModelInfoDto modelDetails(String modelName) {
        return modelMapper.map(modelRepository.findByName(modelName).orElse(null), ShowDetailedModelInfoDto.class);
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