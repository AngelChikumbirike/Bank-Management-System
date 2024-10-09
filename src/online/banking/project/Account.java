package online.banking.project;
//import java.util.ArrayList;//
import java.util.Date;
import javax.swing.JOptionPane;

/*public class Account {
    private double balance;
    private double accNum;
    
     
    public Account(double bal, double number){
        this.balance=bal;
        this.accNum=number;
        
    }
    public void addBalance(){
    String str= JOptionPane.showInputDialog("Do you want to make a deposit. Yes OR No?");
    str=str.toLowerCase();
    if (str.equals("yes")){
        String add= JOptionPane.showInputDialog("How much would you like to deposit today?");
        double added= Double.parseDouble(add);
        balance += added;
        
        
        
                
        
    }
    }
    public double getBalance(){
        return  this.balance;
        
                
    }
    @Override
public String toString(){
String Str = "The account balance for" + this.accNum + " is " + this.balance ;
return Str;
}
}*/
import online.banking.project.exceptions.InvalidOperationException;

public class Account {
  protected static double INTEREST_RATE;

  private final String accountNumber;
  protected double balance;
  private final Date dateOpened;
  private final String customerId;

  public Account(String accountNumber, String customerId, double balance) {
    this.accountNumber = accountNumber;
    this.customerId = customerId;
    this.balance = balance;
    this.dateOpened = new Date();
  }

  public Account(String accountNumber, String customerId) {
    this(accountNumber, customerId, 0);
  }

  public double getBalance() {
    return this.balance;
  }

  public String getAccountNumber() {
    return this.accountNumber;
  }

  public double deposit(double amount) {
    this.balance += amount;
    return balance;
  }

  public double withdraw(double amount, double minimumBalance) throws InvalidOperationException {
    double newBalance = balance - amount;
    if (newBalance < minimumBalance) {
      throw new InvalidOperationException("You have insufficient funds to make this transaction.");
    }
    this.balance = newBalance;
    return newBalance;
  }

  public double withdraw(double amount) throws InvalidOperationException {
    return withdraw(amount, 0);
  }

  public static double getInterestRate() {
    return INTEREST_RATE;
  }

  public static void setInterestRate(double newRate) {
    INTEREST_RATE = newRate;
    System.out.println(INTEREST_RATE);
  }

  public double addInterest() {
    balance += balance * INTEREST_RATE / 100;
    return balance;
  }

  @Override
  public String toString() {
    return String.format(
        "Account with account number: %s opened on: %s for customer with id: %s has a balance of: $%.2f",
        this.accountNumber, this.dateOpened.toString(), customerId, this.balance);
  }
}
