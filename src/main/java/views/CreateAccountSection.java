package views;

import config.ColorText;
import config.Validators;
import entities.Client;
import services.ClientServiceImp;

import java.util.Scanner;

public class CreateAccountSection {

    private static ClientServiceImp clientService = new ClientServiceImp();

    public static Client creatAccountSection(){

        System.out.println("* ───────────────────────────────────────────────────*");
        System.out.println("*                Create a user account               *");
        System.out.println("* ───────────────────────────────────────────────────*");

        Scanner scanner = new Scanner(System.in);
        Client currentClient ;
        boolean ok = false;
        do {
            System.out.print("- Username : ");
            String fullName = scanner.nextLine();
            System.out.println("*   ──────────────────   *");
            System.out.print("- Email : ");
            String email = scanner.nextLine();
            System.out.println("*   ──────────────────   *");
            System.out.print("- Password : ");
            String password = scanner.nextLine();
            System.out.println("*   ──────────────────   *");
            System.out.print("- Confirm Password : ");
            String passwordConf = scanner.nextLine();

            currentClient = clientService.createClient(fullName, email, password, passwordConf);

            if(currentClient != null)
                ok = true;

        }while (!ok);
        return currentClient;
    }


}
