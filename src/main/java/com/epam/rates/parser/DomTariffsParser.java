package com.epam.rates.parser;

import com.epam.rates.exception.WrongDataException;
import com.epam.rates.model.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DomTariffsParser implements TariffsParser {
    private static final String NAMESPACE_URI = "http://epam.com/rates";
    private static final String TARIFF = "tariff";
    private static final String PREFERENTIAL_TARIFF = "preferential-tariff";

    public Tariffs parse(String source) throws WrongDataException {
        List<Tariff> tariffsList;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(source);
            Element root = document.getDocumentElement();
            NodeList nodes = root.getChildNodes();
            tariffsList = parseTariffs(nodes);
        } catch (ParserConfigurationException e) {
            throw new WrongDataException("Wrong parser configuration", e);
        } catch (SAXException e) {
            throw new WrongDataException("Parse exception", e);
        } catch (IOException e) {
            throw new WrongDataException("Input/output exception", e);
        }
        return new Tariffs(tariffsList);
    }

    private List<Tariff> parseTariffs(NodeList nodes) throws WrongDataException {
        List<Tariff> tariffsList = new ArrayList<>();
        for (int i = 0; i < nodes.getLength(); ++i) {
            Node tariffNode = nodes.item(i);
            if (tariffNode.getNodeType() == Node.ELEMENT_NODE) {
                Tariff tariff = buildTariff(tariffNode);
                tariffsList.add(tariff);
            }
        }
        return tariffsList;
    }

    private Tariff buildTariff(Node tariffNode) throws WrongDataException {
        Tariff tariff;
        switch (tariffNode.getLocalName()) {
            case TARIFF:
                tariff = buildTariff(tariffNode, new Tariff());
                break;
            case PREFERENTIAL_TARIFF:
                tariff = buildPreferentialTariff(tariffNode, new PreferentialTariff());
                break;
            default:
                throw new WrongDataException("Invalid tag");
        }
        return tariff;
    }

    private Tariff buildPreferentialTariff(Node node, Tariff tariff) {
        buildTariff(node, tariff);

        Element element = (Element) node;
        String preferentialString = getChildNodeValue("preferential", element);
        Preferential preferential = Preferential.valueOf(preferentialString);
        ((PreferentialTariff) tariff).setPreferential(preferential);
        return tariff;
    }

    private Tariff buildTariff(Node node, Tariff tariff){
        Element element = (Element) node;
        String name = element.getAttribute("name");
        tariff.setName(name);
        String operatorNameString = getChildNodeValue("operator-name", element);
        Operator operatorName = Operator.valueOf(operatorNameString);
        tariff.setOperatorName(operatorName);
        String payrollString = getChildNodeValue("payroll", element);
        BigDecimal payroll = new BigDecimal(payrollString);
        tariff.setPayroll(payroll);
        return tariff;
    }

    private Element getChildNode(String tagName, Element parent) {
        NodeList nodes = parent.getElementsByTagNameNS(NAMESPACE_URI, tagName);
        return (Element) nodes.item(0);
    }

    private String getChildNodeValue(String tagName, Element parent) {
        Element child = getChildNode(tagName, parent);
        Node childNode = child.getFirstChild();
        return childNode.getNodeValue();
    }
}