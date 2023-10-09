public class Manager implements Employee {
    private final double fixedSalary;
    private final double bonus;

    public Manager(double fixedSalary) {
        this.fixedSalary = fixedSalary;
        this.bonus = Math.random() * (140000 - 115000) + 115000;
        // Бонус от 115000 до 140000 рублей
    }
    @Override
    public double getMonthSalary() {
        return fixedSalary + bonus * 0.05;
    }
}
