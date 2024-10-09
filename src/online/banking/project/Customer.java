
package online.banking.project;

import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.lang.Math;
import java.util.Date;


/*public class Customer  {
    ArrayList<Customer> Customers= new ArrayList<>();
    private String FirstName;
    private String LastName;
    private String Address;
    private String Phone;
    private String Email;
    private String customerID;
    Account account ;
   // SavingsAccount savings;
    //CheckingAccount checking;
    public Customer(){
     //savings= new SavingsAccount();
        // checking= new CheckingAccount();
        double random= Math.random();
        account= new Account(0,random);
        
        
    }
   
    
    public void addCustomer(){
        String name,  lname,  Add,  num,  mail,  Id;
       name= JOptionPane.showInputDialog( " Enter The first name of customer ");
       this.FirstName= name;
       lname=JOptionPane.showInputDialog( " Enter The last name of customer ");
       this.LastName=lname;
       Add=JOptionPane.showInputDialog( " Enter The customer home address ");
       this.Address=Add;
       num=JOptionPane.showInputDialog( " What is the customer's 10 digit phone number ");
       this.Phone=num;
       mail=JOptionPane.showInputDialog( " Enter the customer's email address");
       this.Email=mail;
       Id=JOptionPane.showInputDialog( " Enter the CustomerID ");
        this.customerID=Id;
        
      if (this.FirstName !=null && this.LastName != null && this.Address !=null && this.Phone!=null && this.Email!=null && this.customerID != null){
             
                Customers.add(new Customer());
      
                JOptionPane.showMessageDialog(null, "Customer Added at" +" "+ LocalDateTime.now()+ " !");
      }         
    }
    public void removeCustomer(){
       String Id=JOptionPane.showInputDialog( " Enter The CustomerId of the customer ");
      boolean  customerFound=false;
        
    for (int i = 0; i < Customers.size(); i++) {
        Customer customer = Customers.get(i);
        
        if (customer.customerID.equals(Id)) {
            
            Customers.remove(i);
            JOptionPane.showMessageDialog(null, " Customer removed successfully at " + LocalDateTime.now()+" !");
            customerFound = true;
            break; 
        }
    }

    if (!customerFound) {
        JOptionPane.showMessageDialog(null, "Customer with ID " + Id + " not found.");
    }
}       
    public void editCustomer(){
        String Id= JOptionPane.showInputDialog("Enter the customer Id");
        boolean Found=false;
        for (int i = 0; i < Customers.size(); i++) {
        Customer customer = Customers.get(i);
        
        if (customer.customerID.equals(Id)) {
            
            String newName = JOptionPane.showInputDialog("Enter new first name (current: " + customer.FirstName + "): ");
            String newLastName = JOptionPane.showInputDialog("Enter new last name (current: " + customer.LastName + "): ");
            String newAddress = JOptionPane.showInputDialog("Enter new address (current: " + customer.Address + "): ");
            String newPhone = JOptionPane.showInputDialog("Enter new phone number (current: " + customer.Phone + "): ");
            String newEmail = JOptionPane.showInputDialog("Enter new email address (current: " + customer.Email + "): ");
           
            customer.FirstName = newName;
            customer.LastName = newLastName;
            customer.Address = newAddress;
            customer.Phone = newPhone;
            customer.Email = newEmail;
            
            JOptionPane.showMessageDialog(null, "Customer information updated successfully.");
            Found = true;
            break; 
        }
        }

    if (!Found) {
        JOptionPane.showMessageDialog(null, "Customer with ID " + Id + " not found.");
    }
    } 
  public void searchCustomer(){
      String Id= JOptionPane.showInputDialog("Enter Customer ID");
      boolean Found=false;
        for (int i = 0; i < Customers.size(); i++) {
        Customer customer = Customers.get(i);
        
        if (customer.customerID.equals(Id)) {JOptionPane.showMessageDialog(null, "Customer found!The name is"+ this.FirstName+ this.LastName);
            Found = true;
            break;
        }
}
        if (!Found) {
        JOptionPane.showMessageDialog(null, "Customer with ID " + Id + " not found.");
    }
  }
}
*/

public class Customer {
  private final String id;
  private String firstName;
  private String lastName;
  private String address;
  private String phoneNumber;
  private String email;
  private String password;
  private final String checkingAccountNumber;
  private String savingsAccountNumber; // can be null if user does not have savings account
  private final Date dateCreated;

  public Customer(
      String id,
      String firstName,
      String lastName,
      String address,
      String phoneNumber,
      String email,
      String password,
      String checkingAccountNumber,
      String savingsAccountNumber) {

    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.password = password;
    this.checkingAccountNumber = checkingAccountNumber;
    this.savingsAccountNumber = savingsAccountNumber;
    this.dateCreated = new Date();
  }

  private Customer(
      String id,
      String firstName,
      String lastName,
      String address,
      String phoneNumber,
      String email,
      String password,
      String checkingAccountNumber,
      String savingsAccountNumber,
      Date dateCreated) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.password = password;
    this.checkingAccountNumber = checkingAccountNumber;
    this.savingsAccountNumber = savingsAccountNumber;
    this.dateCreated = dateCreated;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getFullName() {
    return String.format("%s %s", firstName, lastName);
  }

  public String getAddress() {
    return address;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getCheckingAccountNumber() {
    return checkingAccountNumber;
  }

  public String getSavingsAccountNumber() {
    return savingsAccountNumber;
  }

  public boolean hasSavingsAccount() {
    return savingsAccountNumber != null;
  }

  public void setSavingsAccountNumber(String savingsAccountNumber){ 
    if (hasSavingsAccount()) {
      JOptionPane.showMessageDialog( null,
          "You cannot create a new Savings account since you already have another one.");
    }
    this.savingsAccountNumber = savingsAccountNumber;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Customer copy() {
    return new Customer(
        id,
        firstName,
        lastName,
        address,
        phoneNumber,
        email,
        password,
        checkingAccountNumber,
        savingsAccountNumber,
        dateCreated);
  }
}
