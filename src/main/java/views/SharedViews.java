package views;

import entities.Account;
import entities.Client;
import services.ClientServiceImp;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SharedViews {

    public static void backToMenu(Client currentClient){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Click enter to return to the menu:");
        scanner.nextLine();
        MenuSection.menuSection(currentClient);
    }

    public static void displayAccountsNumbers(Client currentClient) {
        int index = 1;
        System.out.println("*         Your Accounts          *");
        for (Account account : currentClient.getAccounts()) {
            System.out.println("- Account " + index + " : " + account.getAccountNumber());
            System.out.println("* --------------------------*");
            index++;
        }
    }

}
