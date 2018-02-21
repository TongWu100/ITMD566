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

import Model.Car;


public class ParseCar {
	public static ArrayList<Car> getParseCar() {  
        ArrayList<Car> list= new ArrayList<Car>();  
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder builder;  
        try {  
               builder = factory.newDocumentBuilder();  
               Document doc;  
               doc = builder.parse("File//Car.xml");  
                NodeList carNodes = doc.getElementsByTagName("Car");  
                for(int i = 0; i<carNodes.getLength();i++){  
                    Element carElement = (Element) carNodes.item(i);  
                    Car car = new Car();
                    NodeList childNodes =carElement.getChildNodes();  
                    for (int j = 0; j < childNodes.getLength(); j++) {  
                    if(childNodes.item(j).getNodeType()==Node.ELEMENT_NODE){  
                        if("CarID".equals(childNodes.item(j).getNodeName())){  
                            car.setId((childNodes.item(j).getFirstChild().getNodeValue()));  
                        }else if("CarName".equals(childNodes.item(j).getNodeName())){  
                           car.setName((childNodes.item(j).getFirstChild().getNodeValue()));  
                        }else if("CarType".equals(childNodes.item(j).getNodeName())) {
                           car.setType((childNodes.item(j).getFirstChild().getNodeValue()));	
                        }else if("BuyPrice".equals(childNodes.item(j).getNodeName())) {
                        	car.setBuyPrice(Double.parseDouble((childNodes.item(j).getFirstChild().getNodeValue())));
                        }else if("RentPrice".equals(childNodes.item(j).getNodeName())) {
                        	car.setRentPrice(Double.parseDouble((childNodes.item(j).getFirstChild().getNodeValue())));
                        }
                    }  
                }  
                    list.add(car);  
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
