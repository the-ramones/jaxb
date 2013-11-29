package j2s.marshal;

import j2s.marshal.cardfile.Address;
import j2s.marshal.cardfile.BusinessCard;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Schema;
import org.xml.sax.SAXException;

public class Marshal {

    public static void main(String[] args) throws Exception {
        final File f = new File("src/j2s/marshal/bcard.xml");

        // Illustrate two methods to create JAXBContext for j2s binding.
        // (1) by root classes newInstance(Class ...)
        JAXBContext context1 = JAXBContext.newInstance(BusinessCard.class);

        // (2) by package, requires jaxb.index file in package cardfile.
        //     newInstance(String packageNames)
        JAXBContext context2 = JAXBContext.newInstance("j2s.marshal.cardfile");

        Marshaller m = context1.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(getCard(), System.out);

        // illustrate optional unmarshal validation.
        Marshaller m2 = context1.createMarshaller();
        m2.marshal(getCard(), new FileOutputStream(f));

        Unmarshaller um = context2.createUnmarshaller();
        um.setSchema(getSchema(new File("src/j2s/marshal/bcard.xsd")));

        Object bce = um.unmarshal(f);
        m.marshal(bce, System.out);
    }

    /**
     * returns a JAXP 1.3 schema by parsing the specified resource.
     */
    static Schema getSchema(File schemaResourceName)
            throws SAXException {
        SchemaFactory sf = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);

        try {
            
            return sf.newSchema(schemaResourceName);
        } catch (SAXException se) {
            // this can only happen if there's a deployment error and the resource is missing.
            throw se;
        }
    }

    private static BusinessCard getCard() {
        return new BusinessCard(
                "John Doe",
                "Sr. Widget Designer",
                "Acme, Inc.",
                new Address(
                null,
                "123 Widget Way",
                "Anytown",
                "MA",
                (short) 12345),
                "123.456.7890",
                null,
                "123.456.7891",
                "John.Doe@Acme.ORG");
    }
}
