package com.pedrohrr.simpletransfer.data;

import com.blade.ioc.annotation.Bean;
import com.pedrohrr.simpletransfer.exception.InvalidDataException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Bean
public class Validator {

    private static final String SPACE = " ";
    private static final String SEPARATOR = "; ";

    private final ValidatorFactory factory;

    public Validator() {
        factory = Validation.buildDefaultValidatorFactory();
    }

    public void validate(final ValidatedData data) throws InvalidDataException {
        final Set<ConstraintViolation<ValidatedData>> violations = factory.getValidator().validate(data);
        if (!violations.isEmpty()) {
            throw new InvalidDataException(violations.stream().map(getCustomMessage()).collect(Collectors.joining(SEPARATOR)));
        }
    }

    private Function<ConstraintViolation<ValidatedData>, String> getCustomMessage() {
        return v -> v.getPropertyPath().toString() + SPACE + v.getMessage();
    }

}
