package com.epam.rates.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

@XmlType(namespace = "http://epam.com/rates")
@XmlRootElement(name = "tariff", namespace = "http://epam.com/rates")
public class Tariff {
    private String name;
    private Operator operatorName;
    private BigDecimal payroll;

    public Tariff() {
    }

    public Tariff(String name, Operator operatorName, BigDecimal payroll) {
        this.name = name;
        this.operatorName = operatorName;
        this.payroll = payroll;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOperatorName(Operator operatorName) {
        this.operatorName = operatorName;
    }

    public void setPayroll(BigDecimal payroll) {
        this.payroll = payroll;
    }

    @XmlAttribute(name = "name", required = true)
    public String getName() {
        return name;
    }

    @XmlElement(name = "operator-name", namespace = "http://epam.com/rates", required = true)
    public Operator getOperatorName() {
        return operatorName;
    }

    @XmlElement(name = "payroll", namespace = "http://epam.com/rates", required = true)
    public BigDecimal getPayroll() {
        return payroll;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tariff tariff = (Tariff) o;
        return payroll.compareTo(tariff.payroll) == 0 &&
                name.equals(tariff.name) &&
                operatorName == tariff.operatorName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, operatorName, payroll);
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(";");
        stringJoiner.add(name).add(operatorName.toString()).add(payroll.toString());
        return stringJoiner.toString();
    }
}
