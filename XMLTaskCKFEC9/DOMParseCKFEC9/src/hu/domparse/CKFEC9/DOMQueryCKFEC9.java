package hu.domparse.CKFEC9;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DOMQueryCKFEC9 {
    public static void main(String[] args) {
        try {
            // XML dokumentum betöltése
            File inputFile = new File("C:\\Users\\varbendi\\Desktop\\xml\\DOMParseCKFEC9\\XMLCKFEC9.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // 1. Gyökérelem kiírása
            System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());

            // 2. Összes "Telefon" elem kiírása
            NodeList telefonList = doc.getElementsByTagName("Telefon");
            for (int i = 0; i < telefonList.getLength(); i++) {
                Node node = telefonList.item(i);
                System.out.println("Telefon: " + node.getTextContent());
            }

            // 3. Adott "JegyID" elem kiírása
            NodeList jegyList = doc.getElementsByTagName("HibajegyRecord");
            for (int i = 0; i < jegyList.getLength(); i++) {
                Element elem = (Element) jegyList.item(i);
                System.out.println("JegyID: " + elem.getAttribute("JegyID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
