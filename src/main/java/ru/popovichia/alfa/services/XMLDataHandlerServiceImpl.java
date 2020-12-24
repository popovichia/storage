package ru.popovichia.alfa.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.popovichia.alfa.data.Box;
import ru.popovichia.alfa.data.Item;

/**
 *
 * @author Igor Popovich, email: popovichia, phone: +7 913 902 36 36, company:
 * popovichia@gmail.com
 */
@Service
public class XMLDataHandlerServiceImpl implements DataHandlerService {

    private static Logger logger = LogManager.getLogger(XMLDataHandlerServiceImpl.class);
    
    @Autowired
    private ItemsRepositoryService itemsRepositoryService;
    
    @Autowired
    private BoxesRepositoryService boxesRepositoryService;
   
    public boolean createDataStructure(String sourceFilePath) {
        
        boolean result = false;

        try {
            
            logger.info("Method .createDataStructure(...) is starting --->");
            
            File XMLFile = ResourceUtils.getFile(sourceFilePath);
            
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document XMLDocument = documentBuilder.parse(XMLFile);
            XMLDocument.getDocumentElement().normalize();
            Element XMLElement = XMLDocument.getDocumentElement();
                        
            result = parseXML(XMLElement);
            
            result = true;
            
            logger.info("<--- Method .createDataStructure(...) is finished.");

        } catch (FileNotFoundException fileNotFoundException) {

            logger.error("<--- Method .createDataStructure(...) is stopped with below error.");
            logger.error("XML source " + sourceFilePath + " is not found!"
                    + " Please, set correct path to XML source file in args.");
            logger.error(fileNotFoundException);

        } catch (SAXException saxException) {

            logger.error("<--- Method .createDataStructure(...) is stopped with below error.");
            logger.error(saxException);

        } catch (IOException ioException) {

            logger.error("<--- Method .createDataStructure(...) is stopped with below error.");
            logger.error("XML source " + sourceFilePath + " is not read!"
                    + " Please, check XML source file.");
            logger.error(ioException);

        } catch (ParserConfigurationException parserConfigurationException) {

            logger.error("<--- Method .createDataStructure(...) is stopped with below error.");
            logger.error("XML source " + sourceFilePath + " is not parsed!");
            logger.error(parserConfigurationException);

        }
        
        return result;
        
    }
    
    private boolean parseXML(Element XMLElement) {
        
        boolean result = false;
        
        if (XMLElement != null) {
            
            NodeList childNodesList = XMLElement.getChildNodes();
            for (int i = 0; i < childNodesList.getLength(); i++) {
                
                Node node = childNodesList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE
                        && node.getNodeName().equalsIgnoreCase("box")) {
    
                    saveInStorage(node);
                    parseXML((Element) node);
                    
                } else if (node.getNodeType() == Node.ELEMENT_NODE
                        && node.getNodeName().equalsIgnoreCase("item")) {

                    saveInStorage(node);
                    
                }
                
            }
            
        }
        result = true;
        return result;
    }
    
    private void saveInStorage(Node node) {
                
        if (node != null
                && node.getNodeName().equalsIgnoreCase("box")
                && node.getAttributes().getNamedItem("id") != null) {
            
            Box box = new Box();
            box.setId(
                    Integer.parseInt(node.getAttributes().getNamedItem("id").getNodeValue())
            );
            
            Box parentBox = getParent(node);

            if (parentBox != null) {

                box.setParentBox(parentBox);

            }
    
            boxesRepositoryService.saveBox(box);
        }
        
        if (node != null
                && node.getNodeName().equalsIgnoreCase("item")
                && node.getAttributes().getNamedItem("id") != null) {
            
            Item item = new Item();
            item.setId(
                    Integer.parseInt(node.getAttributes().getNamedItem("id").getNodeValue())
            );
            
            Box parentBox = getParent(node);

            if (parentBox != null) {

                item.setParentBox(parentBox);

            }
            
            if (node.getAttributes().getNamedItem("color") != null) {
                item.setColor(
                        node.getAttributes().getNamedItem("color").getNodeValue()
                );
            }
    
            itemsRepositoryService.saveItem(item);
        }  
        
    }
    
    
    private Box getParent(Node node) {
        
        Box parentBox = null;
        
        Node parentNode = node.getParentNode();
        if (parentNode != null 
                && parentNode.getNodeType() == Element.ELEMENT_NODE
                && parentNode.getNodeName().equalsIgnoreCase("box")
                && parentNode.getAttributes().getNamedItem("id") != null) {
            
            parentBox = boxesRepositoryService.findById(
                    Integer.parseInt(parentNode.getAttributes().getNamedItem("id").getNodeValue())
            );
            
        }        
        
        return parentBox;
        
    }
        
}
