package schematype.address;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;

@XmlRootElement
@XmlType(name = "TrackingOrderType")
public class TrackingOrder {

    String trackingDuration;
    @XmlElement
    XMLGregorianCalendar deliveryDate;
    @XmlElement
    XMLGregorianCalendar orderDate;
    @XmlSchemaType(name = "date")
    XMLGregorianCalendar shipDate;

    @XmlSchemaType(name = "duration")
    public String getTrackingDuration() {
        return trackingDuration;
    }

    public void setTrackingDuration(String d) {
        trackingDuration = d;
    }
}
