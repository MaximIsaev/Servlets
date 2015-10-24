package com.xml_parser;

import com.json_news_item.JSONContainer;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLParser {


    public static void parser() {
        try {

            DocumentBuilder xml = DocumentBuilderFactory.
                    newInstance().newDocumentBuilder();


            Document doc = xml.parse(new File("D:/Tomcat/rss.builder.feedrss.builder.xml"));

            Element rootel = doc.getDocumentElement();

            System.out.println(rootel.getNodeName());

            System.out.println("Child elements: ");
            NodeList lst = rootel.getChildNodes();
            NodeList itemList;


            for (int i = 0; i < lst.getLength(); i++) {
                System.out.println(lst.item(i).getNodeName());
                NodeList channelNodes = lst.item(i).getChildNodes();
                System.out.println("Channels child elements: " + channelNodes.getLength());
                int itemCount = xmlItemCounter(channelNodes);
                for (int j = channelNodes.getLength() - itemCount; j < channelNodes.getLength(); j++) {
                    System.out.println(channelNodes.item(j).getNodeName() + (j - 1));
                    itemList = channelNodes.item(j).getChildNodes();
                    displayItemChild(itemList);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void displayItemChild(NodeList list) {

        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < list.getLength(); i++) {
            jsonObject.put(list.item(i).getNodeName(), list.item(i).getTextContent());
            System.out.println(" " + list.item(i).getNodeName() + ":" + list.item(i).getTextContent());
        }
        JSONContainer.addNewJsonRecord(jsonObject);
    }

    public static int xmlItemCounter(NodeList list) {

        int counterItem = 0;
        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).getNodeName().equals("item")) {
                counterItem++;
            }
        }
        return counterItem;
    }
}


