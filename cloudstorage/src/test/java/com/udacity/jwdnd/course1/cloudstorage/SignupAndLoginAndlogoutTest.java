package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.view.SignupForm;
import com.udacity.jwdnd.course1.cloudstorage.page.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.page.SignUpPage;
import com.udacity.jwdnd.course1.cloudstorage.utils.TestingConstant;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SignupAndLoginAndlogoutTest {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    public static String BASEURL;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
        BASEURL = TestingConstant.LOCALHOST + port;
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }
    @AfterAll
    static void aferAll(){

    }

    @Test
    @Order(1)
    public void testLoginToSignup() throws InterruptedException{
        driver.get(SignupAndLoginAndlogoutTest.BASEURL + TestingConstant.LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.signUp(driver);
        Thread.sleep(3000);
        SignUpPage signupPage = new SignUpPage(driver);
        //signupPage.signup("first", "last", "username", "pwd" );
        //first time, it should return true;
        Assertions.assertTrue(signupPage.signUpRun(driver));
        //Assertions.assertFalse(signupPage.signUpRun(driver));
    }

    @Test
    @Order(2)
    public void testLoginToSignup2() throws InterruptedException{
        driver.get(SignupAndLoginAndlogoutTest.BASEURL + TestingConstant.LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.signUp(driver);
        Thread.sleep(3000);
        SignUpPage signupPage = new SignUpPage(driver);
        //signupPage.signup("first", "last", "username", "pwd" );
        //first time, it should return true;
        Assertions.assertFalse(signupPage.signUpRun(driver));
        //Assertions.assertFalse(signupPage.signUpRun(driver));
    }

}
