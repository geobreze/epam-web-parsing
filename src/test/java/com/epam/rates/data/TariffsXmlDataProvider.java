package com.epam.rates.data;

import com.epam.rates.model.*;
import org.testng.annotations.DataProvider;

import java.math.BigDecimal;
import java.util.Arrays;

public class TariffsXmlDataProvider {
    private static final Tariffs TARIFFS = new Tariffs(Arrays.asList(
            new Tariff("Easy to say", Operator.MTS, BigDecimal.valueOf(1004.6)),
            new Tariff("Kind", Operator.VELCOM, BigDecimal.valueOf(553.1)),
            new PreferentialTariff("Unlim", Operator.LIFE, BigDecimal.valueOf(100.5), Preferential.DISABLED),
            new PreferentialTariff("Cheaper unlim", Operator.VELCOM, BigDecimal.valueOf(64.42), Preferential.ORPHAN)
    ));
    private static final Tariffs BASIC_TARIFFS = new Tariffs(Arrays.asList(
            new Tariff("1024", Operator.LIFE, BigDecimal.valueOf(1024.0)),
            new Tariff("Lemon", Operator.VELCOM, BigDecimal.valueOf(335.3))
    ));
    private static final Tariffs PREFERENTIAL_TARIFFS = new Tariffs(Arrays.asList(
            new PreferentialTariff("Jukebox", Operator.VELCOM, BigDecimal.ZERO, Preferential.DISABLED),
            new PreferentialTariff("Orphan happiness", Operator.MTS, BigDecimal.TEN, Preferential.ORPHAN)
    ));
    private static final String PATH = "src/test/resources/";

    @DataProvider
    public static Object[][] parsedDataProvider() {
        return new Object[][]{
                new Object[]{PATH + "tariffs.xml", TARIFFS},
                new Object[]{PATH + "basicTariffs.xml", BASIC_TARIFFS},
                new Object[]{PATH + "preferentialTariffs.xml", PREFERENTIAL_TARIFFS}
        };
    }

    @DataProvider
    public static Object[][] wrongDataProvider() {
        return new Object[][]{
                new Object[]{PATH + "wrongEnum.xml"},
                new Object[]{PATH + "wrongPayload.xml"},
                new Object[]{PATH + "wrongTags.xml"}
        };
    }
}
