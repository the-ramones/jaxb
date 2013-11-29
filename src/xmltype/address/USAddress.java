package xmltype.address;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "uSAddress")
@XmlType(name = "USAddressType", factoryClass = xmltype.address.USAddressFactory.class, factoryMethod = "getUSAddress")
public class USAddress {

    private String city;
    private String name;
    private String state;
    private String street;
    private USAddress _instance = null;
    private int zip;

    public USAddress(
            String name,
            String street,
            String city,
            String state,
            int zip) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        if (name != null) {
            s.append(name)
                    .append('\n');
        }

        s.append(street)
                .append('\n')
                .append(city)
                .append(", ")
                .append(state)
                .append(" ")
                .append(zip);

        return s.toString();
    }
}
