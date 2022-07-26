package com.seleniummaster.maganto.backendpages.salespages;

import com.seleniummaster.maganto.utility.TestUtility;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class SalesShipmentsPage {
    WebDriver driver;
    TestUtility testUtility;
    String text;

    @FindBy(xpath = "(//a[text()='View'])[18]")
    WebElement viewIcon;
    @FindBy(css = "textarea#history_comment")
    WebElement shipmentHistoryCommentField;
    @FindBy(css = "button[title='Submit Comment']")
    WebElement submitCommentButton;
    @FindBy(xpath = "//select[@name='carrier']")
    WebElement carrierDropDown;
    @FindBy(id = "tracking_number")
    WebElement numberField;
    @FindBy(xpath = "//button[@title='Add']")
    WebElement addButton;
    @FindBy(xpath = "(//button[@class='scalable save'])[2]")
    WebElement sendTrackingInformationButton;
    @FindBy(css = ".success-msg>ul li span")
    WebElement updateShipmentSuccessMessage;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SalesShipmentsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        testUtility=new TestUtility(driver);

    }

    public void updateShipmentsHistory(String commentHistory) {
        testUtility.waitForElementPresent(viewIcon);
        viewIcon.click();
        testUtility.waitForElementPresent(shipmentHistoryCommentField);
        shipmentHistoryCommentField.sendKeys(commentHistory);
        setText(commentHistory);
        testUtility.waitForElementPresent(submitCommentButton);
        submitCommentButton.click();
    }

    public boolean verifyUpdateShipmentsHistorySuccessfully(){
        WebElement text=driver.findElement(By.xpath("//li[contains(.,'"+getText()+"')]"));
        return text.getText().contains(getText());
    }

    public void updateTrackingInformation(String number){
        testUtility.waitForElementPresent(carrierDropDown);
        Select select = new Select(carrierDropDown);
        select.selectByValue("dhlint");
        testUtility.waitForElementPresent(numberField);
        numberField.sendKeys(number);
        testUtility.waitForElementPresent(addButton);
        addButton.click();
        testUtility.waitForElementPresent(sendTrackingInformationButton);
        sendTrackingInformationButton.click();
        testUtility.waitForAlertPresent();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public boolean verifyUpdateShipmentsTrackingInformationSuccessfully(){
        testUtility.waitForElementPresent(updateShipmentSuccessMessage);
        if (driver.getPageSource().contains(updateShipmentSuccessMessage.getText())){
            System.out.println("Sales Manager update shipments Test is Passed!!!");
            return true;
        }else {
            System.out.println("Sales Manager update shipments Test is failed!!!");
            return false;
        }
    }

}


