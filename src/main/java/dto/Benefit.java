package dto;

public class Benefit {
    private final String name;
    private final double amount;
    public Benefit(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

}
