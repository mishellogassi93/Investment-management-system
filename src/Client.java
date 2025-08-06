public class Client extends User {
    private String fullName;
    private String idNumber;
    private String address;
    private String phone;
    private String riskProfile;
    private boolean institutional;

    public Client(String username, String password, String fullName, String idNumber, String address,
                  String phone, String riskProfile, boolean institutional) {
        super(username, password);
        this.fullName = fullName;
        this.idNumber = idNumber;
        this.address = address;
        this.phone = phone;
        this.riskProfile = riskProfile;
        this.institutional = institutional;
    }

    public String getRiskProfile() {
        return riskProfile;
    }

    public boolean isInstitutional() {
        return institutional;
    }

    public String getFullName() {
        return fullName;
    }
}