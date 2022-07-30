package entities;


import java.util.Date;
import java.util.List;


public class Account {

    private String accountNumber;
    private double balance = 0;

    private Date date;
    private List<Operation> operations;


    public Account() {
    }

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        this.date = new Date();
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", date=" + date +
                ", operations=" + operations +
                '}';
    }
}
