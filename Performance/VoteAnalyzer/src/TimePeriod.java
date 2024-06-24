import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class TimePeriod implements Comparable<TimePeriod> {

    private long from;
    private long to;
    private LocalDate date; // Сохраняем дату отдельно для быстрого сравнения

    /**
     * Time period within one day
     *
     * @param from
     * @param to
     */
    public TimePeriod(long from, long to) {
        this(new Date(from), new Date(to));
    }

    public TimePeriod(Date from, Date to) {
        this.from = from.getTime();
        this.to = to.getTime();
        LocalDate fromDate = from.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate toDate = to.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (!fromDate.equals(toDate)) {
            throw new IllegalArgumentException("Dates 'from' and 'to' must be within ONE day!");
        }
        this.date = fromDate; // Инициализируем поле date
    }

    public void appendTime(Date visitTime) {
        LocalDate visitDate = visitTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (!this.date.equals(visitDate)) {
            throw new IllegalArgumentException("Visit time must be within the same day as the current TimePeriod!");
        }
        long visitTimeTs = visitTime.getTime();
        if (visitTimeTs < from) {
            from = visitTimeTs;
        }
        if (visitTimeTs > to) {
            to = visitTimeTs;
        }
    }

    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String from = dateFormat.format(this.from);
        String to = timeFormat.format(this.to);
        StringBuilder sb = new StringBuilder();
        sb.append(from).append("-").append(to);
        return sb.toString();
    }

    @Override
    public int compareTo(TimePeriod period) {
        return Long.compare(this.from, period.from);
    }
}




