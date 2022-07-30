package services;

import entities.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateBankAccountTest {


    private ClientServiceImp clientService;
    private Client currentClient;


    @BeforeEach
    void setUp() {
        clientService = new ClientServiceImp();
        currentClient = new Client("Ulysses Everett", "ulysses@gmail.com", "Ulysses1234");
    }

    @Test
    void should_Create_A_Bank_Account() {
        //given
        //currentClient Defined in setUp()
        //when
        boolean result = clientService.createBankAccount(currentClient);
        //then
        assertTrue(result);
    }

    @Test
    void should_Not_Create_A_Bank_Account_When_NullClient() {
        //given
        Client currentClient = null;
        //when
        boolean result = clientService.createBankAccount(currentClient);
        //then
        assertFalse(result);
    }
}
