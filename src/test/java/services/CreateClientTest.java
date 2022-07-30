package services;

import entities.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CreateClientTest {

        private ClientServiceImp clientService;


        @BeforeEach
        void setUp() {
            clientService = new ClientServiceImp();
        }

        @Test
        void should_Create_Client() {
            //given
            String username = "Ulysses Everett";
            String email = "ulysses@gmail.com";
            String password = "Ulysses1234";
            String passwordConf = "Ulysses1234";
            //when
            Client client = clientService.createClient(username, email, password, passwordConf);
            //then
            assertNotNull(client);
        }

        @Test
        void should_Not_Create_Client_When_Passwords_Do_Not_Match() {
            //given
            String username = "Ulysses Everett";
            String email = "ulysses@gmail.com";
            String password = "Ulysses1234";
            String passwordConf = "@Ulysses1234";
            //when
            Client client = clientService.createClient(username, email, password, passwordConf);
            //then
            assertNull(client);
        }

        @Test
        void should_Not_Create_Client_When_Email_Not_Valid() {
            //given
            String username = "Ulysses Everett";
            String email = "ulyssesgmail.com";
            String password = "Ulysses1234";
            String passwordConf = "Ulysses1234";
            //when
            Client client = clientService.createClient(username, email, password, passwordConf);
            //then
            assertNull(client);
        }

}
