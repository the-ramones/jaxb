package adapter.shop;

import java.util.List;
import java.util.Vector;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(name = "PurchaseListType")
public class PurchaseList {
    //- this must be a public field for the adapter to function
    //- When it is public the generated xml uses the variable name
    //- as the element tag.
    //- If the entry is not public the generic identifier is used
    //- as the element tag.  Settter/getter methods would be
    //- needed.

    public List<PartEntry> entry;

    public PurchaseList() {
        entry = new Vector<PartEntry>();
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        int cnt = entry.size();

        for (int i = 0; i < cnt; i++) {
            buf.append(entry.get(i).toString());
            buf.append("\n");
        }

        return buf.toString();
    }
}
