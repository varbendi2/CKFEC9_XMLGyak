package domckfec91022;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory; 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.w3c.dom.Document; 
import org.w3c.dom.NodeList; 
import org.w3c.dom.Node; 
import org.w3c.dom.Element;

public class DomRead {
    public static void main(String argv[]) throws SAXException, IOException, ParserConfigurationException {
        // XML file megnyitása
        File xmlFile = new File("hallgatok.xml");
        
        // példányosítás a DocumentBuilderFactory osztályt a statikus newInstance() metódussal.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        
        // DOM fa elõállítása
        Document neptunkod = dBuilder.parse(xmlFile);
        
        // A dokumentum normalizálása segít a helyes eredmények elérésében.
        neptunkod.getDocumentElement().normalize();
        
        System.out.println("Gyökér elem: " + neptunkod.getDocumentElement().getNodeName()); // Kiíratjuk a dokumentum gyökérelemét

        // A fa megadott névvel (hallgato) rendelkezõ csomópontjainak összegyûjtése listába.
        NodeList nlist = neptunkod.getElementsByTagName("hallgato"); // gyerekelemek mentése listába

        for (int i = 0; i < nlist.getLength(); i++) {
            // A listán for ciklussal megyünk végig.
            // Lekérjük a lista aktuális elemét
            Node nNode = nlist.item(i);
            System.out.println("\nAktuális elem: " + nNode.getNodeName());
            
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                // Elementté konvertáljuk az aktuális elemet
                Element elem = (Element) nNode;
                
                // Lekérjük az aktuális elem attribútumának tartalmát
                String hid = elem.getAttribute("id");
                
                // Lekérjük az aktuális elem gyerekelemeit és annak tartalmát
                String kname = elem.getElementsByTagName("keresztnev").item(0).getTextContent();
                String vname = elem.getElementsByTagName("vezeteknev").item(0).getTextContent();
                String fname = elem.getElementsByTagName("foglalkozas").item(0).getTextContent();
                
                // Formázva kiíratjuk a lekért információkat az adott elemrõl
                System.out.println("Hallgató id: " + hid);
                System.out.println("Keresztnév: " + kname);
                System.out.println("Vezetéknév: " + vname);
                System.out.println("Foglalkozás: " + fname);
            }
        }
    }
}
