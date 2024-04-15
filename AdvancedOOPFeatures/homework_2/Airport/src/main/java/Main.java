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

        Airport airport = Airport.getInstance();
        List<Flight> departingFlights = findPlanesLeavingInTheNextTwoHours(airport);

        if (departingFlights.isEmpty()) {
            System.out.println("Нет рейсов, вылетающих в ближайшие два часа.");
        } else {
            System.out.println("Рейсы, вылетающие в ближайшие два часа:");
            for (Flight flight : departingFlights) {
                System.out.println("Тип: " + flight.getType());
                System.out.println("Код: " + flight.getCode());
                System.out.println("Дата: " + flight.getDate());
                System.out.println("Самолет: " + flight.getAircraft());
                System.out.println();
            }
        }

    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
       Date timeNow = new Date();
        Date twoHoursLate = calculateTwoHoursAfter (timeNow);

        List<Flight> departingFlights = airport.getTerminals().stream()
                .flatMap(terminal -> terminal.getFlights().stream()) // Получаем все рейсы из всех терминалов
                .filter(flight -> flight.getType() == Flight.Type.DEPARTURE) // Фильтруем только вылеты
                .filter(flight -> flight.getDate().after(timeNow) && flight.getDate().before(twoHoursLate)).collect(Collectors.toList());


        return departingFlights;
    }

    private static Date calculateTwoHoursAfter(Date date) {
        Calendar search = Calendar.getInstance();
        search.setTime(date);

        search.add(Calendar.HOUR_OF_DAY, 2);

        return search.getTime ();
        }
    }
