package com.xml_parser;

import com.get_news_feed_file.DownloadNewsFeedFile;
import com.json_news_item.JSONContainer;

import org.json.simple.JSONObject;
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

    final String IMG_FOLDER_HOME_PATH = System.getenv("CATALINA_HOME") + "\\webapps\\NewsData\\images";
    final String IMG_FILE_HOME_PATH = System.getenv("CATALINA_HOME") + "\\webapps\\NewsData\\images\\imgLinks.txt";
    JSONObject bufferObject;
    String allImgLinks = "";
    File imgLinksFile = new File(IMG_FILE_HOME_PATH);
    File folder = new File(IMG_FOLDER_HOME_PATH);
    JSONContainer jsonContainer = new JSONContainer();

    public JSONContainer getJsonContainer() {
        return jsonContainer;
    }


    public void parseToJson() {

        DownloadNewsFeedFile downloadNewsFeedFile = new DownloadNewsFeedFile();
        try {

            DocumentBuilder xml = DocumentBuilderFactory.
                    newInstance().newDocumentBuilder();


            Document doc = xml.parse(downloadNewsFeedFile.getOutputFeedFile());


            Element rootElement = doc.getDocumentElement();
            NodeList lst = rootElement.getChildNodes();


            for (int i = 0; i < lst.getLength(); i++) {
                NodeList channelNodes = lst.item(i).getChildNodes();
                int itemCount = xmlItemCounter(channelNodes);
                getItemsChildNodes(channelNodes, itemCount);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getItemsChildNodes(NodeList channelNodes, int itemCount) throws IOException {

        NodeList itemList;
        for (int j = channelNodes.getLength() - itemCount; j < channelNodes.getLength(); j++) {
            itemList = channelNodes.item(j).getChildNodes();
            displayItemChild(itemList);
        }
    }

    public void displayItemChild(NodeList list) throws IOException {

        JSONObject jsonObject = new JSONObject();
        bufferObject = jsonObject;

        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).getNodeName().equals("description")) {
                imgURLSelection(list.item(i));
            } else {
                jsonObject.put("" + list.item(i).getNodeName() + "", list.item(i).getTextContent());
            }
        }
        jsonContainer.addNewJsonRecord(jsonObject);
    }


    public int xmlItemCounter(NodeList list) {

        int counterItem = 0;
        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).getNodeName().equals("item")) {
                counterItem++;
            }
        }
        return counterItem;
    }

    public void imgURLSelection(Node descriptionNode) throws IOException {

        NodeList descriptionNodeChildNodes = descriptionNode.getChildNodes();

        getDescriptionContent(descriptionNodeChildNodes);

        bufferObject.put("" + descriptionNode.getNodeName() + "", descriptionNode.getTextContent());


    }

    public void getDescriptionContent(NodeList descriptionNodeChildNodes) throws IOException {

        for (int i = 0; i < descriptionNodeChildNodes.getLength(); i++) {
            String imgString = descriptionNodeChildNodes.item(i).getNodeValue();
            String[] imgStringArray = imgString.split(" ");
            getCharsFromSRCString(imgStringArray);
        }
    }

    public void getCharsFromSRCString(String[] imgStringArray) throws IOException {

        char[] subStringImg;
        for (int j = 0; j < imgStringArray.length; j++) {
            if (imgStringArray[j].contains("src=")) {
                subStringImg = imgStringArray[j].toCharArray();
                concatCharIntoImgURL(subStringImg);
            }
        }
    }

    public void concatCharIntoImgURL(char[] subStringImg) throws IOException {

        String ImgLink = "";
        for (int k = 5; k < subStringImg.length - 1; k++) {
            ImgLink = ImgLink + subStringImg[k];
        }
        allImgLinks = allImgLinks + ImgLink + "\n";
        write(allImgLinks);
    }

    public void write(String text) throws IOException {

        if (!folder.exists()) {
            folder.mkdirs();
        }
        createImgFile(text);
    }

    public void createImgFile(String text) throws IOException {

        if (!imgLinksFile.exists()) {
            imgLinksFile.createNewFile();
        }
        PrintWriter out = new PrintWriter(imgLinksFile.getAbsoluteFile());
        try {
            out.print(text + "\n");
        } finally {
            out.close();
        }
    }
}


