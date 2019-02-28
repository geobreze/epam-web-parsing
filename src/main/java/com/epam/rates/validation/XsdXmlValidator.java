package com.epam.rates.validation;

import com.epam.rates.exception.WrongDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XsdXmlValidator implements XmlValidator {
    private static final Logger LOGGER = LogManager.getLogger(XsdXmlValidator.class);
    private final String schemaFilePath;

    public XsdXmlValidator(String schemaFilePath) {
        this.schemaFilePath = schemaFilePath;
    }

    public boolean validate(String source) throws WrongDataException {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        boolean result = true;
        File schemaFile = new File(schemaFilePath);
        File sourceFile = new File(source);
        StreamSource streamSource = new StreamSource(sourceFile);
        try {
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(streamSource);
        } catch (SAXException e) {
            LOGGER.info("Supplied source or schema file is invalid", e);
            result = false;
        } catch (IOException e) {
            throw new WrongDataException("Invalid files applied", e);
        }
        return result;
    }
}
