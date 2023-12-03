package ru.rutmiit.services;


import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.rutmiit.dto.AddBrandDto;
import ru.rutmiit.dto.ShowBrandInfoDto;
import ru.rutmiit.dto.ShowDetailedBrandInfoDto;
import ru.rutmiit.models.Brand;
import ru.rutmiit.repositories.BrandRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandService {

    private final  ModelMapper modelMapper;
    private final BrandRepository brandRepository;

    public BrandService(ModelMapper modelMapper, BrandRepository brandRepository) {
        this.modelMapper = modelMapper;
        this.brandRepository = brandRepository;
    }

    public void addBrand(AddBrandDto brand) {
        brandRepository.saveAndFlush(modelMapper.map(brand, Brand.class));
    }

    public List<ShowBrandInfoDto> getAll() {
        return brandRepository.findAll().stream().map((brand) -> modelMapper.map(brand, ShowBrandInfoDto.class)).collect(Collectors.toList());
    }

    public AddBrandDto findBrandByName(String brandName) {
        return brandRepository.findByName(brandName)
                .map(brand -> modelMapper.map(brand, AddBrandDto.class))
                .orElse(null);
    }



    public ShowDetailedBrandInfoDto brandDetails(String brandName) {
        return modelMapper.map(brandRepository.findByName(brandName), ShowDetailedBrandInfoDto.class);
    }


    public void removeBrand(String brandName) {
        brandRepository.deleteByName(brandName);
    }


    public AddBrandDto update(AddBrandDto brand) {
        Optional<Brand> existingBrand = brandRepository.findByName(brand.getName());
        if (existingBrand.isPresent()) {
            return modelMapper.map(brandRepository.save(modelMapper.map(brand, Brand.class)), AddBrandDto.class);
        } else {
            throw new DataIntegrityViolationException("Exception of update!");
        }
    }
}