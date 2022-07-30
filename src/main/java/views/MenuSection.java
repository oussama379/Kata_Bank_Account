package views;

import config.ColorText;
import config.Validators;
import entities.Client;
import services.ClientServiceImp;

import java.util.Scanner;

import static java.lang.System.exit;

public class MenuSection {
    public static void menuSection(Client currentClient){
        System.out.println("* ─────────────────────────────────────────────*");
        System.out.println("*    Hi ! "+ currentClient.getusername()+"     *");
        System.out.println("* ─────────────────────────────────────────────*");
        Scanner scanner = new Scanner(System.in);
        int option = 1;
        while (option != 0){
            System.out.println("* --------------------------*");
            System.out.println("*           Menu            *");
            System.out.println("* --------------------------*");
            System.out.println("1- Create a bank account");
            System.out.println("2- Make a deposit or a withdrawal");
            System.out.println("3- See your account statements");
            System.out.println("4- See your accounts");
            System.out.println("0- Exit");
            System.out.println("* --------------------------*");
            try {
                System.out.print("Choose your option : ");
                String input = scanner.nextLine();

                if(Validators.isNumericValue(input))  option = Integer.parseInt(input);
                else option = 9;

                switch (option){
                    case 1: CreateBankAccountSection.createBankAccountSection(currentClient); break;
                    case 2: DepositAndWhidrawlSection.depositAndWhidrawlSection(currentClient); break;
                    case 3: AccountStatementSection.accountStatementSection(currentClient); break;
                    case 4: ShowAccountsSection.showAccountsSection(currentClient); break;
                    case 0: exit(0);
                    default: System.out.println(ColorText.color("Please enter an integer value between 0 and 4", "RED"));
                }
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
