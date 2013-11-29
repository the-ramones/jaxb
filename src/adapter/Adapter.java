package adapter;

import adapter.shop.KitchenWorldBasket;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Adapter {

    public static void main(String[] args) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(KitchenWorldBasket.class);
        Unmarshaller u = jc.createUnmarshaller();
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        try {
            KitchenWorldBasket kwBasket = (KitchenWorldBasket) u.unmarshal(
                    new File("src/adapter/shoppingCartData.xml"));

            // Demonstrate adapter's unmarshal integrated data into HashMap properly
            System.out.println(kwBasket.toString());

            // Demonstate adapter's marshal writes the data properly
            m.marshal(kwBasket, System.out);
        } catch (javax.xml.bind.UnmarshalException e) {
            System.out.println("Main: " + e);
        }
    }
}
