package adapter.shop;

import java.util.HashMap;
import java.util.TreeMap;
import javax.xml.bind.annotation.adapters.XmlAdapter;


/*
 *  PurchaseList - ValueType
 *  HashMap - BoundType
 */
public class AdapterPurchaseListToHashMap extends XmlAdapter<PurchaseList, HashMap<Integer, String>> {

    public AdapterPurchaseListToHashMap() {
    }

    // Convert a value type to a bound type.
    // read xml content and put into Java class.
    @Override
    public HashMap<Integer, String> unmarshal(PurchaseList v) {
        HashMap<Integer, String> aHashMap = new HashMap<Integer, String>();
        int cnt = v.entry.size();

        for (int i = 0; i < cnt; i++) {
            PartEntry pe = v.entry.get(i);
            aHashMap.put(pe.key, pe.value);
        }

        return aHashMap;
    }

    // Convert a bound type to a value type.
    // write Java content into class that generates desired XML 
    @Override
    public PurchaseList marshal(HashMap<Integer, String> v) {
        PurchaseList pList = new PurchaseList();

        // For QA consistency order the output. 
        TreeMap<Integer, String> tMap = new TreeMap<Integer, String>(v);

        for (Integer key : tMap.keySet()) {
            pList.entry.add(
                    new PartEntry(
                    key,
                    tMap.get(key)));
        }

        return pList;
    }
}
