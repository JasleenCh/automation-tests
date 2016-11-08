package stepDef;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageObjects.LoginPageObjects;

public class LoginSteps {

    public WebDriver driver;

    private static final String EMAIL_ADDRESS = "";

    LoginPageObjects loginPage = new LoginPageObjects(driver);

    @Given("^I am logged in$")
    public void iLoggedIn()  {
        loginPage.userLogin(EMAIL_ADDRESS);
    }

    @When("^I select the my company option$")
    public void iClickOnSedexAdvance() throws InterruptedException {
        loginPage.clickSedexAdvanceButton();
        loginPage.clickmyCompany();
    }

    @When("^I click the subscriptions button$")
    public void I_click_the_subscriptions_button() throws Throwable {
        loginPage.clickSubscriptionsButton();
    }

    @And("^I input number of sites as \"([^\"]*)\", number of years as \"([^\"]*)\" and click calculate$")
    public void iInputNumberOfSitesAsNumberOfYearsAsAndClickCalculate(String numberOfSites, String numberOfYears) throws Throwable {
        loginPage.inputNumberOfSites(numberOfSites);
        loginPage.inputLengthOfSubscription(numberOfYears);
        loginPage.clickCalculateButton();
    }

    @Then("^I should see that new expiration date is extended by \"([^\"]*)\" years$")
    public void iShouldSeeThatNewExpirationDateIsExtendedByYears(int noOfyears)  {
        loginPage.checkNewExpirationDate(noOfyears);
    }

    @And("^And I click the pay via WorldPay button$")
    public void iClickPayNowButton() throws InterruptedException {
       loginPage.clickPayNowButton();
        loginPage.clickWorldPayButton();
    }

    @And("^I select the payment provider$")
    public void iSelectThePaymentProvider() throws InterruptedException {
        loginPage.selectPaymentMethod();
    }

    @And("^I enter the card details$")
    public void iEnterTheCardDetails() throws Throwable {
        loginPage.enterCardDetails();
    }

    @Then("^I can successfully make a payment$")
    public void iCanSuccessfullyMakeAPayment() throws Throwable {
        Assert.assertEquals("The payment is not successfull", "Thank you.", loginPage.checkWorldPayPaymentsPageTitle());
    }
}