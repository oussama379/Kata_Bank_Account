package services;


import entities.Account;
import entities.Client;
import entities.Operation;
import entities.OperationType;

import java.util.List;

public interface ClientService {
    Client createClient(String username, String email, String password, String passwordConf);
    boolean signIn(Client client, String email, String password);
    boolean createBankAccount(Client client);
    boolean deleteBankAccount(Client client, String accountNumber);
    boolean depositOrWithDraw(Client client, String accountNumber, String amount, OperationType operationType);
    List<Operation> getHistoryByAccount(Client client, String accountNumber);
    String generateAccountNumber();

}
