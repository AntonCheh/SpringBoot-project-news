public class TopManager implements Employee{

    private final double fixedSalary;
    private final double bonus;
    private final double companyOverPay;  // доход компании сверх установленной прибыли

    public TopManager(double companyOverPay, double fixedSalary) {
        this.fixedSalary = fixedSalary;
        this.bonus = companyOverPay > 10_000_000 ? fixedSalary * 1.5 : 0;
        // Бонус 150% при доходе компании более 10 млн рублей
        this.companyOverPay = companyOverPay;
    }

    @Override
    public double getMonthSalary() {
        return fixedSalary + bonus;
    }
}
