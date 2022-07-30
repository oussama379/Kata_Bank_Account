package services;

import entities.Account;
import entities.Client;
import entities.OperationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DepositAndWithdrawalTest {

    private ClientServiceImp clientService;
    private Client currentClient;


    @BeforeEach
    void setUp() {
        clientService = new ClientServiceImp();
        currentClient = new Client("Ulysses Everett", "ulysses@gmail.com", "Ulysses1234");
        currentClient.setAccounts(new ArrayList<>(Arrays.asList(new Account("0745609876545671"))));
    }

    @Test
    void should_Make_A_Deposit_Or_Withdrawal() {
        //given
        //currentClient Defined in setUp() with the account 0745609876545671
        //when
        boolean depositResult = clientService.depositOrWithDraw(currentClient, "0745609876545671", String.valueOf(12000), OperationType.DEPOSIT);
        boolean withdrawalResult = clientService.depositOrWithDraw(currentClient, "0745609876545671", String.valueOf(12000), OperationType.WITHDRAW);
        //then
        assertAll(
                () -> assertTrue(depositResult),
                () -> assertTrue(withdrawalResult)
        );
    }

    @Test
    void should_Not_Make_A_Withdrawal_When_Balance_Is_Less_Then_Amount() {
        //given
        //currentClient Defined in setUp() with the account 0745609876545671
        //when
        clientService.depositOrWithDraw(currentClient, "0745609876545671", String.valueOf(12000), OperationType.DEPOSIT);
        boolean withdrawalResult = clientService.depositOrWithDraw(currentClient, "0745609876545671", String.valueOf(22000), OperationType.WITHDRAW);
        //then
        assertFalse(withdrawalResult);
    }

    @Test
    void should_Not_Make_A_Deposit_Or_Withdrawal_When_Amount_Is_Not_Double() {
        //given
        //currentClient Defined in setUp() with the account 0745609876545671
        //when
        boolean depositResult = clientService.depositOrWithDraw(currentClient, "0745609876545671", "12000b", OperationType.DEPOSIT);
        boolean withdrawalResult = clientService.depositOrWithDraw(currentClient, "0745609876545671", "12000b", OperationType.WITHDRAW);
        //then
        assertAll(
                () -> assertFalse(depositResult),
                () -> assertFalse(withdrawalResult)
        );
    }

    @Test
    void should_Not_Make_A_Deposit_Or_Withdrawal_When_AccountNumber_Is_Wrong() {
        //given
        //currentClient Defined in setUp() with the account 0745609876545671
        //when
        boolean depositResult = clientService.depositOrWithDraw(currentClient, "0745609876545675", String.valueOf(12000), OperationType.DEPOSIT);
        boolean withdrawalResult = clientService.depositOrWithDraw(currentClient, "0745609876545675", String.valueOf(12000), OperationType.WITHDRAW);
        //then
        assertAll(
                () -> assertFalse(depositResult),
                () -> assertFalse(withdrawalResult)
        );
    }
    @Test
    void should_Not_Make_A_Deposit_Or_Withdrawal_When_Amount_Is_Negative() {
        //given
        //currentClient Defined in setUp() with the account 0745609876545671
        //when
        boolean depositResult = clientService.depositOrWithDraw(currentClient, "0745609876545671", String.valueOf(-12000), OperationType.DEPOSIT);
        boolean withdrawalResult = clientService.depositOrWithDraw(currentClient, "0745609876545671", String.valueOf(-12000), OperationType.WITHDRAW);
        //then
        assertAll(
                () -> assertFalse(depositResult),
                () -> assertFalse(withdrawalResult)
        );
    }

}
