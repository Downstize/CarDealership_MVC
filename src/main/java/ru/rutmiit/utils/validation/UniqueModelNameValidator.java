package ru.rutmiit.utils.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.rutmiit.repositories.ModelRepository;

public class UniqueModelNameValidator implements ConstraintValidator<UniqueModelName, String> {
    private final ModelRepository modelRepository;

    public UniqueModelNameValidator(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return modelRepository.findByName(value).isEmpty();
    }
}
