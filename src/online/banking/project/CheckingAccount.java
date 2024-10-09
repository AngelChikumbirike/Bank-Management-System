
package online.banking.project;


/*public class CheckingAccount extends Account{
    private String Opening_date;
    public CheckingAccount(){
        
    }
 public void addAccount()   {
        
    }
   public void removeAccount() {
       
       
   }    
    
}*/
public class CheckingAccount extends Account {
  static {
    INTEREST_RATE = 1; // as a percentage
  }

  public CheckingAccount(String accountNumber, String customerId, double balance) {
    super(accountNumber, customerId, balance);
  }

  public CheckingAccount(String accountNumber, String customerId) {
    super(accountNumber, customerId);
  }
}




