import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Company {

    protected List<Employee> employees = new ArrayList<>();
    private double salary;

    public void hire(Employee employee) {
        employees.add(employee);

    }
    public void hireAll(Collection<Employee> employees) {
        this.employees.addAll(employees);
    }
    public void fire(Employee employee) {
        employees.remove(employee);
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public List<Employee> getTopSalaryStaff(int count) {
        List<Employee> topSalaryStaff = new ArrayList<>(employees); // Создаем копию списка сотрудников
        topSalaryStaff.sort((e1, e2) -> Double.compare(e2.getMonthSalary(), e1.getMonthSalary())); // Сортируем список
        return topSalaryStaff.subList(0, Math.min(count, topSalaryStaff.size())); // Получаем подсписок
    }
    public List<Employee> getLowestSalaryStaff(int count) {
        List<Employee> lowestSalaryStaff = new ArrayList<>(employees); // Создаем копию списка сотрудников
        lowestSalaryStaff.sort(Comparator.comparingDouble(Employee::getMonthSalary)); // Сортируем список
        return lowestSalaryStaff.subList(0, Math.min(count, lowestSalaryStaff.size())); // Получаем подсписок
    }
}
