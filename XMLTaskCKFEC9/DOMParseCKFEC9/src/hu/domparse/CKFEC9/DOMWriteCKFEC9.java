package hu.domparse.CKFEC9;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DOMWriteCKFEC9 {
    public static void main(String[] args) {
        try {
            // XML dokumentum beolvasása
            File inputFile = new File("DOMParseCKFEC9\\XMLCKFEC9.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // XML fájl tartalmának fa struktúra kiírása
            printTree(doc.getDocumentElement(), 0);

            // Az XML fájl mentése új fájlba
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("XMLCKFEC91.xml"));
            transformer.transform(source, result);
            System.out.println("Az XML fájl sikeresen mentve: XMLCKFEC91.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Fa struktúra kiírása
    public static void printTree(Node node, int depth) {
        for (int i = 0; i < depth; i++) System.out.print("  ");
        System.out.println(node.getNodeName() + ": " + node.getTextContent().trim());
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            printTree(childNodes.item(i), depth + 1);
        }
    }
}
