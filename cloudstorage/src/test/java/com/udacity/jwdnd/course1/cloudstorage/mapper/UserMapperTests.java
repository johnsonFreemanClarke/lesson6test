package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.data.User;
import com.udacity.jwdnd.course1.cloudstorage.utils.TestingConstant;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserMapperTests {

    @LocalServerPort
    private int port;
    private static User testUser;
    private static int count = 0;
    private static final Logger log = LoggerFactory.getLogger(UserMapperTests.class);

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
    @Order(1)
    public void testInsertUser() {
        int expectingResult = 1;
        int result = mapper.insertUser(testUser);
        count++;
        log.debug("count : " + count);
        Assertions.assertEquals(expectingResult, result);
        log.debug("final count : " + count);
    }

    @Test
    public void testDeleteUser() {
        int expectingResult = 0;
        int result = mapper.delete(testUser.getUserId());
        log.debug("count : " + count);
        Assertions.assertEquals(expectingResult, result);
    }

    @Test
    public void testDeleteAllUser() {
        int expectingResult = 3;
        for(int i =  0; i<expectingResult; i++) {
            mapper.insertUser(testUser);
            count++;
        }
        int result = mapper.deleteAll();
        log.debug("count : " + count);
        Assertions.assertEquals(expectingResult, result);
    }

    @Test
    public void testGetAllUser() {
        int expectingResult = 3;
        for(int i =  0; i<expectingResult; i++) {
            mapper.insertUser(testUser);
            count++;
        }
        List<User> allUserList = mapper.getAllUser();
        int result = allUserList.size();
        log.debug("count : " + count);
        Assertions.assertEquals(expectingResult, result);
    }

    @Test
    public void testdeleteById(){
        int expectingResult = 3;
        for(int i =  0; i<expectingResult; i++) {
            mapper.insertUser(testUser);
            count++;
        }
        List<User> allUserList = mapper.getAllUser();
        int result = allUserList.size();
        User lastUser = allUserList.get(result-1);
        int result2 = mapper.delete(lastUser.getUserId());
        Assertions.assertEquals(1, result2);
    }


}


