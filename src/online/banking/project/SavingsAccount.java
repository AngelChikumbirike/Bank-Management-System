
package online.banking.project;
import javax.swing.JOptionPane;

/*public class SavingsAccount extends Account {
    private double MinumumBal;
    private String Opening_date;
    public SavingsAccount(){
        
    }
            
    public void removeCustomer(){
        
    }
    
    public void addAccount(){
        
    }
}
*/
import online.banking.project.exceptions.InvalidOperationException;

public class SavingsAccount extends Account {
  static {
    INTEREST_RATE = 4;
  }
  private static final double MINIMUM_BALANCE = 10;

  public SavingsAccount(String accountNumber, String customerId, double balance) {
    super(accountNumber, customerId, balance);
  }

  public SavingsAccount(String accountNumber, String customerId) {
    super(accountNumber, customerId);
  }

  public static double getMinimumBalance() {
    return MINIMUM_BALANCE;
  }

  @Override
  public double withdraw(double amount) throws InvalidOperationException {
    return withdraw(amount, MINIMUM_BALANCE);
  }
}