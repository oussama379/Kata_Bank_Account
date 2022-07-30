package views;

import entities.Client;
import services.ClientServiceImp;

import java.util.Scanner;

public class LoginSection {
    private static ClientServiceImp clientService = new ClientServiceImp();
    public static void loginSection(Client currentClient){
        System.out.println("* ───────────────────────────────────────*");
        System.out.println("*                Login                   *");
        System.out.println("* ───────────────────────────────────────*");

        Scanner scanner = new Scanner(System.in);
        boolean ok = false;
        do {
            System.out.print("- Email : ");
            String email = scanner.nextLine();
            System.out.println("*   ──────────────────   *");
            System.out.print("- Password : ");
            String password = scanner.nextLine();

            if(clientService.signIn(currentClient, email, password))
                ok = true;

        }while (!ok);
    }

}
