package services;

import entities.Account;
import entities.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenerateAccountTest {

    private ClientServiceImp clientService;

    @BeforeEach
    void setUp() {
        clientService = new ClientServiceImp();
    }

    @Test
    void should_Generate_A_16_Character_Account_Number() {
        //given
        String accountNumber;
        //when
        accountNumber = clientService.generateAccountNumber();
        //then
        assertEquals(16, accountNumber.length());
    }
}
