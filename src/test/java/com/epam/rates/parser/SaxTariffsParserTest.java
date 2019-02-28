package com.epam.rates.parser;

import com.epam.rates.data.TariffsXmlDataProvider;
import com.epam.rates.exception.WrongDataException;
import com.epam.rates.model.Tariffs;
import org.junit.Assert;
import org.testng.annotations.Test;

public class SaxTariffsParserTest {
    private static final String WRONG_PATH = "wrong.xml";
    private final TariffsParser parser = new SaxTariffsParser();

    @Test(dataProvider = "parsedDataProvider", dataProviderClass = TariffsXmlDataProvider.class)
    public void parseShouldReturnOrderedList(String source, Tariffs expected) throws WrongDataException {
        Tariffs result = parser.parse(source);
        Assert.assertEquals(expected, result);
    }

    @Test(expectedExceptions = WrongDataException.class)
    public void parseShouldThrowWrongDataExceptionWhenWrongXmlPathSupplied() throws WrongDataException {
        parser.parse(WRONG_PATH);
    }
}