package unmarshal;

import generated.PurchaseOrderType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author the-ramones
 */
public class CustomValidateUnmarshal {

    public static void main(String[] args) {
        try {
            JAXBContext jc = JAXBContext.newInstance("generated");
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            try {
                Schema schema = sf.newSchema(new File("po.xsd"));
                unmarshaller.setSchema(schema);
                unmarshaller.setEventHandler(new ValidationEventHandler() {

                    @Override
                    public boolean handleEvent(ValidationEvent event) {
                        if (event.getSeverity() != ValidationEvent.WARNING) {
                            ValidationEventLocator locator = event.getLocator();
                            System.out.println("Line:Col[" + locator.getLineNumber() +
                                    " : " + locator.getColumnNumber() +
                                    "] " + event.getMessage());
                        }
                        return true;
                    }
                });
            } catch (SAXException ex) {
                System.err.println("SAXException. " + ex.getMessage());
            }

            JAXBElement poe = (JAXBElement) unmarshaller.unmarshal(new FileInputStream("po.xml"));
            PurchaseOrderType po = (PurchaseOrderType) poe.getValue();

            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(poe, System.out);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CustomValidateUnmarshal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            System.err.println("JAXbException occured. " + ex.getMessage());
        }
    }
}
