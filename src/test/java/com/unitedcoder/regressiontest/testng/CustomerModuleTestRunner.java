package com.unitedcoder.regressiontest.testng;

import com.seleniummaster.maganto.backendpages.BackEndLogin;
import com.seleniummaster.maganto.backendpages.customerpages.*;
import com.seleniummaster.maganto.utility.ApplicationConfig;
import com.seleniummaster.maganto.utility.BasePage;
import com.seleniummaster.maganto.utility.TestDataHolder;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;


public class CustomerModuleTestRunner extends BasePage {
    final String configFile = "config.properties";
    BackEndLogin login;
    CustomerDashboardPage customerDashboardPage;
    CustomerGroupsPage customerGroupsPage;
    FilterCustomerPage filterCustomerPage;
    CustomerPage customerPage;
    AddAddressesPage addAddressesPage;

    @BeforeClass
    public void setup(ITestContext context) {
        String url = ApplicationConfig.readFromConfigProperties(configFile, "url");
        browserSetUp(url);
        context.setAttribute("driver", driver);
        login = new BackEndLogin(driver);
        login.customerPageLogin();
        customerDashboardPage = new CustomerDashboardPage(driver);
        customerGroupsPage = new CustomerGroupsPage(driver);
        filterCustomerPage = new FilterCustomerPage(driver);
        customerPage = new CustomerPage(driver);
        addAddressesPage = new AddAddressesPage(driver);

    }

    @Test(groups = "regression test", description = "Customer Manager can add a new customer ")
    public void addNewCustomer() {
        customerDashboardPage.clickOnManageCustomers();
        customerPage.addNewCustomer();
        Assert.assertTrue(customerPage.verifyNewCustomerAdded());
    }

    @Test(dataProvider = "customerGroupInfo", groups = "regression test", description = "Customer Manager can add new customer groups.")
    public void addNewCustomerGroups(TestDataHolder testDataHolder) {
        login.VerifyLoginSuccessfully();
        customerDashboardPage.clickOnCustomerGroups();
        customerGroupsPage.addNewCustomerGroups(testDataHolder);
        Assert.assertTrue(customerGroupsPage.verifyAddNewCustomerGroups());
    }

    @Test(enabled = false,dataProvider = "customerGroupInfo", groups = "regression test", description = "Customer Manager can  update existing customer groups.", dependsOnMethods = "addNewCustomerGroups")
    public void updateExistingCustomerGroups(TestDataHolder testDataHolder) {
        customerDashboardPage.clickOnCustomerGroups();
        customerGroupsPage.updateExistingCustomerGroups(testDataHolder);
        Assert.assertTrue(customerGroupsPage.verifyUpdateExistingCustomerGroups());
    }

    @Test(description = "assign a customer to group",
            dataProvider = "customerGroupInfo",dependsOnMethods = {"addNewCustomer","addNewCustomerGroups"})
    public void assignACustomerToGroup(TestDataHolder testDataHolder) {
        customerDashboardPage.clickOnManageCustomers();
        customerPage.selectAddedCustomer();
        customerPage.selectActionsList();
        customerPage.selectGroup(testDataHolder);
        customerPage.clickOnSubmitButton();
        customerPage.verificationACustomerAssignToGroup();
    }

    @Test(enabled = false,description = "exportCustomer")
    public void exportCustomer() {
        String fileType = "Excel XML";
        customerPage.selectFileType(fileType);
        customerPage.clickOnExportButton();
        customerPage.isCustomerFileExported();
    }

    @Test(dataProvider = "customerGroupInfo", groups = "regression test", description = "Customer Manager can delete existing customer groups", dependsOnMethods = "assignACustomerToGroup")
    public void deleteExistingCustomerGroups(TestDataHolder testDataHolder) {
        customerDashboardPage.clickOnCustomerGroups();
        customerGroupsPage.deleteExistingCustomerGroups(testDataHolder);
        Assert.assertTrue(customerGroupsPage.verifyDeleteExistingCustomerGroups());
    }

    @Test(enabled = false,dataProvider = "filterCustomerInfo", groups = "regression test", description = "Customer Manager Can Filter Customers by Email")
    public void filterCustomerByEmail(String email) {
        customerDashboardPage.clickOnManageCustomers();
        filterCustomerPage.clickEmailField(email);
        Assert.assertTrue(filterCustomerPage.verifyFilterCustomerByEmail());
    }

    @DataProvider
    public Object[] customerGroupInfo() {
        Object[] data = new Object[]{new TestDataHolder("Europe Customer")};
        return data;
    }

    @DataProvider
    public Object[][] filterCustomerInfo() {
        Object[][] data = new Object[][]{
                {"gunes8811@hotmail.com"}
        };

        return data;
    }


    @Test(enabled = false,groups = "regression test", description = "Customer Manager can add a new address for a customer")
//dataProvider = "customerGroupInfo",,dependsOnMethods = "addNewCustomer"
    public void addNewAddress() {
        customerDashboardPage.navigateToAddressesLink();
        addAddressesPage.addNewAddress();
        Assert.assertTrue(addAddressesPage.verifyNewAddressAdded());
        addAddressesPage.deleteAddedAddress();
    }

    @Test(enabled = false,description = "Customer Manager can update an existing customer ")
    public void updateCustomer() {
        customerPage.updateCustomer();
        Assert.assertTrue(customerPage.verifyUpdateCustomer());
    }

    @Test(enabled = false,groups = "regression test", description = "Customer Manager can filter customers by Country, State, and website. ")
    public void filterCustomerByCountry() {
        customerDashboardPage.clickOnManageCustomers();
        filterCustomerPage.filterByCountry();
        Assert.assertTrue(filterCustomerPage.verifyFilteredByCountry());
        filterCustomerPage.clickOnResetFilter();
        filterCustomerPage.filterByWebsite();
        Assert.assertTrue(filterCustomerPage.verifyFilteredByWebsite());
        filterCustomerPage.clickOnResetFilter();
        filterCustomerPage.filterByState();
        Assert.assertTrue(filterCustomerPage.verifyFilteredByState());
    }

    @Test(enabled = false,description = "Customer Manager can delete an existing customer")
    public void deleteExistingCustomer() {
        customerDashboardPage.clickOnManageCustomers();
        customerPage.deleteCustomer();
        Assert.assertTrue(customerPage.verifyDeleteCustomer());
    }

    @AfterClass
    public void tearDown() {
        closeBrowser();
    }

}
