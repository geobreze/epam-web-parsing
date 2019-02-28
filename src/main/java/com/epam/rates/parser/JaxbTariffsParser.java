package com.epam.rates.parser;

import com.epam.rates.exception.WrongDataException;
import com.epam.rates.model.Tariffs;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JaxbTariffsParser implements TariffsParser {
    public Tariffs parse(String source) throws WrongDataException {
        File sourceFile = new File(source);
        Object object;
        try {
            JAXBContext context = JAXBContext.newInstance(Tariffs.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            object = unmarshaller.unmarshal(sourceFile);
        } catch (JAXBException e) {
            throw new WrongDataException("Invalid XML file", e);
        }
        return (Tariffs) object;
    }
}
