package datatype.converter;

import datatype.Items;
import datatype.POType;
import datatype.USAddress;
import datatype.USState;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ListIterator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author the-ramones
 */
public class CustomDataTypeConverter {

    public static void main(String[] args) {
        try {
            JAXBContext jc = JAXBContext.newInstance("datatype");
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            JAXBElement poe = (JAXBElement) unmarshaller.unmarshal(
                    new FileInputStream("src/datatype/converter/po.xml"));

            POType po = (POType) poe.getValue();

            USAddress address = po.getBillTo();
            address.setToName("John Bob");
            address.setStreet("242 Main Street");
            address.setCity("Beverly Hills");
            address.setState(USState.CA);
            address.setZipCode(90210);

            USState purchaseState = address.getState();
            ListIterator i = po.getItems().getItem().listIterator();

            while (i.hasNext()) {
                Items.Item item = (Items.Item) i.next();
                // update to 20% off discount
                item.setPrice(item.getPrice().multiply(new BigDecimal("0.80")));

                if (purchaseState.equals(USState.MA)) {
                    item.setPrice(item.getPrice().multiply(new BigDecimal("1.05")));
                } else if (purchaseState.equals(USState.CA)) {
                    item.setPrice(item.getPrice().multiply(new BigDecimal("1.06")));
                }
                // rounding down
                item.setPrice(item.getPrice().setScale(2, RoundingMode.DOWN));
            }

            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(poe, System.out);
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (JAXBException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
