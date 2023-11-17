import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {

    static Logger logger;
    private static final String ADD_COMMAND = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static final String COMMAND_EXAMPLES = "\t" + ADD_COMMAND + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static final String COMMAND_ERROR = "Wrong command! Available command examples: \n" +
            COMMAND_EXAMPLES;
    private static final String helpText = "Command examples:\n" + COMMAND_EXAMPLES;

    static final Logger queriesLogger = LogManager.getLogger("queriesLogger");
    static final Logger errorsLogger = LogManager.getLogger("errorsLogger");

    public static void main(String[] args) {
         logger = LogManager.getRootLogger();
//        logger.log(Level.WARN, "Сообщение об ошибке");
//        logger.info("you made a mistake :");

        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();

        while (true) {
            String command = scanner.nextLine();
            String[] tokens = command.split("\\s+", 2);

            try {
                if (tokens[0].equalsIgnoreCase("add")) {
                    executor.addCustomer(tokens[1]);
                    queriesLogger.info("Запрос к приложению: " + tokens[0]);

                } else if (tokens[0].equalsIgnoreCase("list")) {
                    executor.listCustomers();
                } else if (tokens[0].equalsIgnoreCase("remove")) {
                    executor.removeCustomer(tokens[1]);
                } else if (tokens[0].equalsIgnoreCase("count")) {
                    System.out.println("There are " + executor.getCount() + " customers");
                } else if (tokens[0].equalsIgnoreCase("help")) {
                    System.out.println(helpText);
                } else {
                    System.out.println(COMMAND_ERROR);
                }
            }
            catch (ArrayIndexOutOfBoundsException exception) {
                //String errorMessage = exception.getMessage();
                logger.log(Level.ERROR, "Ошибка выполнения команды: " + exception.getMessage(), exception);
                System.err.println("Не введена команда");

            }
             catch (IllegalArgumentException e) {
                //logger.log(Level.ERROR, "Ошибка выполнения команды: " + e.getMessage(), e);
                // errorsLogger.error("Ошибка выполнения команды: " + e.getMessage(), e);
                 String errorMessage = "Ошибка при добавлении клиента: " + e.getMessage();
                 errorsLogger.error(errorMessage, e);
                 System.err.println(errorMessage);
            }
        }
    }
}

