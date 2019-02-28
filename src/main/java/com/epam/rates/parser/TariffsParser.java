package com.epam.rates.parser;

import com.epam.rates.exception.WrongDataException;
import com.epam.rates.model.Tariffs;

public interface TariffsParser {
    Tariffs parse(String source) throws WrongDataException;
}
