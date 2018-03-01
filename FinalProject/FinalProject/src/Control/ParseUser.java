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

import Model.User;

public class ParseUser {
	public static ArrayList<User> getParseUser() {  
        ArrayList<User> list= new ArrayList<User>();  
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder builder;  
        try {  
               builder = factory.newDocumentBuilder();  
               Document doc;  
               doc = builder.parse("File//User.xml");  
                NodeList userNodes = doc.getElementsByTagName("User");  
                for(int i = 0; i<userNodes.getLength();i++){  
                    Element userElement = (Element) userNodes.item(i);  
                    User user = new User();  
                    NodeList childNodes =userElement.getChildNodes();  
                    for (int j = 0; j < childNodes.getLength(); j++) {  
                    if(childNodes.item(j).getNodeType()==Node.ELEMENT_NODE){  
                        if("UserID".equals(childNodes.item(j).getNodeName())){  
                            user.setId((childNodes.item(j).getFirstChild().getNodeValue()));  
                        }else if("PassWord".equals(childNodes.item(j).getNodeName())){  
                           user.setPassword(((childNodes.item(j).getFirstChild().getNodeValue())));  
                        }else if("FullName".equals(childNodes.item(j).getNodeName())) {
                           user.setFullname(childNodes.item(j).getFirstChild().getNodeValue());	
                        }else if("Email".equals(childNodes.item(j).getNodeName())) {
                        	user.setEmail(childNodes.item(j).getFirstChild().getNodeValue());
                        }else if("StreetAddress".equals(childNodes.item(j).getNodeName())) {
                        	user.setStreetaddress(childNodes.item(j).getFirstChild().getNodeValue());
                        }else if("City".equals(childNodes.item(j).getNodeName())) {
                        	user.setCity((childNodes.item(j).getFirstChild().getNodeValue()));
                        }else if("State".equals(childNodes.item(j).getNodeName())) {
                        	user.setState((childNodes.item(j).getFirstChild().getNodeValue()));
                    }  
                }  
                    list.add(user);  
                } 
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
