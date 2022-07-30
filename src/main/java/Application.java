import entities.Client;
import services.ClientServiceImp;
import views.CreateAccountSection;
import views.LoginSection;
import views.MenuSection;
public class Application {
    static Client currentClient;

    public static void main(String[] args) {
        Application application = new Application();
        System.out.println("* ──────────────────────────────────────────────────────────────────────────────────────────────*");
        System.out.println("*                                      Welcome to Gringotts Bank                                *");
        System.out.println("* ──────────────────────────────────────────────────────────────────────────────────────────────*");
        currentClient = CreateAccountSection.creatAccountSection();
        LoginSection.loginSection(currentClient);
        MenuSection.menuSection(currentClient);
    }
}
