package Users;

public class Homeowner extends User {
    //unique attribute for homeowner usertype
    private String address;

    //constructor, calls parent class via super and defines unique attribute
    public Homeowner(String name, String email, String telNo, String address) {
        super(name, email, telNo);
        this.address = address;
    }

    //getters
    public String getAddress() {
        return address;
    }

    @Override
    public String getUserType() {
        return "Homeowner";
    }
}