import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Airport;



import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {


    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
       Date timeNow = new Date();
        Date twoHoursLate = calculateTwoHoursAfter (timeNow);

        List<Flight> departingFlights = airport.getAllAircrafts().stream()
                .filter(flight -> flight.getType() == Flight.Type.DEPARTURE).
                filter(flight -> flight.getDate().before(twoHoursLate))
                .collect(Collectors.toList());


        //TODO Метод должден вернуть список рейсов вылетающих в ближайшие два часа.
        return departingFlights;
    }

    private static Date calculateTwoHoursAfter(Date date) {
        Calendar search = Calendar.getInstance();
        search.setTime(date);

        search.add(Calendar.HOUR_OF_DAY, 2);

        Date twoHoursFromNow = search.getTime ();

        return twoHoursFromNow;
        }
    }
