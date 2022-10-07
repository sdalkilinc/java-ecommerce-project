import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String userName;
    private String email;
    private List<Address> address;

    public Customer(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public Customer(String userName, String email, List<Address> address) {
        this.userName = userName;
        this.email = email;
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public List<Address> getAddress() {
        return address;
    }
}
