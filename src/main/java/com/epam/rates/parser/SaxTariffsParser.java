package com.epam.rates.parser;

import com.epam.rates.exception.WrongDataException;
import com.epam.rates.model.Tariffs;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class SaxTariffsParser implements TariffsParser {
    @Override
    public Tariffs parse(String source) throws WrongDataException {
        TariffsHandler tariffsHandler = new TariffsHandler();
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(tariffsHandler);
            reader.parse(source);
        } catch (SAXException e) {
            throw new WrongDataException("Invalid XML reader", e);
        } catch (IOException e) {
            throw new WrongDataException("Invalid input file", e);
        }
        return tariffsHandler.getTariffs();
    }
}
