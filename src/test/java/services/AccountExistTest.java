package services;

import entities.Account;
import entities.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountExistTest {

    private ClientServiceImp clientService;
    private Client currentClient;


    @BeforeEach
    void setUp() {
        clientService = new ClientServiceImp();
        currentClient = new Client("Ulysses Everett", "ulysses@gmail.com", "Ulysses1234");
        currentClient.setAccounts(new ArrayList<>(Arrays.asList(new Account("0745609876545671"))));
    }

    @Test
    void should_find_Bank_Account() {
        //given
        //currentClient Defined in setUp() with the account 0745609876545671
        //when
        boolean result = clientService.accountExist(currentClient, "0745609876545671");
        //then
        assertTrue(result);

    }
    @Test
    void should_Not_find_Bank_Account_When_Wrong_Account_Number() {
        //given
        //currentClient Defined in setUp() with the account 0745609876545671
        //when
        boolean result = clientService.accountExist(currentClient, "0745609876545679");
        //then
        assertFalse(result);
    }

    @Test
    void should_Not_find_Bank_Account_When_Theres_No_Bank_Accounts() {
        //given
        Client newCurrentClient = new Client("Ulysses Everett", "ulysses@gmail.com", "Ulysses1234");
        //when
        boolean result = clientService.accountExist(newCurrentClient, "0745609876545679");
        //then
        assertFalse(result);
    }
}
