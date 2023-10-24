import java.util.Date;

public class Customer {
    private int code;
    private String  firstName, lastName, type;
    private Date birth;
    Customer() {

    }

    //ten

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //code
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    //birth
    public Date getBirth() {
        return birth;
    }
    public void setBirth(Date birth) {
        this.birth = birth;
    }
    //type

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
