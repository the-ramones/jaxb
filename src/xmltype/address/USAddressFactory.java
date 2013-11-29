package xmltype.address;

public class USAddressFactory {
    public static USAddress getUSAddress() {
        return new USAddress(
                "Mark Baker",
                "23 Elm St",
                "Dayton",
                "OH",
                90952);
    }
}
