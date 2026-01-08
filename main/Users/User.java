package Users;

// parent class for all user types, used for conformity
public abstract class User {
    private String name;
    private String email;
    private String telNo;

    //constructor
    public User(String name, String email, String telNo) {
        this.name = name;
        this.email = email;
        this.telNo = telNo;
    }

    //getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getTelNo() {
        return telNo;
    }

    public abstract String getUserType();
}

