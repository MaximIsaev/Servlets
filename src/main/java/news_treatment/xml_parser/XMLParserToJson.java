package news_treatment.xml_parser;

import news_treatment.json_news_storage.JSONStorage;
import configuration.sources.SourceConfig;
import org.json.simple.JSONArray;
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

public class XMLParserToJson {

    JSONObject bufferObject;
    String allImgLinks = "";
    File imgLinksFile;
    File folder;
    SourceConfig sourceConfig;

    public XMLParserToJson(SourceConfig sourceConfig) {
        this.sourceConfig = sourceConfig;
        imgLinksFile = new File(sourceConfig.getImgFileHomePath());
        folder = new File(sourceConfig.getImgFolderHomePath());
    }


    public JSONArray parseToJson(String xmlNewsDownloadedFilePath) {

        JSONStorage jsonStorage = new JSONStorage();
        try {

            DocumentBuilder xml = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            Document doc = xml.parse(xmlNewsDownloadedFilePath);

            Element rootElement = doc.getDocumentElement();
            NodeList rootElementChildNodes = rootElement.getChildNodes();

            for (int i = 0; i < rootElementChildNodes.getLength(); i++) {
                NodeList channelNodes = rootElementChildNodes.item(i).getChildNodes();
                int itemCount = xmlItemCounter(channelNodes);
                getItemsChildNodes(channelNodes, itemCount, jsonStorage);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonStorage.getJsonNewsStorageList();
    }

    private void getItemsChildNodes(NodeList channelNodes, int itemCount, JSONStorage jsonStorage) throws IOException {

        NodeList itemNodeList;
        for (int j = channelNodes.getLength() - itemCount; j < channelNodes.getLength(); j++) {
            itemNodeList = channelNodes.item(j).getChildNodes();
            convertItemChildesToJson(itemNodeList, jsonStorage);
        }
    }

    private void convertItemChildesToJson(NodeList itemNodeList, JSONStorage jsonStorage) throws IOException { //rename;public;parameters;

        JSONObject jsonObject = new JSONObject();
        bufferObject = jsonObject;

        for (int i = 0; i < itemNodeList.getLength(); i++) {
            if (itemNodeList.item(i).getNodeName().equals("description")) {
                imgURLSelection(itemNodeList.item(i));
            } else {
                jsonObject.put(itemNodeList.item(i).getNodeName(), itemNodeList.item(i).getTextContent());
            }
        }
        jsonStorage.addNewJsonRecord(jsonObject);
    }


    private int xmlItemCounter(NodeList list) {

        int counterItem = 0;
        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).getNodeName().equals("item")) {
                counterItem++;
            }
        }
        return counterItem;
    }

    private void imgURLSelection(Node descriptionNode) throws IOException {

        NodeList descriptionNodeChildNodes = descriptionNode.getChildNodes();
        getDescriptionContent(descriptionNodeChildNodes);
        bufferObject.put("" + descriptionNode.getNodeName() + "", descriptionNode.getTextContent());
    }

    private void getDescriptionContent(NodeList descriptionNodeChildNodes) throws IOException {

        for (int i = 0; i < descriptionNodeChildNodes.getLength(); i++) {
            String imgString = descriptionNodeChildNodes.item(i).getNodeValue();
            String[] imgStringArray = imgString.split(" ");
            getCharsFromSRCString(imgStringArray);
        }
    }

    private void getCharsFromSRCString(String[] imgStringArray) throws IOException {

        char[] subStringImg;
        for (int j = 0; j < imgStringArray.length; j++) {
            if (imgStringArray[j].contains("src=")) {
                subStringImg = imgStringArray[j].toCharArray();
                concatCharIntoImgURL(subStringImg);
            }
        }
    }

    private void concatCharIntoImgURL(char[] subStringImg) throws IOException {

        String ImgLink = "";
        for (int k = 5; k < subStringImg.length - 1; k++) {
            ImgLink = ImgLink + subStringImg[k];
        }
        allImgLinks = allImgLinks + ImgLink + "\n";
        write(allImgLinks);
    }

    private void write(String text) throws IOException {

        if (!folder.exists()) {
            folder.mkdirs();
        }
        createImgFile(text);
    }

    private void createImgFile(String text) throws IOException {

        if (!imgLinksFile.exists()) {
            imgLinksFile.createNewFile();
        }
        try (PrintWriter out = new PrintWriter(imgLinksFile.getAbsoluteFile())) {
            out.print(text + "\n");
        }
    }
}


