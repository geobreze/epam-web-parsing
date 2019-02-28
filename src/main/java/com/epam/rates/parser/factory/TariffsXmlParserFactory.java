package com.epam.rates.parser.factory;

import com.epam.rates.parser.DomTariffsParser;
import com.epam.rates.parser.JaxbTariffsParser;
import com.epam.rates.parser.SaxTariffsParser;
import com.epam.rates.parser.TariffsParser;

public class TariffsXmlParserFactory implements TariffsParserFactory {
    public TariffsParser getParser(ParserType type) {
        switch (type) {
            case DOM:
                return new DomTariffsParser();
            case SAX:
                return new SaxTariffsParser();
            case JAXB:
                return new JaxbTariffsParser();
            default:
                return null;
        }
    }
}
