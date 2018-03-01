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

import Model.Customer;


public class ParseCustomer {
	public static ArrayList<Customer> getParseCustomer() {  
        ArrayList<Customer> list= new ArrayList<Customer>();  
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder builder;  
        try {  
               builder = factory.newDocumentBuilder();  
               Document doc;  
               doc = builder.parse("File//Customer.xml");  
                NodeList cusNodes = doc.getElementsByTagName("Customer");  
                for(int i = 0; i<cusNodes.getLength();i++){  
                    Element cusElement = (Element) cusNodes.item(i);  
                    Customer customer = new Customer();  
                    NodeList childNodes =cusElement.getChildNodes();  
                    for (int j = 0; j < childNodes.getLength(); j++) {  
                    if(childNodes.item(j).getNodeType()==Node.ELEMENT_NODE){  
                        if("UserID".equals(childNodes.item(j).getNodeName())){  
                            customer.setId((childNodes.item(j).getFirstChild().getNodeValue()));  
                        }else if("FullName".equals(childNodes.item(j).getNodeName())){  
                           customer.setFullname(((childNodes.item(j).getFirstChild().getNodeValue())));  
                        }else if("PassWord".equals(childNodes.item(j).getNodeName())) {
                           customer.setPassword(childNodes.item(j).getFirstChild().getNodeValue());	
                        }else if("isAdmin".equals(childNodes.item(j).getNodeName())) {
                        	customer.setIsadmin(childNodes.item(j).getFirstChild().getNodeValue());
                        }else if("Email".equals(childNodes.item(j).getNodeName())) {
                        	customer.setEmail(childNodes.item(j).getFirstChild().getNodeValue());
                        }else if("Balance".equals((childNodes.item(j).getNodeName()))) {
                        	customer.setBalance((Double.parseDouble((childNodes.item(j).getFirstChild().getNodeValue()))));
                        }else if("StreetAddress".equals(childNodes.item(j).getNodeName())) {
                        	customer.setStreetaddress(childNodes.item(j).getFirstChild().getNodeValue());
                        }else if("City".equals(childNodes.item(j).getNodeName())) {
                        	customer.setCity(childNodes.item(j).getFirstChild().getNodeValue());
                        }else if("State".equals(childNodes.item(j).getNodeName())) {
                        	customer.setState(childNodes.item(j).getFirstChild().getNodeValue());
                        }
                    }  
                }  
                    list.add(customer);  
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
