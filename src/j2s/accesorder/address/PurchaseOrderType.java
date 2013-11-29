package j2s.accesorder.address;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "purchaseOrder")
@XmlType(name = "PurchaseOrderType")
public class PurchaseOrderType {
    public USAddress billTo;
    public USAddress shipTo;

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
