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

import Model.CustomerAccount;

public class ParseCustomerAccount {
	public static ArrayList<CustomerAccount> getParseCustomerAccount() {  
        ArrayList<CustomerAccount> list= new ArrayList<CustomerAccount>();  
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder builder;  
        try {  
               builder = factory.newDocumentBuilder();  
               Document doc;  
               doc = builder.parse("File//CustomerAccount.xml");  
                NodeList cusaNodes = doc.getElementsByTagName("CustomerAccount");  
                for(int i = 0; i<cusaNodes.getLength();i++){  
                    Element cusaElement = (Element) cusaNodes.item(i);  
                    CustomerAccount cusa = new CustomerAccount();  
                    NodeList childNodes =cusaElement.getChildNodes();  
                    for (int j = 0; j < childNodes.getLength(); j++) {  
                    if(childNodes.item(j).getNodeType()==Node.ELEMENT_NODE){  
                        if("CustomerAccountID".equals(childNodes.item(j).getNodeName())){  
                            cusa.setAccountid(((childNodes.item(j).getFirstChild().getNodeValue())));  
                        }else if("UserID".equals(childNodes.item(j).getNodeName())){  
                           cusa.setUserid(((childNodes.item(j).getFirstChild().getNodeValue())));  
                        }else if("RentCarNumber".equals(childNodes.item(j).getNodeName())) {
                           cusa.setRentCarNumber(Integer.parseInt((childNodes.item(j).getFirstChild().getNodeValue())));	
                        }else if("BuyCarNumber".equals(childNodes.item(j).getNodeName())) {
                        	cusa.setBuyCarNumber(Integer.parseInt((childNodes.item(j).getFirstChild().getNodeValue())));
                        }else if("Balance".equals(childNodes.item(j).getNodeName())) {
                        	cusa.setBalance(Double.parseDouble((childNodes.item(j).getFirstChild().getNodeValue())));
                        }
                    }  
                }  
                    list.add(cusa);  
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
