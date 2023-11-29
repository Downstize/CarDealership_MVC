package ru.rutmiit.utils.validation;

import ru.rutmiit.repositories.CompanyRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.rutmiit.repositories.repo.BrandRepository;

public class UniqueBrandNameValidator implements ConstraintValidator<UniqueBrandName, String> {
    private final BrandRepository brandRepository;

    public UniqueBrandNameValidator(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return brandRepository.findByName(value).isEmpty();
    }
}
