package com.epam.rates.validation;

import com.epam.rates.data.TariffsXmlDataProvider;
import com.epam.rates.exception.WrongDataException;
import com.epam.rates.model.Tariffs;
import org.junit.Assert;
import org.testng.annotations.Test;

public class XsdXmlValidatorTest {
    private static final String SCHEMA_PATH = "src/main/resources/schema.xsd";
    private static final String WRONG_PATH = "wrong.xml";

    private final XmlValidator validator = new XsdXmlValidator(SCHEMA_PATH);

    @Test(dataProvider = "parsedDataProvider", dataProviderClass = TariffsXmlDataProvider.class)
    public void validateShouldReturnTrueWhenCorrectXmlSupplied(String source, Tariffs expected) throws WrongDataException {
        boolean result = validator.validate(source);
        Assert.assertTrue(result);
    }

    @Test(dataProvider = "wrongDataProvider", dataProviderClass = TariffsXmlDataProvider.class)
    public void validateShouldReturnFalseWhenWrongXmlSupplied(String source) throws WrongDataException {
        boolean result = validator.validate(source);
        Assert.assertFalse(result);
    }

    @Test(expectedExceptions = WrongDataException.class)
    public void validateShouldThrowWrongDataExceptionWhenFileIsNotFound() throws WrongDataException {
        validator.validate(WRONG_PATH);
    }
}