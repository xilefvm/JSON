import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    private static String fitxer = "examen.xml";

    private static void llegeixXML(String fitxer) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        Document document = documentBuilder.parse(fitxer);

        document.getDocumentElement().normalize();
        Element root= document.getDocumentElement();
        System.out.println(root.getNodeName());

        NodeList nodeList=root.getChildNodes();

        for(int i=0;i<nodeList.getLength();i++){
            Node node=nodeList.item(i);
            if(node.getNodeType()==Node.ELEMENT_NODE){
                System.out.println(node.getNodeName()+": ");
                NodeList nodeList1=node.getChildNodes();
                for(int x=0;x<nodeList1.getLength();x++){
                    Node childNode=nodeList1.item(x);
                    if(childNode.getNodeType()==Node.ELEMENT_NODE){
                        Element e=(Element) childNode;
                        System.out.println(e.getTextContent()+",");
                    }
                }
                System.out.println();
            }
        }

        JSONArray array=new JSONArray();

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("Name","Belgian Waffles");
        jsonObject.put("price","$5.95");
        jsonObject.put("description","Two of our famous Belgian Waffles with plenty of real maple syrup");
        jsonObject.put("calories","650");

        System.out.println(jsonObject.toString());
        array.put(jsonObject);

        JSONObject jsonObject1=new JSONObject();
        jsonObject1.put("name","Strawberry Belgian Waffles");
        jsonObject1.put("price","$7.95");
        jsonObject1.put("description","Light Belgian waffles covered with strawberries and whipped cream");
        jsonObject1.put("calories","900");

        System.out.println(jsonObject1.toString());
        array.put(jsonObject1);

        JSONObject jsonObject2=new JSONObject();
        jsonObject2.put("name","Berry-Berry Belgian Waffles");
        jsonObject2.put("price","$8.95");
        jsonObject2.put("description","Light Belgian waffles covered with an assortment of fresh berries and whipped cream");
        jsonObject2.put("calories","900");

        System.out.println(jsonObject2.toString());
        array.put(jsonObject2);

        System.out.println("===============================");

        System.out.println(array.toString(2)+"\n");
        writeJSONArrayToFile(array,"examen.json");
    }

    private static void writeJSONArrayToFile(JSONArray json, String ruta) throws IOException {
        //Abrimos el writer
        try (FileWriter fileWriter = new FileWriter(ruta)) {
            //Creamos el file para poder definir la ruta (No necesario)
            File f = new File(ruta);
            //Escribimos el objeto json en la ruta
            fileWriter.write(json.toString(2));
            //Mensaje por pantalla (No necesario)
            System.out.println("S'ha guardat l'objecte JSON a la ruta "+f.getAbsolutePath());
        }
    }


    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        llegeixXML(fitxer);
    }
}
