import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class CustomerStorage {
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) throws IllegalArgumentException {
        final int EXPECTED_COMPONENTS = 4;
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String[] components = data.split("\\s+");
//            String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
//            storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));

        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        String email = components[INDEX_EMAIL];
        String phone = components[INDEX_PHONE];

        if (components.length != EXPECTED_COMPONENTS) {
            throw new IllegalArgumentException("Количество элементов более 4");
        }
        if (!isValidPhoneNumber(phone)) {
            throw new IllegalArgumentException("Неверный формат номера телефона");
        }
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Неверный формат электронной почты");
        }
        // storage.put(name, new Customer(name, phone, components[INDEX_EMAIL]));
        storage.put(name, new Customer(name, phone, email));
    }
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\+7\\d{10}");
    }
    private boolean isValidEmail(String email) {
        Pattern emailPattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$");
        return emailPattern.matcher(email).matches();
    }
    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }
    public void removeCustomer(String name) {
        storage.remove(name);
    }
    public Customer getCustomer(String name) {
        return storage.get(name);
    }
    public int getCount() {
        return storage.size();
    }
}




