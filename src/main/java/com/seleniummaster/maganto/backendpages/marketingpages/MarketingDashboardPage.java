package com.seleniummaster.maganto.backendpages.marketingpages;

import com.seleniummaster.maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MarketingDashboardPage {
    WebDriver driver;
    TestUtility testUtility;
    Actions actions;

    public MarketingDashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        testUtility=new TestUtility(driver);
        actions=new Actions(driver);
    }

    @FindBy(xpath = "//span[contains(text(),'Catalog')]")
    WebElement catalogLink;
    @FindBy(xpath = "//span[text()='Newsletter']")
    WebElement newsletterLink;
    @FindBy(xpath = "//span[text()='Newsletter Templates']")
    WebElement newsletterTemplatesLink;
    @FindBy(xpath = "//span[contains(text(),'Reviews and Ratings')]")
    WebElement reviewAndRatingsLink;
    @FindBy(xpath = "//span[contains(text(),'Customer Reviews')]")
    WebElement customerReviewsLink;
    @FindBy(xpath = "//span[contains(text(),'Pending Reviews')]")
    WebElement pendingReviews;
    @FindBy(xpath = "//span[contains(text(),'All Reviews')]")
    WebElement allReviewsLink;

    public void clickOnPendingReviewsLink() {
        testUtility.waitForElementPresent(catalogLink);
        actions.moveToElement(catalogLink).click().perform();
        testUtility.waitForElementPresent(reviewAndRatingsLink);
        actions.moveToElement(reviewAndRatingsLink).click().perform();
        testUtility.waitForElementPresent(customerReviewsLink);
        actions.moveToElement(customerReviewsLink).click().perform();
        testUtility.waitForElementPresent(pendingReviews);
        pendingReviews.click();
    }

    public void clickOnNewsletterTemplatesLink() {
        testUtility.waitForElementPresent(newsletterLink);
        actions.moveToElement(newsletterLink).perform();
        testUtility.waitForElementPresent(newsletterTemplatesLink);
        newsletterTemplatesLink.click();
    }
    public void clickOnAllReviewsLink(){
        testUtility.waitForElementPresent(catalogLink);
        actions.moveToElement(catalogLink).click().perform();
        testUtility.waitForElementPresent(reviewAndRatingsLink);
        actions.moveToElement(reviewAndRatingsLink).click().perform();
        testUtility.waitForElementPresent(customerReviewsLink);
        actions.moveToElement(customerReviewsLink).click().perform();
        testUtility.waitForElementPresent(allReviewsLink);
        allReviewsLink.click();
    }

}