package ru.rutmiit.services.serv;


import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.rutmiit.dto.ShowDetailedCompanyInfoDto;
import ru.rutmiit.dto.dtooo.AddBrandDto;
import ru.rutmiit.dto.dtooo.ShowDetailedBrandInfoDto;
import ru.rutmiit.models.Brand;
import ru.rutmiit.repositories.repo.BrandRepository;

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

    public void  addBrand(AddBrandDto brand) {
        Brand b = modelMapper.map(brand, Brand.class);
        if (b.getId() == null || findBrand(b.getId()).isEmpty()) {
            modelMapper.map(brandRepository.saveAndFlush(b), AddBrandDto.class);
        }

    }

    public List<AddBrandDto> getAll() {
        return brandRepository.findAll().stream().map((s) -> modelMapper.map(s, AddBrandDto.class)).collect(Collectors.toList());
    }


    public Optional<AddBrandDto> findBrand(String id) {
        return Optional.ofNullable(modelMapper.map(brandRepository.findById(id), AddBrandDto.class));
    }

    public ShowDetailedBrandInfoDto brandDetails(String brandName) {
        return modelMapper.map(brandRepository.findByName(brandName).orElse(null), ShowDetailedBrandInfoDto.class);
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