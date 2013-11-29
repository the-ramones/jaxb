package schematype;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import schematype.address.TrackingOrder;

public class SchemaType {

    public static void main(String[] args) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(TrackingOrder.class);
        Unmarshaller u = jc.createUnmarshaller();
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        try {
            TrackingOrder tOrder = (TrackingOrder) u.unmarshal(
                    new File("src/schematype/trackingData.xml"));
            m.marshal(tOrder, System.out);
        } catch (javax.xml.bind.UnmarshalException e) {
            System.out.println("Main: " + e);
        }
    }
}
