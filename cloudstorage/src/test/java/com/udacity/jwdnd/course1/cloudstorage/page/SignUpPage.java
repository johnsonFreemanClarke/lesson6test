package com.udacity.jwdnd.course1.cloudstorage.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage {

    @FindBy(id = "inputFirstName")
    private WebElement firstNameInput;

    @FindBy(id = "inputLastName")
    private WebElement lastNameInput;
    @FindBy(id = "inputUsername")
    private WebElement usernameInput;
    @FindBy(id = "inputPassword")
    private WebElement passwordInput;
//    @FindBy(id = "signupSuccess")
//    private WebElement signupSuccessText;
//    @FindBy(id = "signupErrorMsg")
//    private WebElement signupErrorMsgText;
    @FindBy(id = "signupBtn")
    private WebElement signUpBtn;

    public SignUpPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
    public void signup(String firstName, String lastName, String username, String password) {
        this.firstNameInput.sendKeys(firstName);
        this.lastNameInput.sendKeys(lastName);
        this.usernameInput.sendKeys(username);
        this.passwordInput.sendKeys(password);
        signUpBtn.click();
    }

    public boolean signUpRun(WebDriver driver) throws InterruptedException{
        signup("j", "j", "j", "j");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement signUpSuccessTxt = null;
        try {
            signUpSuccessTxt = wait.until(webDriver -> webDriver.findElement(By.id("signupSuccess")));
        }catch(Exception e){ //find out what is exception...
            //doing if catch occured ... use log to print error statement.
        }
        return signUpSuccessTxt != null;
    }

}
