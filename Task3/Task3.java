package Task3;

import java.util.ArrayList;
import java.util.Scanner;

class InsufficientBalanceException extends Exception {
    InsufficientBalanceException() {
        super("Insufficient Balance");
    }
}

class BankAccount {

    private String name, userName, password, accountNo, transactionHistory = "";
    float balance = 500f;
    boolean transactions = true;

    BankAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Your Name - ");
        this.name = sc.nextLine();
        System.out.print("\nEnter Your Username - ");
        this.userName = sc.nextLine();
        System.out.print("\nEnter Your Password - ");
        this.password = sc.nextLine();
        System.out.print("\nEnter Your Account Number - ");
        this.accountNo = sc.nextLine();
        System.out.println("\nRegistration completed..kindly login");
    }

    public String getName() {
        return this.name;
    }

    public void withdraw() throws InsufficientBalanceException {
        System.out.print("\nEnter amount to withdraw :\t");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        if (balance < amount)
            throw new InsufficientBalanceException();
        else {
            transactions = false;
            balance -= amount;
            System.out.println("\nWithdraw Successfully");
            String str = amount + " Rs Withdrawed\n";
            transactionHistory = transactionHistory.concat(str);
        }
    }

    public void deposit() {

        System.out.print("\nEnter amount to deposit - ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();

        transactions = false;
        balance += amount;
        System.out.println("\nSuccessfully Deposited");
        String str = amount + " Rs deposited\n";
        transactionHistory = transactionHistory.concat(str);
    }

    public void transfer() throws InsufficientBalanceException {

        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Receipent's Name :\t");
        String receipent = sc.nextLine();
        System.out.print("\nEnter amount to transfer :\t");
        float amount = sc.nextFloat();

        if (balance < amount)
            throw new InsufficientBalanceException();
        else {
            if (amount <= 50000f) {
                transactions = false;
                balance -= amount;
                System.out.println("\nSuccessfully Transfered to " + receipent);
                String str = amount + " Rs transfered to " + receipent + "\n";
                transactionHistory = transactionHistory.concat(str);
            } else
                System.out.println("\nSorry...Limit is 50000.00 Rs");
        }
    }

    public void checkBalance() {
        System.out.println("\n" + balance + " Rs");
    }

    public boolean isValidCredentials(String accountNo, String userName, String password) {
        if (userName.equals(this.userName) && accountNo.equals(this.accountNo) && password.equals(this.password))
            return true;
        return false;
    }

    public void transHistory() {
        if (transactions)
            System.out.println("\nEmpty");
        else
            System.out.println("\n" + transactionHistory);
    }
}

public class Task3 {
    private static void loginFunctions(BankAccount obj) {
        Scanner sc = new Scanner(System.in);
        int subChoice;
        do {
            System.out.println("********** WELCOME " + obj.getName() + " **********");
            System.out.println(
                    "1.Deposit 2.Withdraw 3.Check Balance 4.Transfer Funds 5.Transaction History 6.Exit\nEnter your choice :\t");
            subChoice = sc.nextInt();

            switch (subChoice) {
                case 1:
                    obj.deposit();
                    break;

                case 2:
                    try {
                        obj.withdraw();
                    } catch (InsufficientBalanceException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 3:
                    obj.checkBalance();
                    break;

                case 4:
                    try {
                        obj.transfer();
                    } catch (InsufficientBalanceException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 5:
                    obj.transHistory();
                    break;

                default:
                    return;
            }
        } while (subChoice != 6);
    }

    public static void main(String[] args) {
        ArrayList<BankAccount> customers = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n********** WELCOME TO ATM SYSTEM **********\n");
            System.out.print("1.Register\t2.Login\t3.Exit\nEnter your choice :\t");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    customers.add(new BankAccount());
                    break;

                case 2:
                    if (customers.size() == 0) {
                        System.out.println("No Customers Registered Yet");
                        break;
                    }
                    System.out.println("Enter account number :\t");
                    String accountNo = sc.next();
                    System.out.println("Enter username :\t");
                    String userName = sc.next();
                    System.out.println("Enter password :\t");
                    String password = sc.next();

                    boolean flag = true;
                    BankAccount obj = null;
                    for (BankAccount bankAccount : customers) {
                        if (bankAccount.isValidCredentials(accountNo, userName, password)) {
                            obj = bankAccount;
                            flag = false;
                            break;
                        }
                    }

                    if (flag)
                        System.out.println("Invalid Credentials\nLogin Failed!!");
                    else
                        loginFunctions(obj);
                    break;

                default:
                    System.out.println("Exiting System!!");
            }
        } while (choice != 3);
    }
}