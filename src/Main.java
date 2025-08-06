public class Main {
    public static void main(String[] args) {
        Client client = new Client(
                "user123",
                "securePass!",
                "שירה כהן",
                "987654321",
                "הרצליה",
                "0529876543",
                "נמוך",
                false
        );

        InvestmentAdvisor equityAdvisor = new InvestmentAdvisor(
                "adv001",
                "passEquity",
                "אייל לוי",
                "מניות",
                true,
                "בכיר",
                "פעיל"
        );

        InvestmentAdvisor bondAdvisor = new InvestmentAdvisor(
                "adv002",
                "passBond",
                "נועה ברק",
                "אג''ח",
        true,
                "מתחיל",
                "פעיל");

        InvestmentAdvisor[] advisors = {equityAdvisor, bondAdvisor};
        SystemHistory history = new SystemHistory();
        Commission commission = new Commission(0.015);
        Action action = new Action(
                "רכישה",
                client,
                20000,
                "מניות",
                history
        );

        action.execute(advisors, commission);
        System.out.println("\n--- היסטוריית פעולות ---");
        history.printLogs();
    }
}

