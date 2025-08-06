public class InvestmentAdvisor extends User {
    private String fullName;
    private String specialization;
    private boolean licenseValid;
    private String certificationLevel;
    private String status;

    public InvestmentAdvisor(String username, String password, String fullName, String specialization,
                             boolean licenseValid, String certificationLevel, String status) {
        super(username, password);
        this.fullName = fullName;
        this.specialization = specialization;
        this.licenseValid = licenseValid;
        this.certificationLevel = certificationLevel;
        this.status = status;
    }

    public boolean approveRequest() {
        return licenseValid && status.equals("פעיל");
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getFullName() {
        return fullName;
    }

    public boolean isLicenseValid() {
        return licenseValid;
    }

    public String getStatus() {
        return status;
    }
}