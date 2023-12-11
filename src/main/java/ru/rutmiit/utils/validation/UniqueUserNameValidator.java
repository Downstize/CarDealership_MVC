package ru.rutmiit.utils.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.rutmiit.repositories.BrandRepository;
import ru.rutmiit.repositories.UserRepository;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {
    private final UserRepository userRepository;

    public UniqueUserNameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return userRepository.findByUserName(value).isEmpty();
    }
}
