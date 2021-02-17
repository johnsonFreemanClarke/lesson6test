package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.data.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.utils.TestingConstant;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTest {

    @LocalServerPort
    private int port;
    private static User testUser;
    private static int count = 0;
    private static final Logger log = LoggerFactory.getLogger(com.udacity.jwdnd.course1.cloudstorage.service.UserServiceTest.class);

    @Autowired
    private UserService service;
    @Autowired
    private UserMapper mapper;

    @BeforeAll
    static void beforeAll() {

    }

    @BeforeEach
    public void beforeEach() {
        testUser = TestingConstant.getUser();
    }

    @AfterEach
    public void afterEach() {
        mapper.deleteAll();
    }

    @Test
    public void isUsernameAvailableTest(){
        int expected = 1;
        int result = mapper.insertUser(testUser);
        Assertions.assertEquals(expected, result);
        boolean bolResult = service.isUsernameAvailable(testUser.getUsername());
        Assertions.assertFalse(bolResult);
    }

    @Test
    public void createUserTest(){
        int expected = 1;
        int result = service.createUser(testUser);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void deleteUserTest(){
        int expected = 1;
        int result = mapper.insertUser(testUser);
        Assertions.assertEquals(expected, result);
        result = service.deleteUser(testUser);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getUserTest(){
        int expected = 1;
        int result = mapper.insertUser(testUser);
        Assertions.assertEquals(expected, result);
        User temp = service.getUser(testUser.getUsername());
        Assertions.assertEquals(testUser.getUsername(), temp.getUsername());
    }

    @Test
    public void deleteAllTest(){
        int expected = 3;
        for(int i = 0; i< expected; i++) {
            mapper.insertUser(testUser);
        }
        int result = service.deleteAll();
        Assertions.assertEquals(expected, result);
    }



}