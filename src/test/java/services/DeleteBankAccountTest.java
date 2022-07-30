package services;

import entities.Account;
import entities.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteBankAccountTest {


    private ClientServiceImp clientService;
    private Client currentClient;


    @BeforeEach
    void setUp() {
        clientService = new ClientServiceImp();
        currentClient = new Client("Ulysses Everett", "ulysses@gmail.com", "Ulysses1234");
        currentClient.setAccounts(new ArrayList<>(Arrays.asList(new Account("0745609876545671"))));
    }


    @Test
    void should_Delete_Bank_Account() {
        //given
        //currentClient Defined in setUp() with the account 0745609876545671
        //when
        boolean result = clientService.deleteBankAccount(currentClient, "0745609876545671");
        //then
        assertTrue(result);
    }

    @Test
    void should_Not_Delete_Bank_Account_When_Wrong_Account_Number() {
        //given
        //currentClient Defined in setUp() with the account 0745609876545671
        //when
        boolean result = clientService.deleteBankAccount(currentClient, "0745609876545670");
        //then
        assertFalse(result);
    }

}
