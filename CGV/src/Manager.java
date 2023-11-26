public class Manager extends Employee {
    private boolean datve;

    Manager() {

    }

    Manager(boolean datve) {
        this.datve = datve;
    }

    public boolean hasDatveAccess() {
        return datve;
    }

    public void setDatveAccess(boolean datve) {
        this.datve = datve;
    }

    // You can add other methods specific to the Manager class here
}
