package pullparser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import javax.xml.stream.*;
import static javax.xml.stream.XMLStreamConstants.*;


/*
 * Use is subject to the license terms.
 */

public class PullParser {
    public static void main(String[] args) throws Exception {
        String nameToLookFor = "Jane Smith";

        JAXBContext jaxbContext = JAXBContext.newInstance("pullparser");
        Unmarshaller um = jaxbContext.createUnmarshaller();

        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(
                    new FileReader("src/pullparser/contact.xml"));

        // move to the root element and check its name.
        xmlr.nextTag();
        xmlr.require(START_ELEMENT, null, "addressBook");

        xmlr.nextTag(); // move to the first <contact> element.

        while (xmlr.getEventType() == START_ELEMENT) {
            // unmarshall one <contact> element into a JAXB Contact object
            xmlr.require(START_ELEMENT, null, "contact");

            Contact contact = (Contact) um.unmarshal(xmlr);

            if (contact.getName()
                           .equals(nameToLookFor)) {
                // we found what we wanted to find. show it and quit now.
                System.out.println(
                        "The email address for " + nameToLookFor + " is "
                        + contact.getEmail() + ".");

                return;
            }

            if (xmlr.getEventType() == CHARACTERS) {
                xmlr.next(); // skip the whitespace between <contact>s.
            }
        }

        System.out.println("Unable to find " + nameToLookFor);
    }
}
