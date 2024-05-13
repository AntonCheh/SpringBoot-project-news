package example;

import java.util.HashMap;
import java.util.Random;

public class Bank {
  private HashMap<String, Account> accounts;
  private final Random random = new Random();

  public Bank() {
    this.accounts = new HashMap<>();
  }

  public synchronized boolean isFraud(String fromAccount, String toAccountNum, long amount) throws InterruptedException {
    Thread.sleep(1000); // Имитация времени, затраченного на проверку
    return random.nextBoolean(); // Метод возвращает случайное значение для имитации результата проверки
  }

  /**
   * TODO: реализовать метод. Метод переводит деньги между счетами.
   * Если сумма транзакции > 50000, то после совершения транзакции,
   * она отправляется на проверку Службе Безопасности - вызывается
   * метод isFraud. Если возвращается true, то делается блокировка
   * счетов (как - на ваше усмотрение)
   */
  public void transfer(String fromAccount, String toAccount, long amount) {
    Account from = accounts.get(fromAccount);
    Account to = accounts.get(toAccount);

    if (from == null || to == null) {
      throw new IllegalArgumentException("One or both accounts do not exist");
    }

    synchronized (from) {
      synchronized (to) {
        if (from.getMoney() < amount) {
          throw new IllegalArgumentException("Not enough money on the account");
        }

        from.setMoney(from.getMoney() - amount);
        to.setMoney(to.getMoney() + amount);

        if (amount > 50000) {
          try {
            if (isFraud(fromAccount, toAccount, amount)) {
              // Если обнаружено мошенничество, блокируем оба счета
              from.setBlocked(true);
              to.setBlocked(true);
            }
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }

  /**
   * Метод возвращает остаток на счете.
   */
  public long getBalance(String accountNum) {
    Account account = accounts.get(accountNum);
    if (account == null) {
      throw new IllegalArgumentException("Account does not exist");
    }
    return account.getMoney();
  }

  public void addAccount(long initialMoney, String accountNum) {
    accounts.put(accountNum, new Account(initialMoney, accountNum));
  }

  private boolean isFraudCheckResult;

  public void setIsFraudCheckResult(boolean result) {
    this.isFraudCheckResult = result;
  }
}

