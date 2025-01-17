/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sparrow.utility;

import com.sparrow.protocol.constant.CONSTANT;
import com.sparrow.protocol.constant.magic.ESCAPED;
import com.sparrow.protocol.constant.magic.SYMBOL;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author harry
 */
public class Xml {

    private static Logger logger = LoggerFactory.getLogger(Xml.class);

    public static Document getXmlDocumentByString(String xml) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error("get xml dodument parse config error", e);
            return null;
        }
        Document document = null;
        try {
            if (xml != null) {
                document = builder.parse(new ByteArrayInputStream(xml
                        .getBytes(CONSTANT.CHARSET_UTF_8)));
            }
        } catch (Exception e) {
            logger.error("get xml document error", e);
        }
        return document;
    }

    /**
     * 获取element文本
     *
     * @param xmlKey xml路径不包含根节点
     */
    public static String getElementTextContent(Document doc, String xmlKey) {
        try {
            Element element = doc.getDocumentElement();
            String[] elementName = xmlKey.split("\\.");
            for (String e : elementName) {
                element = (Element) (element
                        .getElementsByTagName(e)).item(0);
            }
            return element.getTextContent().trim();
        } catch (Exception ex) {
            return null;
        }
    }

    public static Element getElementByTagAttribute(Document doc,
            String tagName, String attributeName, String attributeValue) {
        Element element = doc.getDocumentElement();
        NodeList nodeList = element.getElementsByTagName(tagName);
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == 1) {
                element = (Element) nodeList.item(i);
                if (element.getAttribute(attributeName).equals(attributeValue)) {
                    return element;
                }
            }
        }
        return null;
    }

    public static List<Element> getElementsByTagName(Document doc,
            String tagName) {
        Element element = doc.getDocumentElement();
        NodeList nodeList = element.getElementsByTagName(tagName);
        List<Element> elementList = new ArrayList<Element>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == 1) {
                element = (Element) nodeList.item(i);
                elementList.add(element);
            }
        }
        return elementList;
    }

    public static Map<String, String> getInfo(String xml) {
        Map<String, String> info = new TreeMap<String, String>();
        Document document = getXmlDocumentByString(xml);
        Element root = document.getDocumentElement();
        NodeList nl = root.getChildNodes();
        int length = nl.getLength();
        Node node = null;
        for (int i = 0; i < length; i++) {
            node = nl.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                info.put(node.getNodeName(), node.getTextContent());
            }
        }
        return info;
    }

    public static String getXml(Map<String, String> xml) {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<xml>");
        for (String key : xml.keySet()) {
            xmlBuilder.append(String.format("<%1$s><![CDATA[%2$s]]></%1$s>", key, xml.get(key)));
        }
        xmlBuilder.append("</xml>");
        return xmlBuilder.toString();
    }

    /**
     * xml编码
     */
    public static String xmlEncode(String xml) {
        if (xml.contains(SYMBOL.AND)) {
            xml = xml.replace(SYMBOL.AND, ESCAPED.AND);
        }
        if (xml.contains(SYMBOL.LESS_THEN)) {
            xml = xml.replace(SYMBOL.LESS_THEN, ESCAPED.LESS_THEN);
        }
        if (xml.contains(SYMBOL.GREATER_THAN)) {
            xml = xml.replace(SYMBOL.GREATER_THAN, ESCAPED.GREAT_THEN);
        }

        if (xml.contains(SYMBOL.SINGLE_QUOTES)) {
            xml = xml.replace(SYMBOL.SINGLE_QUOTES, ESCAPED.SINGLE_QUOTES);
        }

        if (xml.contains(SYMBOL.DOUBLE_QUOTES)) {
            xml = xml.replace(SYMBOL.DOUBLE_QUOTES, ESCAPED.DOUBLE_QUOTES);
        }
        return xml;
    }

    /**
     * xml解码
     */
    public static String xmlDecode(String xml) {
        if (xml.contains(ESCAPED.LESS_THEN)) {
            xml = xml.replace(ESCAPED.LESS_THEN, SYMBOL.LESS_THEN);
        }
        if (xml.contains(ESCAPED.GREAT_THEN)) {
            xml = xml.replace(ESCAPED.GREAT_THEN, SYMBOL.GREATER_THAN);
        }
        if (xml.contains(ESCAPED.AND)) {
            xml = xml.replace(ESCAPED.AND, SYMBOL.AND);
        }
        if (xml.contains(ESCAPED.SINGLE_QUOTES)) {
            xml = xml.replace(ESCAPED.SINGLE_QUOTES, SYMBOL.SINGLE_QUOTES);
        }
        if (xml.contains(ESCAPED.DOUBLE_QUOTES)) {
            xml = xml.replace(ESCAPED.DOUBLE_QUOTES, SYMBOL.DOUBLE_QUOTES);
        }
        return xml;
    }

    public String getNamespace(Node node) {
        return node.getNamespaceURI();
    }
}
