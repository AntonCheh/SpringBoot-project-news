import java.util.ArrayList;
import java.util.List;

public class Demonstration {

    public static void main(String[] args) {
       Company company = new Company();

        // Наем сотрудников
        List<Employee> operators = new ArrayList<>();
        for (int i = 0; i < 180; i++) {
            company.hire(new Operator(80000));
        }

        List<Employee> managers = new ArrayList<>();
        for (int i = 0; i < 80; i++) {
            company.hire(new Manager(60000));
        }

        List<Employee> topManagers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            company.hire(new TopManager(11_000_000, 100_000));
        }

        company.hireAll(operators);
        company.hireAll(managers);
        company.hireAll(topManagers);

        // Распечатываем список из 10–15 самых высоких зарплат
        List<Employee> topSalaries = company.getTopSalaryStaff(15);
        int count = 0;
        System.out.println("Список из 10–15 самых высоких зарплат:");
        for (Employee employee : topSalaries) {
            count++;
            System.out.println(String.format(count + ") " + "%.2f руб.", employee.getMonthSalary()));
        }

        // Распечатываем список из 30 самых низких зарплат
        List<Employee> lowestSalaries = company.getLowestSalaryStaff(30);
        int count2 = 0;
        System.out.println("\nСписок из 30 самых низких зарплат:");
        for (Employee employee : lowestSalaries) {
            count2++;
            System.out.println(String.format(count2 + ") " +"%.2f руб.", employee.getMonthSalary()));
        }

        // Увольнение 50% сотрудников
        int numToFire = company.getSalary() < 10_000_000 ? (int) (company.employees.size() * 0.5) : 0;
        List<Employee> employeesToFire = company.getLowestSalaryStaff(numToFire);
        for (Employee employee : employeesToFire) {
            company.fire(employee);
        }

        // Распечатываем список из 10–15 самых высоких зарплат после увольнения
        topSalaries = company.getTopSalaryStaff(15);
        int count3 = 0;
        System.out.println("\nСписок из 10–15 самых высоких зарплат после увольнения:");
        for (Employee employee : topSalaries) {
            count3++;
            System.out.println(String.format(count3 + ") " +"%.2f руб.", employee.getMonthSalary()));
        }

        // Распечатываем список из 30 самых низких зарплат после увольнения
        lowestSalaries = company.getLowestSalaryStaff(30);
        int count4 = 0;
        System.out.println("\nСписок из 30 самых низких зарплат после увольнения:");
        for (Employee employee : lowestSalaries) {
            count4++;
            System.out.println(String.format(count4 + ") " + "%.2f руб.", employee.getMonthSalary()));
        }
    }
}
