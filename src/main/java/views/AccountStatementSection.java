package views;

import entities.Client;
import entities.Operation;
import services.ClientServiceImp;
import config.ColorText;

import java.util.List;
import java.util.Scanner;

public class AccountStatementSection {
    private static ClientServiceImp clientService = new ClientServiceImp();
    public static void accountStatementSection(Client currentClient){
        System.out.println("* ───────────────────────────────────────*");
        System.out.println("*            ACCOUNT STATEMENT            ");
        System.out.println("* ───────────────────────────────────────*");
        if(currentClient.getAccounts() == null){
            System.out.println("* ----------------------------------------------------------------------------*");
            System.out.println("*  You have no accounts yet, create an account to make a deposit or whidrawl  *");
            System.out.println("* ----------------------------------------------------------------------------*");
        }else {
            // display the accounts numbers to help the client choose the correct account number
            SharedViews.displayAccountsNumbers(currentClient);
            Scanner scanner = new Scanner(System.in);

            System.out.print("- Enter the account number : ");
            String accountNumber = scanner.nextLine();

            List<Operation> operations = clientService.getHistoryByAccount(currentClient, accountNumber);
            if (operations != null) {
                System.out.println(ColorText.color("OPERATION |              DATE             | AMOUNT | BALANCE AFTER OPERATION", "BLUE"));
                for (Operation operation : operations)
                    System.out.println(operation.getOperationType() + "   | " + operation.getDate() + " | " + operation.getAmount() + " | " + operation.getBalanceAfterOperation());
            }

        }
        SharedViews.backToMenu(currentClient);
    }
}
