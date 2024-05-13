package example;
public class Account {
  private long money;
  private String accNumber;
  private boolean blocked;

  public Account(long initialMoney, String accountNum) {
    this.money = initialMoney;
    this.accNumber = accountNum;
  }

  public long getMoney() {
    return money;
  }

  public void setMoney(long money) {
    this.money = money;
  }

  public String getAccNumber() {
    return accNumber;
  }

  public void setAccNumber(String accNumber) {
    this.accNumber = accNumber;
  }

  public boolean isBlocked() {
    return blocked;
  }

  public void setBlocked(boolean blocked) {
    this.blocked = blocked;
  }
}



