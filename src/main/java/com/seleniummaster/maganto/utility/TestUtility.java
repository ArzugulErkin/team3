package com.seleniummaster.maganto.utility;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUtility{
    private int timeout=Integer.parseInt(ApplicationConfig.readFromConfigProperties(
            "config.properties","timeout"
    ));

    WebDriver driver;

    Faker faker=new Faker();

    public TestUtility(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementPresent(WebElement element){
        WebDriverWait wait=new WebDriverWait(driver,timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public String generateFirstName(){
        String firstName=faker.name().firstName();
        return firstName;
    }

    public String generateLastName(){
        String lastName=faker.name().lastName();
        return lastName;
    }
    public String generateMiddleName(){
        String middleName=faker.name().nameWithMiddle();
        return middleName;
    }

    public String generateStreetAddress(){
        String streetAddress=faker.address().streetAddress();
        return streetAddress;
    }

    public String generateCityName(){
        String cityName=faker.address().cityName();
        return cityName;
    }

    public  String generateCountryName(){
        String countryName=faker.address().country();
        return countryName;
    }

    public String generateStateName(){
        String stateName=faker.address().state();
        return stateName;
    }

    public String generateZipCode(){
        String zinCode=faker.address().zipCode();
        return zinCode;
    }

    public String generateTelephoneNumber(){
        String telephoneNumber=faker.phoneNumber().phoneNumber();
        return telephoneNumber;
    }

    public void waitForAlertPresent(){
        WebDriverWait wai=new WebDriverWait(driver,timeout);
        wai.until(ExpectedConditions.alertIsPresent());
    }

    public void sleep(int second){
        try {
            Thread.sleep(second*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
