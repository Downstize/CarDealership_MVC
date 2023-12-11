package ru.rutmiit.services;


import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.rutmiit.dto.AddBrandDto;
import ru.rutmiit.dto.ShowBrandInfoDto;
import ru.rutmiit.dto.ShowDetailedBrandInfoDto;
import ru.rutmiit.models.Brand;
import ru.rutmiit.repositories.BrandRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@EnableCaching
public class BrandService {

    private final  ModelMapper modelMapper;
    private final BrandRepository brandRepository;

    public BrandService(ModelMapper modelMapper, BrandRepository brandRepository) {
        this.modelMapper = modelMapper;
        this.brandRepository = brandRepository;
    }


    @CacheEvict(cacheNames = "brands", allEntries = true)
    public void addBrand(AddBrandDto brand) {
        Brand brandEntity = modelMapper.map(brand, Brand.class);
        brandEntity.setCreated(LocalDate.now());
        brandRepository.saveAndFlush(brandEntity);
    }

    @Cacheable("brands")
    public List<ShowBrandInfoDto> getAll() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return brandRepository.findAll().stream().map((brand) -> modelMapper.map(brand, ShowBrandInfoDto.class)).collect(Collectors.toList());
    }

    public AddBrandDto findBrandByName(String brandName) {
        return brandRepository.findByName(brandName)
                .map(brand -> modelMapper.map(brand, AddBrandDto.class))
                .orElse(null);
    }



    public ShowDetailedBrandInfoDto brandDetails(String brandName) {

        ShowDetailedBrandInfoDto brandDto = modelMapper.map(brandRepository.findByName(brandName).orElse(null), ShowDetailedBrandInfoDto.class);
        brandDto.setName(brandName);
        System.out.println(brandDto.getName());
        System.out.println("-----");
        return brandDto;
    }

    @CacheEvict(cacheNames = "brands", allEntries = true)
    public void removeBrand(String brandName) {
        brandRepository.deleteByName(brandName);
    }


//    public AddBrandDto editBrand(AddBrandDto brand) {
//        Brand b = modelMapper.map(brand, Brand.class);
////        b.setModified(new LocalDate());
//        return modelMapper.map(brandRepository.saveAndFlush(b), AddBrandDto.class);
//    }

    public void editBrand(String originalBrandName, AddBrandDto brandDto) {
        Optional<Brand> existingBrandOptional = brandRepository.findByName(originalBrandName);

        if (existingBrandOptional.isPresent()) {
            Brand existingBrand = existingBrandOptional.get();
            existingBrand.setName(brandDto.getName());
            existingBrand.setModified(LocalDate.now());

            brandRepository.saveAndFlush(existingBrand);
        } else {
            throw new NoSuchElementException("Brand not found for update: " + originalBrandName);
        }
    }


}