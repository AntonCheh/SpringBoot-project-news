import example.Bank;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankTest {

  @Test
  public void testBankOperations() throws InterruptedException {
    Bank bank = new Bank();

    bank.addAccount(100000, "001");
    bank.addAccount(50000, "002");
    bank.addAccount(20000, "003");

    assertEquals(100000, bank.getBalance("001"));
    assertEquals(50000, bank.getBalance("002"));
    assertEquals(20000, bank.getBalance("003"));

    // Переводим деньги со счета 001 на счет 002
    bank.transfer("001", "002", 50000);

    // Проверяем балансы после перевода
    assertEquals(50000, bank.getBalance("001"));
    assertEquals(100000, bank.getBalance("002"));

    // Запускаем транзакцию между счетами 001 и 002 с суммой > 50000 (требует проверки)
    Thread t = new Thread(() -> {
      bank.transfer("001", "002", 10000);
    });
    t.start();

    // Ждем завершения транзакции
    t.join();

    // Проверяем, что счета не изменились
    assertEquals(50000, bank.getBalance("001"));
    assertEquals(100000, bank.getBalance("002"));

    // Теперь проверим, что блокировка работает, если транзакция мошенническая
    // Устанавливаем фиктивное значение проверки мошенничества в true
    bank.setIsFraudCheckResult(true);

    // Запускаем транзакцию, которая должна быть заблокирована
    Thread t2 = new Thread(() -> {
      bank.transfer("001", "002", 60000);
    });
    t2.start();

    // Ждем завершения второй транзакции
    t2.join();

    // Проверяем, что счета не изменились после мошеннической транзакции
    assertEquals(50000, bank.getBalance("001"));
    assertEquals(100000, bank.getBalance("002"));
  }

  @Test
  public void testGetBalanceWithInvalidAccountNumber() {
    Bank bank = new Bank();

    bank.addAccount(100000, "001");
    bank.addAccount(50000, "002");

    // Проверяем, что метод getBalance() выбрасывает исключение при запросе баланса для несуществующего счета
    assertThrows(IllegalArgumentException.class, () -> bank.getBalance("003"));
  }
}
