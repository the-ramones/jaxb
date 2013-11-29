package attribute.address;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAttribute;


@XmlRootElement(name = "purchaseOrder")
@XmlType(name = "PurchaseOrderType")
public class PurchaseOrderType {
    public USAddress billTo;
    public USAddress shipTo;
    @XmlAttribute
    public CreditCardVendor cCardVendor;

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Ship To: ");
        s.append(shipTo.toString())
         .append('\n');
        s.append("Bill To: ");

        return s.toString();
    }
}
