package com.seleniummaster.maganto.backendpages.storepages;

import com.seleniummaster.maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class StoreProductPage {
    WebDriver driver;
    TestUtility testUtility;
    @FindBy(css = "select#store_switcher")
    WebElement chooseStoreViewDropDown;
    @FindBy(css = "button[title='Add Product']")
    WebElement addProductButton;
    @FindBy(css = "select#attribute_set_id")
    WebElement attributeSetDropDown;
    @FindBy(id = "continue_button")
    WebElement continueButton;
    @FindBy(id = "product[name]")
    WebElement nameField;
    @FindBy(css = "textarea#description")
    WebElement descriptionField;
    @FindBy(css = "textarea#short_description")
    WebElement shortDescriptionFiled;
    @FindBy(xpath = "//input[@name='product[sku]']")
    WebElement SKUField;
    @FindBy(css = "input#weight")
    WebElement weightFiled;
    @FindBy(css = "select#status")
    WebElement statusDropDown;
    @FindBy(css = "select#visibility")
    WebElement visibilityDropdown;
    @FindBy(css = "a[name='group_50']")
    WebElement clothingLink;
    @FindBy(css = "select#apparel_type")
    WebElement typeDropDown;
    @FindBy(css = "a[title='Prices']")
    WebElement pricesLink;
    @FindBy(css = "#price")
    WebElement priceField;
    @FindBy(xpath = "//select[@name='product[tax_class_id]']")
    WebElement taxClassDropDown;
    @FindBy(xpath = "//a[@title='Websites']")
    WebElement websiteLink;
    @FindBy(css = "#product_website_57")
    WebElement websiteCheckbox;
    @FindBy(xpath = "//a[@name='inventory']")
    WebElement inventoryLink;
    @FindBy(css = "#inventory_qty")
    WebElement qtyFiled;
    @FindBy(css = "select#inventory_stock_availability")
    WebElement stockAvailabilityDropDown;
    @FindBy(css = "button[title='Save']")
    WebElement saveButton;
    @FindBy(css = ".success-msg>ul li span")
    WebElement addProductSuccessMessage;


    public StoreProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        testUtility = new TestUtility(driver);
    }

    public void addProduct(String name, String description, String shortDescription, String SKU,String weight ,String price, String qty) {
        testUtility.waitForElementPresent(chooseStoreViewDropDown);
        Select select = new Select(chooseStoreViewDropDown);
        select.selectByValue("60");
        testUtility.waitForElementPresent(addProductButton);
        addProductButton.click();
        testUtility.waitForElementPresent(attributeSetDropDown);
        Select select1 = new Select(attributeSetDropDown);
        select1.selectByValue("13");
        testUtility.waitForElementPresent(continueButton);
        continueButton.click();
        testUtility.waitForElementPresent(nameField);
        nameField.sendKeys(name);
        testUtility.waitForElementPresent(descriptionField);
        descriptionField.sendKeys(description);
        testUtility.waitForElementPresent(shortDescriptionFiled);
        shortDescriptionFiled.sendKeys(shortDescription);
        testUtility.waitForElementPresent(SKUField);
        SKUField.sendKeys(SKU);
        testUtility.waitForElementPresent(weightFiled);
        weightFiled.sendKeys(weight);
        testUtility.waitForElementPresent(statusDropDown);
        Select select2 = new Select(statusDropDown);
        select2.selectByValue("1");
        testUtility.waitForElementPresent(visibilityDropdown);
        Select select3 = new Select(visibilityDropdown);
        select3.selectByValue("4");
        testUtility.waitForElementPresent(clothingLink);
        clothingLink.click();
        testUtility.waitForElementPresent(typeDropDown);
        Select select4 = new Select(typeDropDown);
        select4.selectByValue("36");
        testUtility.waitForElementPresent(pricesLink);
        pricesLink.click();
        testUtility.waitForElementPresent(priceField);
        priceField.sendKeys(price);
        testUtility.waitForElementPresent(taxClassDropDown);
        Select select5=new Select(taxClassDropDown);
        select5.selectByValue("2");
        testUtility.waitForElementPresent(websiteLink);
        websiteLink.click();
        testUtility.waitForElementPresent(websiteCheckbox);
        websiteCheckbox.click();
        testUtility.waitForElementPresent(inventoryLink);
        inventoryLink.click();
        testUtility.waitForElementPresent(qtyFiled);
        qtyFiled.sendKeys(qty);
        testUtility.waitForElementPresent(stockAvailabilityDropDown);
        stockAvailabilityDropDown.click();
        testUtility.waitForElementPresent(saveButton);
        saveButton.click();

    }

    public boolean verifyAddProduct() {
        testUtility.waitForElementPresent(addProductSuccessMessage);
        if (driver.getPageSource().contains(addProductSuccessMessage.getText())){
            System.out.println("Store Manager can Add Product Test is Passed!!!");
            return true;
        }else {
            System.out.println("Store Manager can Add Product Test is Failed!!!");
            return false;
        }
    }
}