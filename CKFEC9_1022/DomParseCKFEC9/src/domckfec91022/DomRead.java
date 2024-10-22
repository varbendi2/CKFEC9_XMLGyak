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
        // XML file megnyit�sa
        File xmlFile = new File("hallgatok.xml");
        
        // p�ld�nyos�t�s a DocumentBuilderFactory oszt�lyt a statikus newInstance() met�dussal.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        
        // DOM fa el��ll�t�sa
        Document neptunkod = dBuilder.parse(xmlFile);
        
        // A dokumentum normaliz�l�sa seg�t a helyes eredm�nyek el�r�s�ben.
        neptunkod.getDocumentElement().normalize();
        
        System.out.println("Gy�k�r elem: " + neptunkod.getDocumentElement().getNodeName()); // Ki�ratjuk a dokumentum gy�k�relem�t

        // A fa megadott n�vvel (hallgato) rendelkez� csom�pontjainak �sszegy�jt�se list�ba.
        NodeList nlist = neptunkod.getElementsByTagName("hallgato"); // gyerekelemek ment�se list�ba

        for (int i = 0; i < nlist.getLength(); i++) {
            // A list�n for ciklussal megy�nk v�gig.
            // Lek�rj�k a lista aktu�lis elem�t
            Node nNode = nlist.item(i);
            System.out.println("\nAktu�lis elem: " + nNode.getNodeName());
            
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                // Elementt� konvert�ljuk az aktu�lis elemet
                Element elem = (Element) nNode;
                
                // Lek�rj�k az aktu�lis elem attrib�tum�nak tartalm�t
                String hid = elem.getAttribute("id");
                
                // Lek�rj�k az aktu�lis elem gyerekelemeit �s annak tartalm�t
                String kname = elem.getElementsByTagName("keresztnev").item(0).getTextContent();
                String vname = elem.getElementsByTagName("vezeteknev").item(0).getTextContent();
                String fname = elem.getElementsByTagName("foglalkozas").item(0).getTextContent();
                
                // Form�zva ki�ratjuk a lek�rt inform�ci�kat az adott elemr�l
                System.out.println("Hallgat� id: " + hid);
                System.out.println("Keresztn�v: " + kname);
                System.out.println("Vezet�kn�v: " + vname);
                System.out.println("Foglalkoz�s: " + fname);
            }
        }
    }
}
