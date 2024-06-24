import java.util.Date;
import java.util.TreeSet;

public class WorkTime {

    private TreeSet<TimePeriod> periods;

    /**
     * Set of TimePeriod objects
     */
    public WorkTime() {

        periods = new TreeSet<>();
    }

    public void addVisitTime(long visitTime) {
        TimePeriod newPeriod = new TimePeriod(visitTime, visitTime);
        TimePeriod existingPeriod = periods.floor(newPeriod);

        if (existingPeriod != null && existingPeriod.compareTo(newPeriod) == 0) {
            existingPeriod.appendTime(new Date(visitTime));
        } else {
            periods.add(newPeriod);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (TimePeriod period : periods) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(period);
        }
        return sb.toString();
    }
}
