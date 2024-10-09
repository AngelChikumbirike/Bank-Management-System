
package online.banking.project;

import java.util.Optional;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/*public class OnlineBankingProject {

   
    public static void main(String[] args) {
        Customer customer= new Customer();
    String Str= JOptionPane.showInputDialog("Hello, welcome back to Angel Banking system."+"What would you like to do today?\n"+ "A.Add customer\n" 
                + "B.Remove customer\n"+"C.Edit Customer\n"+ "D.Search Customer\n");
    Str=Str.toLowerCase();
        switch(Str){
        case "a":
            for (int i=1; i>=1;i++){
                String str=JOptionPane.showInputDialog("Do you want to add a Customer?");
                str=str.toLowerCase();
                if (str.equals("yes")){
             customer.addCustomer();
   
                }
                else break;   
            }
             break;    
        case "b":
            for (int i=1; i>=1;i++){
                String str=JOptionPane.showInputDialog("Do you want to remove a Customer?");
                str=str.toLowerCase();
                if (str.equals("yes")){
             customer.removeCustomer();
                 }
                else break;   
            }
            break;
        case "c":
            for (int i=1; i>=1;i++){
                String str=JOptionPane.showInputDialog("Do you want to edit a Customer?");
                str=str.toLowerCase();
                if (str.equals("yes")){
             customer.editCustomer();}
                else break;   
            }
            break;
        case "d":
            for (int i=1; i>=1;i++){
                String str=JOptionPane.showInputDialog("Do you want to search a Customer?");
                str=str.toLowerCase();
                if (str.equals("yes")){
             customer.searchCustomer();}
                else break;   
            }
            break;
        }
        
        
    }
    
}
*/
public class OnlineBankingProject {
  private static final Bank bank = new Bank();

  public static void main(String[] args) {
    displayWelcomeDialog();
  }

  private static void displayWelcomeDialog() {
    while (true) {
      String option = JOptionPane.showInputDialog(
          null,
          "Hello, welcome to Angel Banking System. What do you want to do today?\n\n"
              + "A.  Log In\n"
              + "B.  Sign Up\n"
              + "C.  Admin Portal\n\n",
          "Angel Banking System Menu",
          JOptionPane.QUESTION_MESSAGE);

      if (option == null) {
        return;
      }

      // ensure user enters valid options
      switch (option.toLowerCase().trim()) {
        case "a":
          displayLoginDialog();
          break;

        case "b":
          displaySignupDialog(null);
          break;

        case "c":
          displayAdminPortalLoginDialog();
          break;

        default:
          JOptionPane.showMessageDialog(
              null,
              "Invalid option. Please enter A, B, or C.",
              "Invalid Input",
              JOptionPane.ERROR_MESSAGE);
          break;
      }
    }
  }
private static void displayLoginDialog() {
    
    JOptionPane.showMessageDialog(null,"Please enter your email and password to log in.");
    String email= JOptionPane.showInputDialog("Email address:");
    String password = JOptionPane.showInputDialog("Password:");
    
    while (true) {
      int result = JOptionPane.showConfirmDialog(
          null,
          email,//
          "Log In",
          JOptionPane.OK_CANCEL_OPTION);
      switch (result) {
        case JOptionPane.OK_OPTION:
          // check if user has entered all the fields
          if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(
                null,
                "Please enter your email and password.",
                "Incomplete Account Details",
                JOptionPane.ERROR_MESSAGE);
            break;
          }

          // check if the credentials are valid
          Optional<String> possibleCustomerId = bank.validateCredentials(email, password);
          if (!possibleCustomerId.isPresent()) {
            JOptionPane.showMessageDialog(
                null,
                "There is no account associated with this email or password.",
                "Invalid Credentials",
                JOptionPane.ERROR_MESSAGE);
            break;
          }

          displayCustomerDialog(possibleCustomerId.get());
          return;

        case JOptionPane.CANCEL_OPTION:
        case JOptionPane.CLOSED_OPTION:
          return;
      }
    }
  }

  private static void displaySignupDialog(String customerId) {
    displayAccountDetailsInputForm(
        "Sign Up",
        "Please enter your details below to create a checking account with us.",
        "Account Created Successfully",
        "Your checking account has been created successfully. You can log in and start depositing money today.",
        "",
        "",
        "",
        "",
        "",
        "",
        customerId);
  }

  private static void displayAccountDetailsInputForm(
      String title,
      String heading,
      String successTitle,
      String successMessage,
      String initialFirstName,
      String initialLastName,
      String initialAddress,
      String initialPhoneNumber,
      String initialEmail,
      String initialPassword,
      String customerId) {
    int columns = 30;

    JTextField firstNameField = new JTextField(initialFirstName, columns);
    JTextField lastNameField = new JTextField(initialLastName, columns);
    JTextField addressField = new JTextField(initialAddress, columns);
    JTextField phoneNumberField = new JTextField(initialPhoneNumber, columns);
    JTextField emailAddressField = new JTextField(initialEmail, columns);
    JTextField passwordField = new JTextField(initialPassword, columns);

    JPanel myPanel = new JPanel();
    myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

    myPanel.add(new JLabel(heading));
    myPanel.add(new JLabel("\n"));
    myPanel.add(new JLabel("\n"));

    myPanel.add(new JLabel("First name:"));
    myPanel.add(firstNameField);

    myPanel.add(new JLabel("Last name:"));
    myPanel.add(lastNameField);

    myPanel.add(new JLabel("Home address:"));
    myPanel.add(addressField);

    myPanel.add(new JLabel("Phone number:"));
    myPanel.add(phoneNumberField);

    myPanel.add(new JLabel("Email address:"));
    myPanel.add(emailAddressField);

    myPanel.add(new JLabel("Password:"));
    myPanel.add(passwordField);

    while (true) {
      int result = JOptionPane.showConfirmDialog(
          null,
          myPanel,
          title,
          JOptionPane.OK_CANCEL_OPTION);

      String firstName = firstNameField.getText().trim();
      String lastName = lastNameField.getText().trim();
      String address = addressField.getText().trim();
      String phoneNumber = phoneNumberField.getText().trim();
      String email = emailAddressField.getText().trim();
      String password = passwordField.getText().trim();

      switch (result) {
        case JOptionPane.OK_OPTION:
          // check if user has entered all the fields
          if (firstName.isEmpty() ||
              lastName.isEmpty() ||
              address.isEmpty() ||
              phoneNumber.isEmpty() ||
              email.isEmpty() ||
              password.isEmpty()) {
            JOptionPane.showMessageDialog(
                null,
                "Please fill in all fields.",
                "Incomplete Account Details",
                JOptionPane.ERROR_MESSAGE);
            break;
          }

          try {
            if (customerId == null) {
              // add a new customer to the bank
              bank.addCustomer(firstName, lastName, address, phoneNumber, email, password);
            } else {
              // update existing customer
              bank.updateCustomer(customerId, firstName, lastName, address, phoneNumber, email, password);
            }

          } catch (CustomerAlreadyExistsException e) {
            JOptionPane.showMessageDialog(
                null,
                e.getMessage(),
                "Email Already In Use",
                JOptionPane.ERROR_MESSAGE);
            break;

          } catch (CustomerNotFoundException e) {
            displayAccountNoLongerExistMessage();
            return;
          }

          JOptionPane.showMessageDialog(
              null,
              successMessage,
              successTitle,
              JOptionPane.INFORMATION_MESSAGE);
          return;

        case JOptionPane.CANCEL_OPTION:
        case JOptionPane.CLOSED_OPTION:
          return;
      }
    }
  }

  private static void displayAccountNoLongerExistMessage() {
    JOptionPane.showMessageDialog(
        null,
        "Your account no longer exists. Please contact customer support for more information.",
        "Account No Longer Exists",
        JOptionPane.ERROR_MESSAGE);
  }

  private static void displayCustomerDialog(String customerId) {
    Customer customer;
    double checkingAccountBalance, savingsAccountBalance;

    while (true) {
      try {
        customer = bank.getCustomer(customerId);
        checkingAccountBalance = bank.getCheckingAccountBalance(customerId);
        savingsAccountBalance = bank.getSavingsAccountBalance(customerId);

      } catch (CustomerNotFoundException e) {
        displayAccountNoLongerExistMessage();
        return;
      }

      String menu;
      if (customer.hasSavingsAccount()) {
        menu = ""
            + "A.  View Checking Account\n"
            + "B.  View Savings Account\n"
            + "C.  Edit Account Details\n"
            + "D.  Log out\n\n";
      } else {
        menu = ""
            + "A.  View Checking Acccount\n"
            + "B.  Add Savings Account\n"
            + "C.  Edit Account Details\n"
            + "D.  Log out\n\n";
      }

      String option = JOptionPane.showInputDialog(
          null,
          getCustomerAccountSummary(customer, checkingAccountBalance, savingsAccountBalance)
              + "\n\n"
              + "What do you want to do today?\n"
              + menu,
          String.format("Welcome, %s", customer.getFullName()),
          JOptionPane.QUESTION_MESSAGE);

      if (option == null) {
        return;
      }

      // ensure user enters valid options
      switch (option.toLowerCase().trim()) {
        case "a":
          displayViewCheckingAccountDialog(customer, checkingAccountBalance, savingsAccountBalance);
          break;

        case "b":
          if (customer.hasSavingsAccount()) {
            displayViewSavingsAccountDialog(customer, checkingAccountBalance, savingsAccountBalance);
          } else {
            displayAddSavingsAccountDialog(customerId);
          }
          break;

        case "c":
          displayEditCustomerDialog(customer);
          break;

        case "d":
          // log out by exiting the function
          return;

        default:
          String errorMessage = "Invalid option. Please enter "
              + (customer.hasSavingsAccount() ? "A, B, C or D" : "A, B, C, D or E");
          JOptionPane.showMessageDialog(
              null,
              errorMessage,
              "Invalid Input",
              JOptionPane.ERROR_MESSAGE);
          break;
      }
    }
  }

  private static String getCustomerAccountSummary(
      Customer customer,
      double checkingAccountBalance,
      double savingsAccountBalance) {
    String summary = String.format(""
        + "Email Address: %s\n"
        + "Phone Number: %s\n"
        + "Home Address: %s\n"
        + "Checking Account Balance: $%.2f\n",
        customer.getEmail(),
        customer.getPhoneNumber(),
        customer.getAddress(),
        checkingAccountBalance);

    if (customer.hasSavingsAccount()) {
      summary += String.format("Savings Account Balance: $%.2f\n", savingsAccountBalance);
    }
    return summary;
  }

  private static void displayViewCheckingAccountDialog(
      Customer customer,
      double checkingAccountBalance,
      double savingsAccountBalance) {
    displayAccountOptionsDialog(CheckingAccount.class, customer, customer.hasSavingsAccount(), checkingAccountBalance,
        savingsAccountBalance);
  }

  private static void displayViewSavingsAccountDialog(
      Customer customer,
      double checkingAccountBalance,
      double savingsAccountBalance) {
    displayAccountOptionsDialog(SavingsAccount.class, customer, true, checkingAccountBalance, savingsAccountBalance);
  }

  private static void displayAccountOptionsDialog(
      Class<? extends Account> accountType,
      Customer customer,
      boolean includeTransferOption,
      double checkingAccountBalance,
      double savingsAccountBalance) {

    while (true) {
      // create message displayed at the top of the dialog
      String message = String.format("Checking Account Balance: $%.2f\n", checkingAccountBalance);
      if (includeTransferOption) {
        message += String.format("Savings Account Balance: $%.2f\n", savingsAccountBalance);
      }

      // create dialog title
      String title = accountType == SavingsAccount.class
          ? "Savings Account Options"
          : "Checking Account Options";

      // create dialog options
      String menu;
      if (includeTransferOption) {
        String transferToOption = accountType == SavingsAccount.class
            ? "C.  Transfer to Checking Account\n\n"
            : "C.  Transfer to Savings Acccount\n\n";
        menu = ""
            + "A.  Deposit Money\n"
            + "B.  Withdraw Money\n"
            + transferToOption;

      } else {
        menu = ""
            + "A.  Deposit Money\n"
            + "B.  Withdraw Money\n\n";
      }

      String option = JOptionPane.showInputDialog(
          null,
          message
              + "\n"
              + "What do you want to do today?\n"
              + menu,
          title,
          JOptionPane.QUESTION_MESSAGE);

      if (option == null) {
        return;
      }

      // ensure user enters valid options
      double balance = accountType == SavingsAccount.class ? savingsAccountBalance : checkingAccountBalance;
      switch (option.toLowerCase().trim()) {
        case "a":
          displayDepositMoneyDialog(accountType, customer, balance);
          return;

        case "b":
          displayWithdrawMoneyDialog(accountType, customer, balance);
          return;

        case "c":
          if (includeTransferOption) {
            if (accountType == SavingsAccount.class) {
              displayTransferMoneyDialog(customer, CheckingAccount.class, SavingsAccount.class);
            } else {
              displayTransferMoneyDialog(customer, SavingsAccount.class, CheckingAccount.class);
            }
            return;
          }

        default:
          String errorMessage = "Invalid option. Please enter " + (includeTransferOption ? "A, B or C." : "A or B.");
          JOptionPane.showMessageDialog(
              null,
              errorMessage,
              "Invalid Input",
              JOptionPane.ERROR_MESSAGE);
          break;
      }
    }
  }

  private static void displayDepositMoneyDialog(
      Class<? extends Account> toAccountClass,
      Customer customer,
      double balance) {
    String toAccount = toAccountClass == SavingsAccount.class ? "Savings" : "Checking";
    while (true) {
      String depositAmountAsString = JOptionPane.showInputDialog(
          null,
          String.format(
              "Your current %s account balance is $%.2f. How much money do you want to deposit to your %s account?\n\n",
              toAccount, balance, toAccount),
          String.format("Deposit to %s account", toAccount),
          JOptionPane.QUESTION_MESSAGE);

      if (depositAmountAsString == null) {
        return;
      }

      double depositAmount;
      try {
        depositAmount = Double.parseDouble(depositAmountAsString);
        if (depositAmount <= 0.0) {
          throw new IllegalArgumentException();
        }

      } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(
            null,
            "Please enter a valid deposit amount.",
            "Invalid Input",
            JOptionPane.ERROR_MESSAGE);
        continue;
      }

      // deposit money to the bank
      try {
        String toAccountNumber = toAccountClass == SavingsAccount.class
            ? customer.getSavingsAccountNumber()
            : customer.getCheckingAccountNumber();
        double newBalance = bank.deposit(toAccountNumber, depositAmount);
        JOptionPane.showMessageDialog(
            null,
            String.format("Your new %s account balance is $%.2f.", toAccount, newBalance),
            "Transaction Completed",
            JOptionPane.INFORMATION_MESSAGE);

      } catch (AccountNotFoundException e) {
        displayAccountNoLongerExistMessage();
      }
      return;
    }
  }

  private static void displayWithdrawMoneyDialog(
      Class<? extends Account> fromAccountClass,
      Customer customer,
      double balance) {
    String fromAccount = fromAccountClass == SavingsAccount.class ? "Savings" : "Checking";
    while (true) {
      String withdrawalAmountAsString = JOptionPane.showInputDialog(
          null,
          String.format(
              "Your current %s account balance is $%.2f. How much money do you want to withdraw from your %s account?\n\n",
              fromAccount, balance, fromAccount),
          String.format("Withdraw from %s account", fromAccount),
          JOptionPane.QUESTION_MESSAGE);

      if (withdrawalAmountAsString == null) {
        return;
      }

      double withdrawalAmount;
      try {
        withdrawalAmount = Double.parseDouble(withdrawalAmountAsString);
        if (withdrawalAmount <= 0.0) {
          throw new IllegalArgumentException();
        }

      } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(
            null,
            "Please enter a valid withdrawal amount.",
            "Invalid Input",
            JOptionPane.ERROR_MESSAGE);
        continue;
      }

      // withdraw money from the bank
      try {
        String fromAccountNumber = fromAccountClass == SavingsAccount.class
            ? customer.getSavingsAccountNumber()
            : customer.getCheckingAccountNumber();
        double newBalance = bank.withdraw(fromAccountNumber, withdrawalAmount);
        JOptionPane.showMessageDialog(
            null,
            String.format("Your new %s account balance is $%.2f.", fromAccount, newBalance),
            "Transaction Completed",
            JOptionPane.INFORMATION_MESSAGE);

      } catch (AccountNotFoundException e) {
        displayAccountNoLongerExistMessage();

      } catch (InvalidOperationException e) {
        JOptionPane.showMessageDialog(
            null,
            e.getMessage(),
            "Transaction Not Completed",
            JOptionPane.INFORMATION_MESSAGE);
      }
      return;
    }
  }

  private static void displayTransferMoneyDialog(
      Customer customer,
      Class<? extends Account> toAccountClass,
      Class<? extends Account> fromAccountClass ) {

    String fromAccount = fromAccountClass == SavingsAccount.class ? "Savings" : "Checking";
    String toAccount = toAccountClass == SavingsAccount.class ? "Savings" : "Checking";

    while (true) {
      String transferAmountAsString = JOptionPane.showInputDialog(
          null,
          String.format(
              "How much money do you want to transfer from your %s account to your %s account?\n\n",
              fromAccount, toAccount),
          String.format("Transfer to %s Account", toAccount),
          JOptionPane.QUESTION_MESSAGE);

      if (transferAmountAsString == null) {
        return;
      }

      double transferAmount;
      try {
        transferAmount = Double.parseDouble(transferAmountAsString);
        if (transferAmount <= 0.0) {
          throw new IllegalArgumentException();
        }

      } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(
            null,
            "Please enter a valid deposit amount.",
            "Invalid Input",
            JOptionPane.ERROR_MESSAGE);
        continue;
      }

      // transfer money between accounts
      try {
        String toAccountNumber = toAccountClass == SavingsAccount.class
            ? customer.getSavingsAccountNumber()
            : customer.getCheckingAccountNumber();
        String fromAccountNumber = fromAccountClass == SavingsAccount.class
            ? customer.getSavingsAccountNumber()
            : customer.getCheckingAccountNumber();
        bank.transfer(fromAccountNumber, toAccountNumber, transferAmount);

      } catch (AccountNotFoundException e) {
        displayAccountNoLongerExistMessage();

      } catch (InvalidOperationException e) {
        JOptionPane.showMessageDialog(
            null,
            e.getMessage(),
            "Transaction Not Completed",
            JOptionPane.INFORMATION_MESSAGE);
      }
      return;
    }
  }

  private static void displayAddSavingsAccountDialog(String customerId) {
    double minimumDepositAmount = SavingsAccount.getMinimumBalance();
    while (true) {
      String depositAmountAsString = JOptionPane.showInputDialog(
          null,
          String.format(
              "To create a Savings account, you need to make a minimum deposit of $%.2f. How much money do you want to deposit today?\n\n",
              minimumDepositAmount),
          "Add Savings Account",
          JOptionPane.QUESTION_MESSAGE);

      if (depositAmountAsString == null) {
        return;
      }

      double depositAmount;
      try {
        depositAmount = Double.parseDouble(depositAmountAsString);
        if (depositAmount < minimumDepositAmount) {
          throw new IllegalArgumentException();
        }

      } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(
            null,
            String.format(
                "Please enter a valid deposit amount which is at least $%.2f.",
                SavingsAccount.getMinimumBalance()),
            "Invalid Input",
            JOptionPane.ERROR_MESSAGE);
        continue;
      }

      // add a savings account
      try {
        bank.addSavingsAccount(customerId, depositAmount);
        JOptionPane.showMessageDialog(
            null,
            String.format("You now have a Savings account with a balance of $%.2f", depositAmount),
            "Savings Account Added",
            JOptionPane.INFORMATION_MESSAGE);

      } catch (CustomerNotFoundException e) {
        displayAccountNoLongerExistMessage();

      } catch (InvalidOperationException e) {
        JOptionPane.showMessageDialog(
            null,
            String.format("%s Please contact customer support for more information.", e.getMessage()),
            "Savings Account Not Added",
            JOptionPane.INFORMATION_MESSAGE);
      }
      return;
    }
  }

  private static void displayEditCustomerDialog(Customer customer) {
    displayAccountDetailsInputForm(
        "Edit Account Details",
        "Please update your account details below.",
        "Account Updated Successfully",
        "Your account details have been updated successfully.",
        customer.getFirstName(),
        customer.getLastName(),
        customer.getAddress(),
        customer.getPhoneNumber(),
        customer.getEmail(),
        customer.getPassword(),
        customer.getId());
  }

  private static void displayAdminPortalLoginDialog() {
    while (true) {
      String adminPassword = JOptionPane.showInputDialog(
          null,
          "Welcome to the Angel Banking System Admin Portal. Please enter admin password to proceed?\n\n",
          "Angel Banking Admin Portal",
          JOptionPane.QUESTION_MESSAGE);

      if (adminPassword == null) {
        return;
      }

      if (!bank.isValidAdminPassword(adminPassword.trim())) {
        JOptionPane.showMessageDialog(
            null,
            "Please enter the correct admin password.",
            "Invalid Admin Password",
            JOptionPane.ERROR_MESSAGE);
        continue;
      }

      displayAdminPortalOptionsDialog();
    }
  }

  private static void displayAdminPortalOptionsDialog() {
    while (true) {
      String option = JOptionPane.showInputDialog(
          null,
          "Welcome to the Angel Banking System Admin Portal. What do you want to do today?\n\n"
              + "A.  Search Customer\n"
              + "B.  Update Savings Account Interest Rate\n"
              + "C.  Update Checking Account Interest Rate\n\n",
          "Angel Banking Admin Portal",
          JOptionPane.QUESTION_MESSAGE);

      if (option == null) {
        return;
      }

      switch (option.toLowerCase().trim()) {
        case "a":
          displayAdminPortalSearchDialog();
          break;

        case "b":
          displayUpdateInterestRateDialog(SavingsAccount.class);
          break;

        case "c":
          displayUpdateInterestRateDialog(CheckingAccount.class);
          break;

        default:
          JOptionPane.showMessageDialog(
              null,
              "Invalid option. Please enter A, B or C",
              "Invalid Input",
              JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private static void displayUpdateInterestRateDialog(Class<? extends Account> accountClass) {
    double minInterestRate = 0.0;
    double maxInterestRate = 10.0;

    while (true) {
      String account = accountClass == SavingsAccount.class ? "Savings" : "Checking";
      double currentInterestRate = accountClass == SavingsAccount.class
          ? SavingsAccount.getInterestRate()
          : CheckingAccount.getInterestRate();
      String interestRateAsString = JOptionPane.showInputDialog(
          null,
          String.format(
              "The current %s accounts intereste rate is %.2f%%. Please enter the new interest rate (%.2f - %.2f):\n\n",
              account, currentInterestRate, minInterestRate, maxInterestRate),
          "Angel Banking Admin Portal",
          JOptionPane.QUESTION_MESSAGE);

      if (interestRateAsString == null) {
        return;
      }

      double interestRate;
      try {
        interestRate = Double.parseDouble(interestRateAsString);
        if (interestRate < minInterestRate || interestRate > maxInterestRate) {
          throw new IllegalArgumentException();
        }
      } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(
            null,
            "Please enter a valid interest rate.",
            "Invalid Input",
            JOptionPane.ERROR_MESSAGE);
        continue;
      }

      // update interest rate
      if (accountClass == SavingsAccount.class) {
        SavingsAccount.setInterestRate(interestRate);
      } else {
        CheckingAccount.setInterestRate(interestRate);
      }

      JOptionPane.showMessageDialog(
          null,
          String.format("%s accounts interest rate has been update to %.2f%%", account, interestRate),
          "Transaction Completed",
          JOptionPane.INFORMATION_MESSAGE);
      return;
    }
  }

  private static void displayAdminPortalSearchDialog() {
    while (true) {
      String customerId = JOptionPane.showInputDialog(
          null,
          "Please enter the customerId for the customer you want to look at:\n\n",
          "Angel Banking Admin Portal",
          JOptionPane.QUESTION_MESSAGE);

      if (customerId == null) {
        return;
      }

      customerId = customerId.trim();
      Customer customer;
      try {
        customer = bank.getCustomer(customerId);

      } catch (CustomerNotFoundException e) {
        JOptionPane.showMessageDialog(
            null,
            String.format("There is not customer with id: %s. Please enter a valid customerId.", customerId),
            "Customer Not Found",
            JOptionPane.ERROR_MESSAGE);
        continue;
      }

      displayAdminPortalCustomerOptions(customer);
      return;
    }
  }

  private static void displayAdminPortalCustomerOptions(Customer customer) {

    while (true) {
      double checkingAccountBalance, savingsAccountBalance;
      try {
        checkingAccountBalance = bank.getCheckingAccountBalance(customer.getId());
        savingsAccountBalance = bank.getSavingsAccountBalance(customer.getId());

      } catch (CustomerNotFoundException e) {
        JOptionPane.showMessageDialog(
            null,
            String.format("There is not customer with id: %s. Please enter a valid customerId.", customer.getId()),
            "Customer Not Found",
            JOptionPane.ERROR_MESSAGE);
        continue;
      }

      String option = JOptionPane.showInputDialog(
          null,
          String.format("Here is the account summary for %s:\n\n", customer.getFullName())
              + getCustomerAccountSummary(customer, checkingAccountBalance, savingsAccountBalance)
              + "\n\n"
              + "What do you want to do today?\n"
              + "A.  Remove Customer\n"
              + "B.  Add Interest to Checking Account\n"
              + (customer.hasSavingsAccount()
                  ? "C.  Add Interest to Savings Account\n\n"
                  : "\n"),
          "Angel Banking Admin Portal",
          JOptionPane.QUESTION_MESSAGE);

      if (option == null) {
        return;
      }

      switch (option.toLowerCase().trim()) {
        case "a":
          handleRemoveCustomer(customer);
          break;

        case "b":
          handleAddInterest(customer.getCheckingAccountNumber(), "Checking");
          break;

        case "c":
          if (customer.hasSavingsAccount()) {
            handleAddInterest(customer.getSavingsAccountNumber(), "Savings");
            break;
          }

        default:
          String errorMessage = "Invalid option. Please enter "
              + (customer.hasSavingsAccount() ? "A, B or C" : "A or B");
          JOptionPane.showMessageDialog(
              null,
              errorMessage,
              "Invalid Input",
              JOptionPane.ERROR_MESSAGE);  
      }
    }
  }

  private static void handleRemoveCustomer(Customer customer) {
    try {
      bank.removeCustomer(customer.getId());
      JOptionPane.showMessageDialog(
          null,
          "This customer has been removed permanently. Their accounts have also been removed.",
          "Customer Removed Successfully",
          JOptionPane.INFORMATION_MESSAGE);

    } catch (CustomerNotFoundException e) {
      JOptionPane.showMessageDialog(
          null,
          String.format("There is not customer with id: %s. Please try again later.", customer.getId()),
          "Customer Not Found",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  private static void handleAddInterest(String accountNumber, String account) {
    try {
      bank.addInterest(accountNumber);
      JOptionPane.showMessageDialog(
          null,
          String.format("You have successfully added interest to the customer's %s account.", account),
          String.format("Customer %s Account Interest Added", account),
          JOptionPane.INFORMATION_MESSAGE);

    } catch (AccountNotFoundException e) {
      JOptionPane.showMessageDialog(
          null,
          String.format("This customer no longer has a %s account.", account),
          "Customer Not Found",
          JOptionPane.ERROR_MESSAGE);
    }
  }
}
