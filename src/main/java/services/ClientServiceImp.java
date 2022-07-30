
package services;


import config.ColorText;
import config.Validators;
import entities.Account;
import entities.Client;
import entities.Operation;
import entities.OperationType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class ClientServiceImp implements ClientService {

    @Override
    public Client createClient(String username, String email, String password, String passwordConf) {
        if(!password.equals(passwordConf)){
            System.out.println(ColorText.color("Passwords Do Not Match", "RED"));
            return null;
        } else if (!Validators.isEmailValid(email)) {
            System.out.println(ColorText.color("The email is not valid", "RED"));
            return null;
        }
        System.out.println(ColorText.color("Account Created Successfully", "GREEN"));
        return new Client(username, email, password);
    }

    @Override
    public boolean signIn(Client currentClient, String email, String password) {
        if(currentClient.getEmail().equals(email) && currentClient.getPassword().equals(password)){
            System.out.println(ColorText.color("Logged In Successfully", "GREEN"));
            return true;
        } else{
            System.out.println(ColorText.color("Wrong Email or Password", "RED"));
            return false;
        }
    }

    @Override
    public boolean createBankAccount(Client currentClient) {
        try {
            String accountNumber = generateAccountNumber();
            List<Account> clientAccounts = new ArrayList<>();

            if (currentClient.getAccounts() != null) clientAccounts.addAll(currentClient.getAccounts());

            clientAccounts.add(new Account(accountNumber));
            currentClient.setAccounts(clientAccounts);

            System.out.println(ColorText.color("your bank account was created Successfully", "GREEN"));
            return true;
        } catch (Exception e) {
            System.out.println(ColorText.color("Something went wrong, please try again", "RED"));
            return false;
        }
    }

    @Override
    public boolean deleteBankAccount(Client currentClient, String accountNumber) {
        boolean result = currentClient.getAccounts().removeIf(element -> (element.getAccountNumber().equals(accountNumber)));
        if(result) {
            System.out.println(ColorText.color("Account deleted successfully", "GREEN"));
            return true;
        }else {
            System.out.println(ColorText.color("Something went wrong, please try again", "RED"));
            return false;
        }
    }

    @Override
    public boolean depositOrWithDraw(Client currentClient, String accountNumber, String amount, OperationType operationType) {
        // check if the client has an account with this "accountNumber"
        if (accountExist(currentClient,accountNumber)) {
            //get the account with this "accountNumber"
            Account account = currentClient.getAccounts().
                    stream()
                    .filter(element -> (element.getAccountNumber().equals(accountNumber)))
                    .findFirst()
                    .orElse(null);
            Operation operation = null;

            // check if the amount is a numeric value
            if(!Validators.isNumericValue(amount)){
                System.out.println(ColorText.color("Please enter a numeric value for the amount", "RED"));
                return false;
            }
            // check if the amount is a positive value
           if(!Validators.isPositive(Double.parseDouble(amount))){
               System.out.println(ColorText.color("Please enter a positive value for the amount", "RED"));
               return false;
           }

            if (operationType == OperationType.DEPOSIT) {
                account.setBalance(Double.parseDouble(amount) + account.getBalance());
                operation = new Operation(OperationType.DEPOSIT, Double.parseDouble(amount), account.getBalance());
            }
            if (operationType == OperationType.WITHDRAW) {
                if(account.getBalance() >= Double.parseDouble(amount)) {
                    account.setBalance(account.getBalance() - Double.parseDouble(amount));
                    operation = new Operation(OperationType.WITHDRAW, Double.parseDouble(amount), account.getBalance());
                }
                else {
                    System.out.println(ColorText.color("You can't withdraw funds beyond your accounts' balance", "RED"));
                    return false;
                }
            }

            List<Operation> operations = new ArrayList<>();

            if(account.getOperations() != null)  operations.addAll(account.getOperations());
            operations.add(operation);
            account.setOperations(operations);

            System.out.println(ColorText.color("The transaction went successfully", "GREEN"));
            return true;
        }
        else {
            System.out.println(ColorText.color("No such account with this number, make sure the account number is correct", "RED"));
            return false;
        }

    }

    @Override
    public List<Operation> getHistoryByAccount(Client currentClient, String accountNumber) {
        try{
            Account account = currentClient.getAccounts().
                    stream()
                    .filter(element -> (element.getAccountNumber().equals(accountNumber)))
                    .findFirst()
                    .orElse(null);
            if(account == null) {
                System.out.println(ColorText.color("Make sure the account number is correct", "RED"));
                return null;
            } else if (account.getOperations() == null) {
                System.out.println(ColorText.color("You made 0 transactions", "RED"));
                return null;
            }else{
                return account.getOperations();
            }
        }catch (Exception e){
            return null;
        }
    }

    public boolean accountExist(Client currentClient, String accountNumber){
        try{
            Account account = currentClient.getAccounts().
                    stream()
                    .filter(element -> (element.getAccountNumber().equals(accountNumber)))
                    .findFirst()
                    .orElse(null);
            if(account != null) return true;
            return false;
        }catch (Exception e){
            return false;
        }
    }

    public String generateAccountNumber() {
        String first5Digits = "07456"; // set a common first 5 digits for all accounts
        UUID uuid = UUID.randomUUID(); //returns 32 characters
        return first5Digits + uuid.toString().replace("-", "").substring(0, 11);
    }






}
