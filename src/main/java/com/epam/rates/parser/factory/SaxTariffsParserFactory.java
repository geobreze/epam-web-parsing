package com.epam.rates.parser.factory;

import com.epam.rates.parser.SaxTariffsParser;
import com.epam.rates.parser.TariffsParser;

public class SaxTariffsParserFactory implements TariffsParserFactory {
    @Override
    public TariffsParser get() {
        return new SaxTariffsParser();
    }
}
