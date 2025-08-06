import java.util.Date;

public class Action {
    private String type;
    private String status;
    private Date date;
    private boolean requiresApproval;
    private InvestmentAdvisor advisor;
    private SystemHistory history;
    private Client client;
    private double amount;
    private String assetType;
    private int negativeReturnMonths;

    public Action(String type, Client client, double amount, String assetType, SystemHistory history) {
        this.type = type;
        this.client = client;
        this.amount = amount;
        this.assetType = assetType;
        this.date = new Date();
        this.status = "ממתינה";
        this.requiresApproval = checkIfNeedsApproval(client, assetType, type);
        this.history = history;
        this.negativeReturnMonths = 0;
    }

    private boolean checkIfNeedsApproval(Client client, String assetType, String actionType) {
        return actionType.equalsIgnoreCase("רכישה") &&
                assetType.equalsIgnoreCase("מניות") &&
                client.getRiskProfile().equalsIgnoreCase("נמוך");
    }

    public void execute(InvestmentAdvisor[] advisors, Commission commission) {
        System.out.println("--- התחלת ביצוע פעולה: " + type + " ---");

        if (!regulationCheck()) {
            status = "בבדיקה";
            history.log("חריגה רגולטורית - דרושה בדיקה נוספת ליועץ מוסמך");
            return;
        }

        if (requiresApproval) {
            advisor = findMatchingAdvisor(advisors);
            if (advisor == null || !advisor.approveRequest()) {
                status = "נדחתה";
                history.log("דחייה: הפעולה לא אושרה על ידי יועץ מוסמך");
                return;
            }

            if (!advisor.getSpecialization().equalsIgnoreCase(assetType)) {
                status = "בבדיקה";
                history.log("חריגה: היועץ אינו מוסמך להשקעות מסוג " + assetType);
                return;
            }
        }

        if (negativeReturnMonths >= 3) {
            status = "בבדיקה";
            history.log("תשואה שלילית רציפה - פעולה מועברת לבדיקה מחודשת");
            return;
        }
        status = "מאושרת";
        double fee = commission.calculate(amount);
        history.log("הפעולה אושרה: סוג: " + type + ", סכום: " + amount + ", עמלה: " + fee);
        finalizeAction();
    }

    private boolean regulationCheck() {
        if (client.isInstitutional() && assetType.equalsIgnoreCase("מניות")) {
            return false;
        }
        return true;
    }

    private InvestmentAdvisor findMatchingAdvisor(InvestmentAdvisor[] advisors) {
        for (InvestmentAdvisor a : advisors) {
            if (a.getSpecialization().equalsIgnoreCase(assetType) && a.isLicenseValid() && a.getStatus().equals("פעיל")) {
                System.out.println("יועץ מוקצה: " + a.getFullName());
                return a;
            }
        }
        return null;
    }

    private void finalizeAction() {
        status = "בוצעה בהצלחה";
        history.log("סטטוס עודכן ל: " + status);
        System.out.println("הפעולה בוצעה בהצלחה עבור הלקוח: " + client.getFullName());
    }

    public void incrementNegativeReturnMonths() {
        negativeReturnMonths++;
    }
}