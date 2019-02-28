package com.epam.rates.parser;

import com.epam.rates.model.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TariffsHandler extends DefaultHandler {
    private static final String OPERATOR_NAME = "operator-name";
    private static final String PAYROLL = "payroll";
    private static final String PREFERENTIAL = "preferential";
    private static final String TARIFF = "tariff";
    private static final String PREFERENTIAL_TARIFF = "preferential-tariff";
    private final List<Tariff> tariffList = new ArrayList<>();
    private Tariffs tariffs;
    private Tariff currentTariff;
    private String currentElement;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentElement = localName;
        switch (localName) {
            case TARIFF:
                currentTariff = new Tariff();
                currentTariff.setName(attributes.getValue("name"));
                break;
            case PREFERENTIAL_TARIFF:
                currentTariff = new PreferentialTariff();
                currentTariff.setName(attributes.getValue("name"));
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String chars = new String(ch, start, length).trim();
        if (chars.length() == 0) {
            return;
        }
        switch (currentElement) {
            case OPERATOR_NAME:
                Operator operatorName = Operator.valueOf(chars);
                currentTariff.setOperatorName(operatorName);
                break;
            case PAYROLL:
                BigDecimal payroll = new BigDecimal(chars);
                currentTariff.setPayroll(payroll);
                break;
            case PREFERENTIAL:
                Preferential preferential = Preferential.valueOf(chars);
                ((PreferentialTariff) currentTariff).setPreferential(preferential);
                break;
        }
    }

    public Tariffs getTariffs() {
        return tariffs;
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (localName) {
            case TARIFF:
            case PREFERENTIAL_TARIFF:
                tariffList.add(currentTariff);
                break;
        }
    }

    @Override
    public void endDocument() {
        tariffs = new Tariffs(tariffList);
    }
}
