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


/*    public static void creatAccountSection(){
        System.out.println("* ───────────────────────────────────────────────────*");
        System.out.println("*                Create a user account               *");
        System.out.println("* ───────────────────────────────────────────────────*");

        Scanner scanner = new Scanner(System.in);
        boolean ok = false;
        do {
            System.out.print("- Full Name : ");
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

            if(password.equals(passwordConf)){
                currentClient = clientService.createClient(fullName, email, password);
                ok = true;
                System.out.println(color("Account Created Successfully", "GREEN"));
            } else{
                System.out.println(color("Passwords Do Not Match", "RED"));
            }
        }while (!ok);
    }

    public static void loginSection(){
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

            if(clientService.signIn(currentClient, email, password)){
                ok = true;
                System.out.println(color("Logged In Successfully", "GREEN"));
            } else{
                System.out.println(color("Wrong Email or Password", "RED"));
            }
        }while (!ok);
    }

    public static void menuSection(){
        System.out.println("* ─────────────────────────────────────────────*");
        System.out.println("*    Hi ! "+ currentClient.getFullName()+"     *");
        System.out.println("* ─────────────────────────────────────────────*");
        Scanner scanner = new Scanner(System.in);
        int option = 1;
        while (option!=0){
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
                option = scanner.nextInt();
                switch (option){
                    case 1: createBankAccountSection(); break;
                    case 2: depositAndWhidrawlSection(); break;
                    case 3: accountStatementSection(); break;
                    case 4: showAccountsSection(); break;
                    case 0: exit(0);
                    default: System.out.println(color("Please enter an integer value between 0 and 4", "RED"));
                }
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void createBankAccountSection(){
        System.out.println("* ───────────────────────────────────────*");
        System.out.println("*         Create a bank account          *");
        System.out.println("* ───────────────────────────────────────*");
        if(clientService.createAccount(currentClient)){
            System.out.println(color("your bank account was created Successfully", "GREEN"));
            System.out.println("* --------------------------*");
            System.out.println("*     Account Details       *");
            System.out.println("* --------------------------*");
            System.out.println("Account number : "+currentClient.getAccounts().get(currentClient.getAccounts().size() - 1).getAccountNumber());
            System.out.println("Balance : "+currentClient.getAccounts().get(currentClient.getAccounts().size() - 1).getBalance());
            System.out.println("Creation date : "+currentClient.getAccounts().get(currentClient.getAccounts().size() - 1).getDate());
            System.out.println("* --------------------------*");
        }else System.out.println(color("Something went wrong, please try again", "RED"));

        backToMenu();
        }

    public static void depositAndWhidrawlSection(){
        System.out.println("* ───────────────────────────────────────*");
        System.out.println("*           DEPOSIT OR WHIDRAWL           ");
        System.out.println("* ───────────────────────────────────────*");
        if(currentClient.getAccounts() == null){
            System.out.println("* ----------------------------------------------------------------------------*");
            System.out.println("*  You have no accounts yet, create an account to make a deposit or whidrawl  *");
            System.out.println("* ----------------------------------------------------------------------------*");
        }else {
            // display the accounts numbers to help the client choose the correct account number
            displayAccountsNumbers();
            Scanner scannerForInt = new Scanner(System.in);
            Scanner scanner = new Scanner(System.in);
            boolean ok = false;
            do {
                System.out.print("- To deposit enter 1, to whidrawl enter 2 (0 to go back to the menu): ");
                int operation = scannerForInt.nextInt();
                System.out.print("- Enter the account number : ");
                String accountNumber = scanner.nextLine();
                System.out.print("- Enter the amount         : ");
                String amount = scanner.nextLine();
                if(clientService.accountExist(currentClient,accountNumber)) {
                    boolean transIsSuccessful = false;
                    switch (operation) {
                        case 1: transIsSuccessful = clientService.depositOrWithDraw(currentClient, accountNumber, amount, OperationType.DEPOSIT); break;
                        case 2: transIsSuccessful = clientService.depositOrWithDraw(currentClient, accountNumber, amount, OperationType.WITHDRAW); break;
                        case 0 : backToMenu();
                    }
                    if (transIsSuccessful) {
                        ok = true;
                        System.out.println(color("The transaction went successfully", "GREEN"));
                    } else if (operation != 1 || operation != 2) {
                        System.out.println(color("Please enter either 1 or 2", "RED"));
                    } else {
                        System.out.println(color("Something went wrong, make sure your information is correct", "RED"));
                    }
                }else System.out.println(color("Make sure the account number is correct", "RED"));
            }while (!ok);

        }
        backToMenu();
    }

    public static void accountStatementSection(){
        System.out.println("* ───────────────────────────────────────*");
        System.out.println("*            ACCOUNT STATEMENT            ");
        System.out.println("* ───────────────────────────────────────*");
        if(currentClient.getAccounts() == null){
            System.out.println("* ----------------------------------------------------------------------------*");
            System.out.println("*  You have no accounts yet, create an account to make a deposit or whidrawl  *");
            System.out.println("* ----------------------------------------------------------------------------*");
        }else {
            // display the accounts numbers to help the client choose the correct account number
            displayAccountsNumbers();
            Scanner scanner = new Scanner(System.in);
            boolean ok = false;
            do {
                System.out.print("- Enter the account number : ");
                String accountNumber = scanner.nextLine();

                if(clientService.accountExist(currentClient,accountNumber)) {
                    System.out.println(color("OPERATION   |              DATE            |  AMOUNT  ", "BLUE"));
                    List<Operation> operations = clientService.getHistoryByAccount(currentClient, accountNumber);
                    if (operations != null) {
                        ok = true;
                        for (Operation operation : operations)
                            System.out.println(operation.getOperationType() + " | " + operation.getDate() + " | " + operation.getAmount());
                    } else System.out.println(color("You made 0 transactions", "RED"));
                }  else System.out.println(color("Make sure the account number is correct", "RED"));
            }while (!ok);

        }
        backToMenu();
    }

    public static void showAccountsSection(){
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
        backToMenu();
    }

    public static void backToMenu(){
        int option = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Click 1 if you want to return to the menu:");
        option = scanner.nextInt();
        if(option == 1) menuSection();
        else backToMenu();
    }

    public static void displayAccountsNumbers() {
        int index = 1;
        System.out.println("*         Your Accounts          *");
        for (Account account : currentClient.getAccounts()) {
            System.out.println("- Account " + index + " : " + account.getAccountNumber());
            System.out.println("* --------------------------*");
            index++;
        }
    }


    public static String color(String s, String color){
        String ANSI_RESET = "\u001B[0m";
        Map<String, String> colors = new HashMap<>();
        colors.put("BLACK", "\u001B[30m");
        colors.put("RED", "\u001B[31m");
        colors.put("GREEN", "\u001B[32m");
        colors.put("YELLOW", "\u001B[33m");
        colors.put("BLUE", "\u001B[34m");
        colors.put("PURPLE", "\u001B[35m");
        colors.put("CYAN", "\u001B[36m");
        colors.put("WHITE", "\u001B[37m");
        return colors.get(color)+s+ANSI_RESET;
    }*/

}
