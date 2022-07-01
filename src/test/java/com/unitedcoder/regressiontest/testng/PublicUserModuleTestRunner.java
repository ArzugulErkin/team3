package com.unitedcoder.regressiontest.testng;

import com.seleniummaster.maganto.frontendpages.AccountInformationPage;
import com.seleniummaster.maganto.frontendpages.LoginPage;
import com.seleniummaster.maganto.frontendpages.MyDashboardPage;
import com.seleniummaster.maganto.frontendpages.MyOrdersPage;
import com.seleniummaster.maganto.utility.ApplicationConfig;
import com.seleniummaster.maganto.utility.BasePage;
import com.seleniummaster.maganto.utility.TestResultListener;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

@Listeners(TestResultListener.class)
public class PublicUserModuleTestRunner extends BasePage {
    WebDriver driver;
    LoginPage loginPage;
    String config = "config.properties";
    MyDashboardPage dashboardPage;
    AccountInformationPage accountInformationPage;
    MyOrdersPage myOrdersPage;

    @BeforeClass
    public void setup(ITestContext context){
        driverSetup(ApplicationConfig.readFromConfigProperties(config,"puburl"));
        context.setAttribute("driver",driver);
        loginPage=new LoginPage(driver);
        loginPage.login();
        dashboardPage=new MyDashboardPage(driver);
        accountInformationPage=new AccountInformationPage(driver);
        myOrdersPage=new MyOrdersPage(driver);
    }

    @Test(description ="EditAccountInformation")
    public void EditAccountInformation(){
        dashboardPage.verifyLogin();
        dashboardPage.clickOnAccountInformationLink();
        accountInformationPage.editAccountInformation();
        Assert.assertTrue(accountInformationPage.verifyEditAccountInformation());
    }

    @Test(description = "A User Should be Able to View his/her Orders")
    public void viewOrders(){
        dashboardPage.clickOnMyOrdersLink();
        Assert.assertTrue(myOrdersPage.viewOrders());

    }


}
