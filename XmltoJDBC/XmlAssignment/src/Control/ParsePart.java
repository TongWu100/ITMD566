package Control;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Model.PartService;


public class ParsePart {
	public static ArrayList<PartService> getParseService() {
        ArrayList<PartService> list= new ArrayList<PartService>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
               builder = factory.newDocumentBuilder();
               Document doc;
               doc = builder.parse("File//PartsServices.xml");
                NodeList partNodes = doc.getElementsByTagName("Part");
                for(int i = 0; i<partNodes.getLength();i++){
                    Element partElement = (Element) partNodes.item(i);
                    PartService part = new PartService();
                    NodeList childNodes =partElement.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                    if(childNodes.item(j).getNodeType()==Node.ELEMENT_NODE){
                        if("partId".equals(childNodes.item(j).getNodeName())){
                            part.setId((childNodes.item(j).getFirstChild().getNodeValue()));
                        }else if("partType".equals(childNodes.item(j).getNodeName())) {
                           part.setType((childNodes.item(j).getFirstChild().getNodeValue()));
                        }else if("partAmount".equals(childNodes.item(j).getNodeName())) {
                        	part.setAmount(Double.parseDouble((childNodes.item(j).getFirstChild().getNodeValue())));
                        }else if("cost".equals(childNodes.item(j).getNodeName())) {
                        	part.setCost(Double.parseDouble((childNodes.item(j).getFirstChild().getNodeValue())));
                        }
                    }
                }
                    list.add(part);
                }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return list;

    }
}
