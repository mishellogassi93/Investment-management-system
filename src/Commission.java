public class Commission {
    private double rate;

    public Commission(double rate) {
        this.rate = rate;
    }

    public double calculate(double amount) {
        return amount * rate;
    }
}
