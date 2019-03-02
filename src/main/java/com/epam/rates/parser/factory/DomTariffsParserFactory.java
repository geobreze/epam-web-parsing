package com.epam.rates.parser.factory;

import com.epam.rates.parser.DomTariffsParser;
import com.epam.rates.parser.TariffsParser;

public class DomTariffsParserFactory implements TariffsParserFactory {
    @Override
    public TariffsParser get() {
        return new DomTariffsParser();
    }
}
