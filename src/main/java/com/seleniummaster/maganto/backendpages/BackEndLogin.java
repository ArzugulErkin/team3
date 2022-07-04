package com.seleniummaster.maganto.backendpages;

import com.seleniummaster.maganto.utility.ApplicationConfig;
import com.seleniummaster.maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BackEndLogin {
    WebDriver driver;
    TestUtility testUtility;
    String configFile = "config.properties";
    String password = ApplicationConfig.readFromConfigProperties(configFile, "password");

    public BackEndLogin(WebDriver driver) {
        this.driver = driver;
        testUtility = new TestUtility(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "login[username]")
    WebElement userNameField;
    @FindBy(name = "login[password]")
    WebElement passwordField;
    @FindBy(css = ".form-button")
    WebElement loginButton;
    @FindBy(css = ".link-logout")
    WebElement logoutButton;

    public void customerPageLogin() {
        testUtility.waitForElementPresent(userNameField);
        userNameField.sendKeys(ApplicationConfig.readFromConfigProperties(configFile, "cususername"));
        testUtility.waitForElementPresent(passwordField);
        passwordField.sendKeys(password);
        testUtility.waitForElementPresent(loginButton);
        loginButton.click();
    }

    public void catalogPageLogin() {
        testUtility.waitForElementPresent(userNameField);
        userNameField.sendKeys(ApplicationConfig.readFromConfigProperties(configFile, "catusername"));
        testUtility.waitForElementPresent(passwordField);
        passwordField.sendKeys(password);
        testUtility.waitForElementPresent(loginButton);
        loginButton.click();
    }

    public boolean VerifyLoginSuccessfully() {
        testUtility.waitForElementPresent(logoutButton);
        if (logoutButton.isDisplayed()) {
            System.out.println("Backend Login Successfully!!");
            return true;
        } else {
            System.out.println("Backend Login failed!!!");
            return false;
        }
    }
}
