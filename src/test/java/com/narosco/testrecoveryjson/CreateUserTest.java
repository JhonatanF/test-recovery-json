package com.narosco.testrecoveryjson;

import com.narosco.testrecoveryjson.base.PayloadTest;
import com.narosco.testrecoveryjson.user.UserDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CreateUserTest extends BaseRealTest {

    UserDTO mockCreateUser;

    @Before
    public void setUp() {
        mockCreateUser = readJsonResource(PayloadTest.CREATE_USER_MOCK, UserDTO.class);
    }


    @Test
    public void testCreateUser() {
        var user = new UserDTO("Jhonatan Narosco", 18, "Male", "jhonatan@narosco.com");

        System.out.println("user : " + user);


        assertEquals(user.getName(), mockCreateUser.getName());
    }

    @Test
    public void testLeituraJson() {
        var user = new UserDTO("Jhonatan Narosco", 18, "Male", "jhonatan@narosco.com");

        System.out.println("user : " + user);


        assertEquals(user.getName(), mockCreateUser.getName());
    }
}
