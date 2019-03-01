package com.epam.rates;

import com.epam.rates.exception.WrongDataException;
import com.epam.rates.model.*;
import com.epam.rates.parser.TariffsParser;
import com.epam.rates.parser.factory.ParserType;
import com.epam.rates.parser.factory.TariffsParserFactory;
import com.epam.rates.validation.XmlValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.mockito.Mockito.*;

public class XmlDirectorTest {
    private static final String CORRECT_XML_FILE = "src/test/resources/tariffs.xml";
    private static final TariffsParserFactory FACTORY = mock(TariffsParserFactory.class);
    private static final TariffsParser PARSER = mock(TariffsParser.class);
    private static final XmlValidator VALIDATOR = mock(XmlValidator.class);
    private static final ParserType TYPE = ParserType.DOM;
    private static final String WRONG_FILE = "WRONG";
    private static final Tariffs CORRECT_TARIFFS = new Tariffs(Arrays.asList(
            new Tariff("Easy to say", Operator.MTS, BigDecimal.valueOf(1004.6)),
            new Tariff("Kind", Operator.VELCOM, BigDecimal.valueOf(553.1)),
            new PreferentialTariff("Unlim", Operator.LIFE, BigDecimal.valueOf(100.5), Preferential.DISABLED),
            new PreferentialTariff("Cheaper unlim", Operator.VELCOM, BigDecimal.valueOf(64.42), Preferential.ORPHAN)
    ));
    private final XmlDirector director = new XmlDirector(FACTORY, VALIDATOR);

    @BeforeClass
    public static void initMocks() throws WrongDataException {
        when(VALIDATOR.validate(WRONG_FILE)).thenThrow(WrongDataException.class);
        when(VALIDATOR.validate(CORRECT_XML_FILE)).thenReturn(true);
        when(FACTORY.getParser(TYPE)).thenReturn(PARSER);
        when(PARSER.parse(CORRECT_XML_FILE)).thenReturn(CORRECT_TARIFFS);
    }

    @Test
    public void processShouldParseWhenXmlIsValid() throws WrongDataException {
        Tariffs result = director.process(CORRECT_XML_FILE, TYPE);

        Assert.assertEquals(CORRECT_TARIFFS, result);
        verify(VALIDATOR).validate(CORRECT_XML_FILE);
        verify(FACTORY).getParser(TYPE);
        verify(PARSER).parse(CORRECT_XML_FILE);
        verifyNoMoreInteractions(VALIDATOR, FACTORY, PARSER);
        clearInvocations(VALIDATOR, FACTORY, PARSER);
    }

    @Test
    public void processShouldReturnEmptyListWhenFileIsInvalid() throws WrongDataException {
        Tariffs expected = new Tariffs();
        Tariffs result = director.process(WRONG_FILE, TYPE);
        Assert.assertEquals(expected, result);
        verify(FACTORY).getParser(TYPE);
        verify(VALIDATOR).validate(WRONG_FILE);
        verifyNoMoreInteractions(FACTORY, VALIDATOR, PARSER);
        clearInvocations(FACTORY, VALIDATOR, PARSER);
    }
}