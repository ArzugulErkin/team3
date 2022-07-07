package com.seleniummaster.maganto.backendpages.catalogpages;

import com.seleniummaster.maganto.utility.TestDataHolder;
import com.seleniummaster.maganto.utility.TestUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class SubCategoriesPage {
    WebDriver driver;
    TestUtility testUtility;
    Actions actions;

    public SubCategoriesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        testUtility=new TestUtility(driver);
        actions=new Actions(driver);
    }

    @FindBy(css = "button[title='Add Subcategory']")
    WebElement addSubcategoryButton;

    @FindBy(css = "input[name='general[name]']")
    WebElement nameField;

    @FindBy(css = "#group_4is_active")
    WebElement isActiveDropDown;

    @FindBy(css ="textarea#group_4description")
    WebElement descriptionField;

    @FindBy(css = ".success-msg>ul li span")
    WebElement successMessages;

    @FindBy(xpath = "//span[text()='Save Category']")
    WebElement saveCategory;


    public void addSubCategories (TestDataHolder testDataHolder) {
        WebElement existingRootCategories=driver.findElement(By.xpath(String.format("//span[contains(text(),'%s (0)')]",testDataHolder.getRootCategoryName())));
        testUtility.waitForElementPresent(existingRootCategories);
        existingRootCategories.click();
        testUtility.sleep(2);
        testUtility.waitForElementPresent(addSubcategoryButton);
        actions.moveToElement(addSubcategoryButton).click().perform();
        testUtility.sleep(2);
        testUtility.waitForElementPresent(nameField);
        nameField.sendKeys(testDataHolder.getSubCategoriesName());
        testUtility.waitForElementPresent(isActiveDropDown);
        Select select = new Select(isActiveDropDown);
        select.selectByValue("1");
        testUtility.waitForElementPresent(descriptionField);
        descriptionField.sendKeys(testDataHolder.getSubCategoriesDescription());
        testUtility.waitForElementPresent(saveCategory);
        saveCategory.click();
    }

    public boolean verifyAddSubCategories(TestDataHolder testDataHolder){
        testUtility.waitForElementPresent(successMessages);
        if (successMessages.getText().contains("The category has been saved.")){
            System.out.printf("Catalog Manager Can Add Sub Categories (%s) Test Passed !",testDataHolder.getSubCategoriesName());
            return true;
        }else{
            System.out.printf("Catalog Manager Can Add Sub Categories (%s) Test Failed !",testDataHolder.getSubCategoriesName());
            return false;
        }
    }

    public void clickOnExistingSubCategories(TestDataHolder testDataHolder){
        WebElement existingSubCategories=driver.findElement(By.xpath(String.format("//span[contains(text(),'%s')]",testDataHolder.getSubCategoriesName())));
        testUtility.waitForElementPresent(existingSubCategories);
        existingSubCategories.click();
    }

    public void updateExistingSubCategories(TestDataHolder testDataHolder){
        clickOnExistingSubCategories(testDataHolder);
        testUtility.waitForElementPresent(isActiveDropDown);
        Select select = new Select(isActiveDropDown);
        select.selectByValue("0");
        testUtility.waitForElementPresent(saveCategory);

    }

    public boolean verifyUpdateExistingSubCategories(TestDataHolder testDataHolder){
        testUtility.waitForElementPresent(successMessages);
        if (successMessages.getText().contains("The category has been saved.")){
            System.out.printf("Catalog Manager Can Update Sub Categories (%s) Test Passed !",testDataHolder.getSubCategoriesName());
            return true;
        }else{
            System.out.printf("Catalog Manager Can Update Sub Categories (%s) Test Failed !",testDataHolder.getSubCategoriesName());
            return false;
        }
    }
}



