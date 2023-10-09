public class TopManager extends Company implements Employee{

    @Override
    public double getMonthSalary() {
        return Employee.super.getMonthSalary(salaryFix);
    }


}
