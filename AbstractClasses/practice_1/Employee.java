public interface Employee {

    default double getMonthSalary(int moneyOfCompany) {
        moneyOfCompany = (int) Math.random();
        return moneyOfCompany;
    }

    double getMonthSalary();
}

