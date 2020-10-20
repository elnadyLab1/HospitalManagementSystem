package Entity;

public class PatientEntity extends WorkersEntity {
    private String address;
    private String email;
    private int payAmount;
    private String payMethodCode;

    public PatientEntity() {
    }

    public PatientEntity(int id, String name, String surname, String phoneNumber,String password, boolean status, String address, String email, int payAmount,String payMethodCode) {
        super(id, name, surname, phoneNumber,password, status);
        this.address = address;
        this.email = email;
        this.payAmount = payAmount;
        this.payMethodCode = payMethodCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(int payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayMethodCode() {
        return payMethodCode;
    }

    public void setPayMethodCode(String payMethodCode) {
        this.payMethodCode = payMethodCode;
    }

    @Override
    String getPersonType() {
        return "Patient";
    }
}
