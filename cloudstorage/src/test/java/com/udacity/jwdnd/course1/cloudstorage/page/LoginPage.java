package com.udacity.jwdnd.course1.cloudstorage.page;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id="signUpClick")
    private WebElement signUpClickLink;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void signUp(WebDriver driver){
        signUpClickLink.click();
    }
}
