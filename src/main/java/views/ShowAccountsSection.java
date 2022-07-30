package views;

import entities.Account;
import entities.Client;

public class ShowAccountsSection {
    public static void showAccountsSection(Client currentClient){
        System.out.println("* ───────────────────────────────────────*");
        System.out.println("*         Your Accounts                  *");
        System.out.println("* ───────────────────────────────────────*");
        if(currentClient.getAccounts() == null){
            System.out.println("* --------------------------*");
            System.out.println("*  You have no accounts yet *");
            System.out.println("* --------------------------*");
        }else {
            int index = 1;
            for (Account account : currentClient.getAccounts()) {
                System.out.println("- Account " + index + " : ");
                System.out.println("    Account number : "+account.getAccountNumber());
                System.out.println("    Balance : "+account.getBalance());
                System.out.println("    Creation date : "+account.getDate());
                System.out.println("* --------------------------*");
                index++;
            }
        }
        SharedViews.backToMenu(currentClient);
    }
}
