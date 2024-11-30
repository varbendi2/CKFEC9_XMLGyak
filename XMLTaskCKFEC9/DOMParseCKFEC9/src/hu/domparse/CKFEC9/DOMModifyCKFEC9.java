package hu.domparse.neptunkod;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DOMModifyCKFEC9 {
    public static void main(String[] args) {
        try {
            // XML dokumentum betöltése
            File inputFile = new File("C:\\Users\\varbendi\\Desktop\\xml\\DOMParseCKFEC9\\XMLCKFEC9.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // 1. Új elem hozzáadása
            Element newElement = doc.createElement("ÚjElem");
            newElement.setTextContent("Új tartalom");
            doc.getDocumentElement().appendChild(newElement);

            // 2. Egy elem módosítása
            NodeList telefonList = doc.getElementsByTagName("Telefon");
            if (telefonList.getLength() > 0) {
                telefonList.item(0).setTextContent("Módosított tartalom");
            }

            // Az XML fájl mentése
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("XMLCKFEC9_Modified.xml"));
            transformer.transform(source, result);
            System.out.println("Az XML fájl sikeresen módosítva: XMLCKFEC9_Modified.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
