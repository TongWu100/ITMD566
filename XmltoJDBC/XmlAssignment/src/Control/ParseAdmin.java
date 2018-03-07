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

import Model.Admin;


public class ParseAdmin {
	public static ArrayList<Admin> getParseAdmin() {
        ArrayList<Admin> list= new ArrayList<Admin>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
               builder = factory.newDocumentBuilder();
               Document doc;
               doc = builder.parse("File//Admin.xml");
                NodeList adminNodes = doc.getElementsByTagName("Admin");
                for(int i = 0; i<adminNodes.getLength();i++){
                    Element adminElement = (Element) adminNodes.item(i);
                    Admin admin = new Admin();
                    NodeList childNodes =adminElement.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                    if(childNodes.item(j).getNodeType()==Node.ELEMENT_NODE){
                        if("AdminID".equals(childNodes.item(j).getNodeName())){
                            admin.setId((childNodes.item(j).getFirstChild().getNodeValue()));
                        }else if("FullName".equals(childNodes.item(j).getNodeName())) {
                        	admin.setFullname(childNodes.item(j).getFirstChild().getNodeValue());
                        }else if("Password".equals(childNodes.item(j).getNodeName())) {
                        	admin.setPassword(childNodes.item(j).getFirstChild().getNodeValue());
                        }
                }
                    
                }list.add(admin);
          }
        }catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return list;


}
}
