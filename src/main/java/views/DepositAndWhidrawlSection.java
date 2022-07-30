package views;

import config.ColorText;
import config.Validators;
import entities.Client;
import entities.OperationType;
import services.ClientServiceImp;

import java.util.Scanner;

public class DepositAndWhidrawlSection {

    private static ClientServiceImp clientService = new ClientServiceImp();

    public static void depositAndWhidrawlSection(Client currentClient){
        System.out.println("* ───────────────────────────────────────*");
        System.out.println("*           DEPOSIT OR WHIDRAWL           ");
        System.out.println("* ───────────────────────────────────────*");
        if(currentClient.getAccounts() == null){
            System.out.println("* ----------------------------------------------------------------------------*");
            System.out.println("*  You have no accounts yet, create an account to make a deposit or whidrawl  *");
            System.out.println("* ----------------------------------------------------------------------------*");
        }else {
            // display the accounts numbers to help the client choose the correct account number
            SharedViews.displayAccountsNumbers(currentClient);
            Scanner scanner = new Scanner(System.in);
            int operation ;

            System.out.print("- Enter the account number : ");
            String accountNumber = scanner.nextLine();
            System.out.print("- Enter the amount         : ");
            String amount = scanner.nextLine();
            System.out.print("- To deposit enter 1, to whidrawl enter 2 (Click enter to go back to the menu): ");
            String input = scanner.nextLine();

            if(Validators.isNumericValue(input))  operation = Integer.parseInt(input);
            else operation = 9;

            switch (operation) {
                case 1 : clientService.depositOrWithDraw(currentClient, accountNumber, amount, OperationType.DEPOSIT); break;
                case 2 : clientService.depositOrWithDraw(currentClient, accountNumber, amount, OperationType.WITHDRAW); break;
                default : MenuSection.menuSection(currentClient);
            }
        }
        SharedViews.backToMenu(currentClient);
    }

}
