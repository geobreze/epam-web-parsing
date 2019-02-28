package com.epam.rates.validation;

import com.epam.rates.exception.WrongDataException;

public interface XmlValidator {
    boolean validate(String source) throws WrongDataException;
}
