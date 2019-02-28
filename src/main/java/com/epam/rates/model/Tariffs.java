package com.epam.rates.model;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "tariffs", namespace = "http://epam.com/rates")
public class Tariffs {
    private List<Tariff> tariffs;

    public Tariffs() {
        tariffs = new ArrayList<>();
    }

    public Tariffs(List<Tariff> tariffs) {
        this.tariffs = tariffs;
    }

    @XmlElementRefs(value = {@XmlElementRef(name = "tariff", type = Tariff.class, namespace = "http://epam.com/rates"),
            @XmlElementRef(name = "preferential-tariff", type = PreferentialTariff.class, namespace = "http://epam.com/rates")})
    public List<Tariff> getTariffs() {
        return tariffs;
    }

    public void setTariffs(List<Tariff> tariffs) {
        this.tariffs = tariffs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tariffs other = (Tariffs) o;
        return Objects.equals(tariffs, other.tariffs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tariffs);
    }

    @Override
    public String toString() {
        return tariffs.toString();
    }
}
