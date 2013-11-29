package adapter.shop;

import java.util.HashMap;
import java.util.TreeMap;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlType(name = "KitchenWorldBasketType")
public class KitchenWorldBasket {

    @XmlJavaTypeAdapter(AdapterPurchaseListToHashMap.class)
    HashMap<Integer, String> basket = new HashMap<Integer, String>();

    public KitchenWorldBasket() {
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("KitchenWorldBasket:\n");

        // For QA consistency order the output. 
        TreeMap<Integer, String> tMap = new TreeMap<Integer, String>(basket);

        for (Integer key : tMap.keySet()) {
            buf.append(
                    "key: " + key.toString() + "\tvalue: " + tMap.get(key)
                    + "\n");
        }

        return buf.toString();
    }
}
