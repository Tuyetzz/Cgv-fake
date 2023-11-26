public class Employee {
    private String username;
    private String password;
    private String selectedRole;

    Employee() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getSelectedRole() {
        return selectedRole;
    }
    public void setSelectedRole(String selectedRole) {
        this.selectedRole = selectedRole;
    }
}
