package com.epam.task11.parser;

import com.epam.task11.exeptions.AppException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SecurityDOM {
    public List<SecurityRestriction> parse(String path){
        try {
            List<SecurityRestriction> list = new ArrayList<>();
            File inputFile = new File(path);
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("constraint");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    SecurityRestriction restriction = new SecurityRestriction();
                    restriction.setUrlPattern(eElement
                            .getElementsByTagName("url-pattern")
                            .item(0)
                            .getTextContent());
                    NodeList roles = eElement.getElementsByTagName("role");
                    List<String> rolesList = new ArrayList<>();
                    for (int i = 0; i < roles.getLength(); i++) {
                        rolesList.add(roles.item(i).getTextContent());
                    }
                    restriction.setRole(rolesList);
                    list.add(restriction);
                }
            }
            return list;
        } catch (Exception e) {
            throw new AppException(e);
        }
    }
}
