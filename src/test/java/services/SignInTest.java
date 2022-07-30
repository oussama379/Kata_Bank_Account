package services;

import entities.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignInTest {
    private ClientServiceImp clientService;
    private Client currentClient;

    @BeforeEach
    void setUp() {
        clientService = new ClientServiceImp();
        currentClient = new Client("Ulysses Everett", "ulysses@gmail.com", "Ulysses1234");
    }


    @Test
    void should_SignIn() {
        //given
        //currentClient Defined in setUp()
        //when
        boolean result = clientService.signIn(currentClient, "ulysses@gmail.com", "Ulysses1234");
        //then
        assertTrue(result);
    }

    @Test
    void should_Not_SignIn_When_Wrong_Email() {
        //given
        //currentClient Defined in setUp()
        //when
        boolean result = clientService.signIn(currentClient, "ulysses1@gmail.com", "Ulysses1234");
        //then
        assertFalse(result);
    }

    @Test
    void should_Not_SignIn_When_Wrong_Password() {
        //given
        //currentClient Defined in setUp()
        //when
        boolean result = clientService.signIn(currentClient, "ulysses1@gmail.com", "Ulysses12345");
        //then
        assertFalse(result);

    }
}
