package services;

import entities.Account;
import entities.Client;
import entities.Operation;
import entities.OperationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class GetHistoryTest {

    private ClientServiceImp clientService;
    private Client currentClient;
    private Account account;


    @BeforeEach
    void setUp() {
        clientService = new ClientServiceImp();
        currentClient = new Client("Ulysses Everett", "ulysses@gmail.com", "Ulysses1234");
        account = new Account("0745609876545671");
        currentClient.setAccounts(new ArrayList<>(Arrays.asList(account)));
    }


    @Test
    void should_getHistoryByAccount() {
        //given
        //currentClient Defined in setUp() with the account 0745609876545671
        account.setBalance(100 + account.getBalance());
        account.setOperations(Arrays.asList(new Operation(OperationType.DEPOSIT, 100, account.getBalance())));

        Operation expected = new Operation(OperationType.DEPOSIT, 100, account.getBalance());

        //when
        List<Operation> result = clientService.getHistoryByAccount(currentClient, "0745609876545671");
        //then
        assertAll(
                () -> assertEquals(expected.getOperationType(), result.get(0).getOperationType()),
                () -> assertEquals(expected.getAmount(), result.get(0).getAmount()),
                () -> assertEquals(expected.getDate(), result.get(0).getDate())
        );
    }
    @Test
    void should_Not_getHistoryByAccount_When_History_Is_Empty() {
        //given
        //currentClient Defined in setUp() with the account 0745609876545671
        //when
        List<Operation> history = clientService.getHistoryByAccount(currentClient, "0745609876545671");
        //then
        assertNull(history);
    }
    @Test
    void should_Not_getHistoryByAccount_When_Account_Doesnt_Exist() {
        //given
        //currentClient Defined in setUp()
        //when
        List<Operation> history = clientService.getHistoryByAccount(currentClient, "0745609876545677");
        //then
        assertNull(history);
    }
}


