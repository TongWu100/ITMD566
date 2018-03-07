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

import Model.CarRental;


public class ParseCar {
	public static ArrayList<CarRental> getParseCarRental() {
        ArrayList<CarRental> list= new ArrayList<CarRental>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
               builder = factory.newDocumentBuilder();
               Document doc;
               doc = builder.parse("File//CarRental.xml");
                NodeList carNodes = doc.getElementsByTagName("Car");
                for(int i = 0; i<carNodes.getLength();i++){
                    Element carElement = (Element) carNodes.item(i);
                    CarRental car = new CarRental();
                    NodeList childNodes =carElement.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                    if(childNodes.item(j).getNodeType()==Node.ELEMENT_NODE){
                        if("carId".equals(childNodes.item(j).getNodeName())){
                            car.setId((childNodes.item(j).getFirstChild().getNodeValue()));
                        }else if("carType".equals(childNodes.item(j).getNodeName())) {
                           car.setType((childNodes.item(j).getFirstChild().getNodeValue()));
                        }else if("dateRented".equals(childNodes.item(j).getNodeName())) {
                        	car.setDateRented((childNodes.item(j).getFirstChild().getNodeValue()));
                        }else if("dateReturned".equals(childNodes.item(j).getNodeName())) {
                        	car.setDateReturned((childNodes.item(j).getFirstChild().getNodeValue()));
                        }else if("rentCostDaily".equals(childNodes.item(j).getNodeName())) {
                        	car.setRentCostDaily(Double.parseDouble((childNodes.item(j).getFirstChild().getNodeValue())));
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
