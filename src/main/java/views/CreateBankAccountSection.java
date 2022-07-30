package views;

import config.ColorText;
import entities.Account;
import entities.Client;
import services.ClientServiceImp;

public class CreateBankAccountSection {
    private static ClientServiceImp clientService = new ClientServiceImp();
    public static void createBankAccountSection(Client currentClient){
        System.out.println("* ───────────────────────────────────────*");
        System.out.println("*         Create a bank account          *");
        System.out.println("* ───────────────────────────────────────*");

        if(clientService.createBankAccount(currentClient)){
            // get the last added account to the users' list of accounts
            Account lastAccount = currentClient.getAccounts().get(currentClient.getAccounts().size() - 1);

            System.out.println("* --------------------------*");
            System.out.println("*     Account Details       *");
            System.out.println("* --------------------------*");
            System.out.println("Account number : "+lastAccount.getAccountNumber());
            System.out.println("Balance : "+lastAccount.getBalance());
            System.out.println("Creation date : "+lastAccount.getDate());
            System.out.println("* --------------------------*");
        }
        SharedViews.backToMenu(currentClient);
    }
}
