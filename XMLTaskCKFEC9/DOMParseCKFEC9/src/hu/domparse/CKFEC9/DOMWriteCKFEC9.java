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

            // Fa struktúra kiírása
            printTree(doc.getDocumentElement(), 0);

            // Új hibajegy hozzáadása
            addHibajegy(doc, "4", "ujemail@gmail.com", "Kis Pista", "36123456789", "987654321", "E:505", "Fagy a kép", "Egy rövid sípolás", "Kattogás a vonalban", "258963", "Power", "Nem csatlakozik az internet");

            // Felvételek hozzáadása
            addFelvetel(doc, "1", "TV", "Hibajavítás elkezdődött");
            addFelvetel(doc, "2", "Telefon", "Új kábel telepítve");

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

    // Új hibajegy hozzáadása
    public static void addHibajegy(Document doc, String jegyID, String email, String nev, String telefon, String mtID, String hibakod, String hibaLeirasTV, String hangTelefon, String leirasTelefon, String internetID, String ledek, String leirasInternet) {
        Element root = doc.getDocumentElement();

        // Új Hibajegy elem létrehozása
        Element newHibajegy = doc.createElement("Hibajegy");
        newHibajegy.setAttribute("JegyID", jegyID);

        // KapcsolattartóiAdatok
        Element kapcsolattartoiAdatok = doc.createElement("KapcsolattartoiAdatok");
        kapcsolattartoiAdatok.appendChild(createTextElement(doc, "Email", email));
        kapcsolattartoiAdatok.appendChild(createTextElement(doc, "Nev", nev));
        kapcsolattartoiAdatok.appendChild(createTextElement(doc, "Telefonszam", telefon));
        newHibajegy.appendChild(kapcsolattartoiAdatok);

        // TV
        Element tv = doc.createElement("TV");
        tv.appendChild(createTextElement(doc, "MTID", mtID));
        Element tvHiba = doc.createElement("Hiba");
        tvHiba.appendChild(createTextElement(doc, "Hibakod", hibakod));
        tvHiba.appendChild(createTextElement(doc, "HibaLeiras", hibaLeirasTV));
        tv.appendChild(tvHiba);
        newHibajegy.appendChild(tv);

        // Telefon
        Element telefonElem = doc.createElement("Telefon");
        telefonElem.appendChild(createTextElement(doc, "Telefonszam", telefon));
        Element telefonHiba = doc.createElement("Hiba");
        telefonHiba.appendChild(createTextElement(doc, "Hang", hangTelefon));
        telefonHiba.appendChild(createTextElement(doc, "Leiras", leirasTelefon));
        telefonElem.appendChild(telefonHiba);
        newHibajegy.appendChild(telefonElem);

        // Internet
        Element internet = doc.createElement("Internet");
        internet.appendChild(createTextElement(doc, "InternetID", internetID));
        Element internetHiba = doc.createElement("Hiba");
        internetHiba.appendChild(createTextElement(doc, "LEDek", ledek));
        internetHiba.appendChild(createTextElement(doc, "Leiras", leirasInternet));
        internet.appendChild(internetHiba);
        newHibajegy.appendChild(internet);

        // Új Hibajegy hozzáadása a gyökérelemhez
        root.appendChild(newHibajegy);
    }

    // Új Felvétel hozzáadása
    public static void addFelvetel(Document doc, String felvetelID, String eszkoz, String leiras) {
        Element root = doc.getDocumentElement();

        // Új Felvetel elem létrehozása
        Element newFelvetel = doc.createElement("Felvetel");
        newFelvetel.setAttribute("FelvetelID", felvetelID);
        newFelvetel.appendChild(createTextElement(doc, "Eszkoz", eszkoz));
        newFelvetel.appendChild(createTextElement(doc, "Leiras", leiras));

        // Új Felvetel hozzáadása a gyökérelemhez
        root.appendChild(newFelvetel);
    }

    // Szöveges elem létrehozása
    public static Element createTextElement(Document doc, String tagName, String textContent) {
        Element element = doc.createElement(tagName);
        element.appendChild(doc.createTextNode(textContent));
        return element;
    }
}
