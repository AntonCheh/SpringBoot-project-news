import java.util.Collection;
import java.util.List;

public abstract class Company {

    protected int salaryFix = 100_000;
    protected int bonus = 0;

    protected  void hire (Employee employee){

    }

    protected void hireAll (Collection <Employee> employes) {
    }

    protected void fire (Employee employee) {
    }

    protected void getIncome () {
    }

   public List<Employee> getTopSalaryStaff(int count) {
        return null;
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        return null;
    }
}
