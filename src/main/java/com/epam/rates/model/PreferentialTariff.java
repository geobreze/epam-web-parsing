package com.epam.rates.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

@XmlType(name = "preferential-tariff", namespace = "http://epam.com/rates")
@XmlRootElement(name = "preferential-tariff", namespace = "http://epam.com/rates")
public class PreferentialTariff extends Tariff {
    private Preferential preferential;

    public PreferentialTariff() {
    }

    public PreferentialTariff(String name, Operator operatorName, BigDecimal payroll, Preferential preferential) {
        super(name, operatorName, payroll);
        this.preferential = preferential;
    }

    @XmlElement(namespace = "http://epam.com/rates")
    public Preferential getPreferential() {
        return preferential;
    }

    public void setPreferential(Preferential preferential) {
        this.preferential = preferential;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        PreferentialTariff that = (PreferentialTariff) o;
        return preferential.equals(preferential);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), preferential);
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(";");
        stringJoiner.add(super.toString()).add(preferential.toString());
        return stringJoiner.toString();
    }
}
