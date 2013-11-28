package modify.marshal;

import generated.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class ModifyMarshal {
    // This sample application demonstrates how to modify a java content
    // tree and marshal it back to a xml data

    public static void main(String[] args) {
        try {
            // create a JAXBContext capable of handling classes generated into
            // the primer.po package
            JAXBContext jc = JAXBContext.newInstance("generated");

            // create an Unmarshaller
            Unmarshaller u = jc.createUnmarshaller();

            // unmarshal a po instance document into a tree of Java content
            // objects composed of classes from the primer.po package.
            JAXBElement poe = (JAXBElement) u.unmarshal(
                    new FileInputStream("src/modify/marshal/po.xml"));
            PurchaseOrderType po = (PurchaseOrderType) poe.getValue();

            // change the billto address
            USAddress address = po.getBillTo();
            address.setName("John Bob");
            address.setStreet("242 Main Street");
            address.setCity("Beverly Hills");
            address.setState("CA");
            address.setZip(new BigDecimal("90210"));
            
            // create a Marshaller and marshal to a file
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(poe, System.out);
        } catch (JAXBException je) {
            je.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
