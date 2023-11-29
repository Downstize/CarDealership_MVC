package ru.rutmiit.utils.validation;

import ru.rutmiit.repositories.CompanyRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueCompanyNameValidator implements ConstraintValidator<UniqueCompanyName, String> {
    private final CompanyRepository companyRepository;

    public UniqueCompanyNameValidator(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return companyRepository.findByName(value).isEmpty();
    }
}
