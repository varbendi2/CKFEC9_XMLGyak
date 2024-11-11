package domCKFEC91105;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.StringWriter;
import java.io.FileOutputStream;

public class DomModifyCKFEC9_1 {

    public static void main(String[] args) {
        try {
 
            File xmlFile = new File("C:\\Users\\Varga Bence\\Desktop\\XML\\1105\\orarendCKFEC9.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);


            doc.getDocumentElement().normalize();

  
            NodeList oraList = doc.getElementsByTagName("ora");
            for (int i = 0; i < oraList.getLength(); i++) {
                Element ora = (Element) oraList.item(i);


                NodeList oraadoList = ora.getElementsByTagName("oraado");
                if (oraadoList.getLength() == 0) {
                    Element oraado = doc.createElement("oraado");
                    oraado.setTextContent("Minta Tanar");
                    ora.appendChild(oraado);
                }


                if ("gyakorlat".equals(ora.getAttribute("tipus"))) {
                    ora.setAttribute("tipus", "eloadas");
                }
            }


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult consoleResult = new StreamResult(writer);
            transformer.transform(source, consoleResult);


            System.out.println(writer.toString());


            StreamResult fileResult = new StreamResult(new FileOutputStream("orarendModifyCKFEC9.xml"));
            transformer.transform(source, fileResult);

            System.out.println("A módosított fájl mentve: orarendModifyCKFEC9.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
