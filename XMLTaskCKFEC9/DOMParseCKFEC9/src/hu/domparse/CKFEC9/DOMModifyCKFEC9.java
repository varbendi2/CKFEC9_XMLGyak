package hu.domparse.CKFEC9;

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
            Document document = loadXML("DOMParseCKFEC9\\XMLCKFEC9.xml");

            // 1. Új elem hozzáadása
            System.out.println("1. Új elem hozzáadása:");
            addNewElement(document, "ÚjElem", "Új tartalom");

            // 2. Telefon elem módosítása
            System.out.println("\n2. Telefon elem módosítása:");
            modifyTelefonElement(document, "Telefon", "Módosított tartalom");

            // 3. Új elem törlése
            System.out.println("\n3. Elem törlése:");
            deleteElement(document, "Telefonszám");

            // 4. Elem nevének módosítása
            System.out.println("\n4. Elem nevének módosítása:");
            modifyElementName(document, "Telefon", "ÚjTelefon");

            // 5. Elem attribútumának módosítása
            System.out.println("\n5. Elem attribútumának módosítása:");
            modifyElementAttribute(document, "Telefon", "type", "mobile");

            // 6. Több elem keresése és módosítása
            System.out.println("\n6. Több elem módosítása:");
            modifyMultipleElements(document, "Telefonszám", "Új szám");

            // 7. Üres elem eltávolítása
            System.out.println("\n7. Üres elem eltávolítása:");
            removeEmptyElements(document);

            // 8. Elem duplikálása
            System.out.println("\n8. Elem duplikálása:");
            duplicateElement(document, "Telefon");

            // 9. Az XML fájl mentése
            saveXML(document, "XMLCKFEC9_Modified.xml");
            System.out.println("\nAz XML fájl sikeresen módosítva: XMLCKFEC9_Modified.xml");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static Document loadXML(String filePath) throws Exception {
        File inputFile = new File(filePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(inputFile);
        doc.getDocumentElement().normalize();
        return doc;
    }

    private static void addNewElement(Document doc, String elementName, String elementContent) {
        Element newElement = doc.createElement(elementName);
        newElement.setTextContent(elementContent);
        doc.getDocumentElement().appendChild(newElement);
        System.out.println("Hozzáadott elem: <" + elementName + ">" + elementContent + "</" + elementName + ">");
    }

    private static void modifyTelefonElement(Document doc, String tagName, String newContent) {
        NodeList nodeList = doc.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            String oldContent = node.getTextContent();
            node.setTextContent(newContent);
            System.out.println("Eredeti tartalom: " + oldContent);
            System.out.println("Új tartalom: " + newContent);
        } else {
            System.out.println("Nincs ilyen nevű elem: " + tagName);
        }
    }

    private static void deleteElement(Document doc, String tagName) {
        NodeList nodeList = doc.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            node.getParentNode().removeChild(node);
            System.out.println("Törölt elem: " + tagName);
        } else {
            System.out.println("Nincs ilyen nevű elem a törléshez: " + tagName);
        }
    }

    private static void modifyElementName(Document doc, String oldTagName, String newTagName) {
        NodeList nodeList = doc.getElementsByTagName(oldTagName);
        if (nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            Element newElement = doc.createElement(newTagName);
            newElement.setTextContent(node.getTextContent());
            node.getParentNode().replaceChild(newElement, node);
            System.out.println("Elem neve módosítva: <" + oldTagName + "> -> <" + newTagName + ">");
        } else {
            System.out.println("Nincs ilyen nevű elem: " + oldTagName);
        }
    }

    private static void modifyElementAttribute(Document doc, String tagName, String attributeName, String newValue) {
        NodeList nodeList = doc.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            Element element = (Element) nodeList.item(0);
            String oldValue = element.getAttribute(attributeName);
            element.setAttribute(attributeName, newValue);
            System.out.println("Attribútum módosítva: " + attributeName + " = " + oldValue + " -> " + newValue);
        } else {
            System.out.println("Nincs ilyen nevű elem: " + tagName);
        }
    }

    private static void modifyMultipleElements(Document doc, String tagName, String newContent) {
        NodeList nodeList = doc.getElementsByTagName(tagName);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            String oldContent = node.getTextContent();
            node.setTextContent(newContent);
            System.out.println("Eredeti tartalom: " + oldContent);
            System.out.println("Új tartalom: " + newContent);
        }
    }

    private static void removeEmptyElements(Document doc) {
        NodeList nodeList = doc.getElementsByTagName("*");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            if (element.getTextContent().trim().isEmpty()) {
                element.getParentNode().removeChild(element);
                System.out.println("Eltávolított üres elem: " + element.getTagName());
            }
        }
    }

    private static void duplicateElement(Document doc, String tagName) {
        NodeList nodeList = doc.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            Node originalNode = nodeList.item(0);
            Node copiedNode = originalNode.cloneNode(true);
            originalNode.getParentNode().appendChild(copiedNode);
            System.out.println("Elem duplikálva: " + tagName);
        } else {
            System.out.println("Nincs ilyen nevű elem a duplikáláshoz: " + tagName);
        }
    }

    private static void saveXML(Document doc, String outputPath) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(outputPath));
        transformer.transform(source, result);
    }
}
