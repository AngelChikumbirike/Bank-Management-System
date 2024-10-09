
package online.banking.project;

  import java.util.ArrayList;
import javax.swing.JOptionPane;

/*public class Bank   {
    ArrayList<Customer> Customers= new ArrayList<>();
    ArrayList<Account> Accounts =new ArrayList<>();
    
    public Bank( double startbalance, double interestRate){
     
      
    }
    public void deposit( double amount){
         +=amount ;
        
        
    }
    public void withdraw( double amount){
        
    }
    public void addInterest(){
        
    }
    public double getbalance(){
       // return ( super.getBalance());
        
        
         
        
    }
    public double getInterestRate(){
        return interestRate;
        
    }
    public double getPay(){
        return pay;
    }
}*/
public class Bank {
  private static int NEXT_CUSTOMER_ID = 1;
  private static int NEXT_ACCOUNT_NUMBER = 1;
  private static final String ADMIN_PASSWORD = "password";

  private final Map<String, Customer> customers; // maps each customer id to customer object
  private final Map<String, Account> accounts; // maps each account number to an account object

  public Bank() {
    this.customers = new HashMap<>();
    this.accounts = new HashMap<>();
  }

  public boolean isValidAdminPassword(String password) {
    return ADMIN_PASSWORD.equals(password);
  }

  public void updateCustomer(
      String customerId,
      String firstName,
      String lastName,
      String address,
      String phoneNumber,
      String email,
      String password)  {
    validateCustomerId(customerId);
    Customer customer = customers.get(customerId);

    // check if customer with the same email already exists
    if (!customer.getEmail().equals(email) && isCustomerExists(email)) {
      JOptionPane.showMessageDialog(null," Customer with email: "+ email+" already exists");
    }

    // update account details
    customer.setFirstName(firstName);
    customer.setLastName(lastName);
    customer.setAddress(address);
    customer.setEmail(email);
    customer.setPhoneNumber(phoneNumber);
    customer.setPassword(password);
  }

  public void addCustomer(
      String firstName,
      String lastName,
      String address,
      String phoneNumber,
      String email,
      String password)  {

    // check if customer with the same email already exists
    if (isCustomerExists(email)) {
      JOptionPane.showMessageDialog(null, "Customer with email:" + email+ " already exists");
    }

    String customerId = String.valueOf(generateCustomerId());
    String checkingAccountNumber = String.valueOf(generateAccountNumber());

    // create a customer object and add it to the customer map
    customers.put(customerId, new Customer(
        customerId,
        firstName,
        lastName,
        address,
        phoneNumber,
        email,
        password,
        checkingAccountNumber,
        null));

    // create checking account and add to the accounts map
    accounts.put(checkingAccountNumber, new CheckingAccount(checkingAccountNumber, customerId));
  }

  public void removeCustomer(String customerId)  {
    validateCustomerId(customerId);
    Customer customer = customers.get(customerId);

    // delete customer accounts
    accounts.remove(customer.getCheckingAccountNumber());
    if (customer.hasSavingsAccount()) {
      accounts.remove(customer.getSavingsAccountNumber());
    }

    // delete customer
    customers.remove(customerId);
  }

  public void addSavingsAccount(
      String customerId,
      double startingBalance)  {
    validateCustomerId(customerId);
    Customer customer = customers.get(customerId);
    String savingsAccountNumber = String.valueOf(generateAccountNumber());

    // add new savings account to accounts map
    if (startingBalance < SavingsAccount.getMinimumBalance()) {
      JOptionPane.showMessageDialog(null, String.format("You need to make a minimum deposit of $%.2f.", SavingsAccount.getMinimumBalance()));
    }
    accounts.put(savingsAccountNumber, new SavingsAccount(savingsAccountNumber, customerId, startingBalance));

    // add savings account id to customer
    customer.setSavingsAccountNumber(savingsAccountNumber);
  }

  public double deposit(String accountNumber, double amount) {
    //validateAccountNumber(accountNumber);
    return accounts.get(accountNumber).deposit(amount);
  }

  public double withdraw(
      String accountNumber,
      double amount)  {
   // validateAccountNumber(accountNumber);
    Account account = accounts.get(accountNumber);
    return account.withdraw(amount);
  }

  public void transfer(
      String fromAccountNumber,
      String toAccountNumber,
      double amount) {
   /* validateAccountNumber(toAccountNumber);
    validateAccountNumber(fromAccountNumber);*/

    accounts.get(fromAccountNumber).withdraw(amount);
    accounts.get(toAccountNumber).deposit(amount);
  }

  public void addInterest(String accountNumber) {
    //validateAccountNumber(accountNumber);//
    Account account = accounts.get(accountNumber);
    account.addInterest();
  }

  public Optional<String> validateCredentials(String email, String password) {
    return customers.values().stream()
        .filter(customer -> customer.getEmail().equals(email) && customer.getPassword().equals(password))
        .findFirst()
        .map(Customer::getId);
  }

  public Customer getCustomer(String customerId) throws CustomerNotFoundException {
    validateCustomerId(customerId);
    Customer customer = customers.get(customerId);
    return customer.copy();
  }

  public double getCheckingAccountBalance(String customerId) throws CustomerNotFoundException {
    validateCustomerId(customerId);
    Customer customer = customers.get(customerId);
    return accounts.get(customer.getCheckingAccountNumber()).getBalance();
  }

  public double getSavingsAccountBalance(String customerId) throws CustomerNotFoundException {
    validateCustomerId(customerId);
    Customer customer = customers.get(customerId);
    if (!customer.hasSavingsAccount()) {
      return 0;
    }
    return accounts.get(customer.getSavingsAccountNumber()).getBalance();
  }

  private boolean isCustomerExists(String email) {
    return customers.values().stream().anyMatch(customer -> customer.getEmail().equals(email));
  }

  private void validateAccountNumber(String accountNumber) throws AccountNotFoundException {
    if (!accounts.containsKey(accountNumber)) {
      throw new AccountNotFoundException(accountNumber);
    }
  }

  private void validateCustomerId(String customerId) {
    if (!customers.containsKey(customerId)) {
      JOptionPane.showMessageDialog(null,"CustomerNotFound"+ customerId);
    }
  }

  private int generateCustomerId() {
    return NEXT_CUSTOMER_ID++;
  }

  private int generateAccountNumber() {
    return NEXT_ACCOUNT_NUMBER++;
  }
}
