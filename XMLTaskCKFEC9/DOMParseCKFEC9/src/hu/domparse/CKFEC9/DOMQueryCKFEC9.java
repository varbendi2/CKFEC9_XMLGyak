package hu.domparse.CKFEC9;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DOMQueryCKFEC9 {

    public static void main(String[] args) {
        try {
            Document document = DOMQueryCKFEC9.loadXML("DOMParseCKFEC9\\XMLCKFEC9.xml");

            // 1. Gyökérelem kiírása
            System.out.println("1. lekérdezés:");
            System.out.println("Gyökérelem: " + getRootElementName(document));

            // 2. Az összes "Telefon" elem kiírása
            System.out.println("\n2. lekérdezés:");
            listAllTelefonElements(document);

            // 3. Adott "JegyID" elem kiírása
            System.out.println("\n3. lekérdezés:");
            listAllJegyIDs(document);

            // 4. Az összes hibajegyet tartalmazó telefonos bejegyzés
            System.out.println("\n4. lekérdezés:");
            listAllTelefonWithHibajegy(document);

            // 5. Az aktív "Hibajegy" elem száma
            System.out.println("\n5. lekérdezés:");
            System.out.println("Aktív hibajegyek száma: " + countActiveHibajegyek(document));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Gyökérelem neve
    private static String getRootElementName(Document document) {
        return document.getDocumentElement().getNodeName();
    }

    // Összes "Telefon" elem kiírása
    private static void listAllTelefonElements(Document document) {
        NodeList telefonList = document.getElementsByTagName("Telefon");
        if (telefonList.getLength() == 0) {
            System.out.println("Nincs Telefon elem az XML fájlban.");
            return;
        }
        for (int i = 0; i < telefonList.getLength(); i++) {
            Element telefon = (Element) telefonList.item(i);
            System.out.println("Telefon: " + telefon.getTextContent());
        }
    }

    // Összes "JegyID" attribútum kiírása a "Hibajegy" elemekből
    private static void listAllJegyIDs(Document document) {
        NodeList jegyList = document.getElementsByTagName("Hibajegy");
        if (jegyList.getLength() == 0) {
            System.out.println("Nincs Hibajegy elem az XML fájlban.");
            return;
        }
        for (int i = 0; i < jegyList.getLength(); i++) {
            Element elem = (Element) jegyList.item(i);
            String jegyID = elem.getAttribute("JegyID");
            System.out.println("JegyID: " + jegyID);
        }
    }

    // Az összes hibajegyet tartalmazó telefonos bejegyzés
    private static void listAllTelefonWithHibajegy(Document document) {
        NodeList telefonList = document.getElementsByTagName("Telefon");
        for (int i = 0; i < telefonList.getLength(); i++) {
            Element telefon = (Element) telefonList.item(i);
            NodeList hibajegyList = telefon.getElementsByTagName("Hibajegy");
            if (hibajegyList.getLength() > 0) {
                System.out.println("Telefon: " + telefon.getTextContent() + " Hibajegyek: ");
                for (int j = 0; j < hibajegyList.getLength(); j++) {
                    Element hibajegy = (Element) hibajegyList.item(j);
                    String jegyID = hibajegy.getAttribute("JegyID");
                    System.out.println("  - JegyID: " + jegyID);
                }
            }
        }
    }

    // Aktív "Hibajegy" elem száma
    private static int countActiveHibajegyek(Document document) {
        int count = 0;
        NodeList hibajegyList = document.getElementsByTagName("Hibajegy");
        for (int i = 0; i < hibajegyList.getLength(); i++) {
            Element hibajegy = (Element) hibajegyList.item(i);
            String status = hibajegy.getAttribute("status");
            if ("aktív".equals(status)) {
                count++;
            }
        }
        return count;
    }

    // XML dokumentum betöltése
    private static Document loadXML(String filePath) throws Exception {
        File inputFile = new File(filePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(inputFile);
        document.getDocumentElement().normalize();
        return document;
    }
}
