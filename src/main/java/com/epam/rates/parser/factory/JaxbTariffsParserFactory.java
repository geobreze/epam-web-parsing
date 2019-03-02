package com.epam.rates.parser.factory;

import com.epam.rates.parser.JaxbTariffsParser;
import com.epam.rates.parser.TariffsParser;

public class JaxbTariffsParserFactory implements TariffsParserFactory {
    @Override
    public TariffsParser get() {
        return new JaxbTariffsParser();
    }
}