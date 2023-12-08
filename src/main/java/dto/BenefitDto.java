package dto;

public class BenefitDto {
    private final String name;
    private final double amount;
    public BenefitDto(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return name + ": " + amount;
    }
}
