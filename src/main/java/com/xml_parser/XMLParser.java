package com.xml_parser;

import com.json_news_item.JSONContainer;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class XMLParser {

    static JSONObject bufferObject;
    static String allImgLinks = "";
    final static String IMG_FILE_HOME_PATH = System.getenv("CATALINA_HOME") + "\\webapps\\images\\imgFile.txt";
    static File imgLinksFile = new File(IMG_FILE_HOME_PATH);


    public static void parser() {
        try {

            DocumentBuilder xml = DocumentBuilderFactory.
                    newInstance().newDocumentBuilder();


            Document doc = xml.parse(new File("I:\\rss.builder.rus.xml"));

            Element rootElement = doc.getDocumentElement();

            System.out.println(rootElement.getNodeName());

            System.out.println("Child elements: ");
            NodeList lst = rootElement.getChildNodes();
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

            System.out.println("Full Links = " + allImgLinks);
            write(allImgLinks);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void displayItemChild(NodeList list) {

        JSONObject jsonObject = new JSONObject();
        bufferObject = jsonObject;

        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).getNodeName().equals("description")) {
                checkDescription(list.item(i));
            } else {
                jsonObject.put("" + list.item(i).getNodeName() + "", list.item(i).getTextContent());
                System.out.println(" " + list.item(i).getNodeName() + ":" + list.item(i).getTextContent());
            }
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

    public static void checkDescription(Node descriptionNode) {

        NodeList descriptionNodeChildNodes = descriptionNode.getChildNodes();
        char[] subStringImg;
        String ImgLink = "";

        bufferObject.put("" + descriptionNode.getNodeName() + "", descriptionNode.getTextContent());
        System.out.println("" + descriptionNode.getNodeName() + ":" + descriptionNode.getTextContent());
        System.out.println("checkDescription invoked successfully");

        for (int i = 0; i < descriptionNodeChildNodes.getLength(); i++) {
            String imgString = descriptionNodeChildNodes.item(i).getNodeValue();
            String[] imgStringArray = imgString.split(" ");

            for (int j = 0; j < imgStringArray.length; j++) {
                if (imgStringArray[j].contains("src=")) {
                    subStringImg = imgStringArray[j].toCharArray();
                    for (int k = 5; k < subStringImg.length - 1; k++) {
                        ImgLink = ImgLink + subStringImg[k];
                    }
                    System.out.println("IMG Link = " + ImgLink);
                    allImgLinks = allImgLinks + ImgLink + "\n";
                }
            }

        }

    }

    public static void write(String text) {

        try {
            //проверяем, что если файл не существует то создаем его
            if (!imgLinksFile.exists()) {
                imgLinksFile.createNewFile();
            }

            //PrintWriter обеспечит возможности записи в файл
            PrintWriter out = new PrintWriter(imgLinksFile.getAbsoluteFile());

            try {
                //Записываем текст у файл
                out.print(text + "\n");
            } finally {
                //После чего мы должны закрыть файл
                //Иначе файл не запишется
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}


