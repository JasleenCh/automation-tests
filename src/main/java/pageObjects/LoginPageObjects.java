package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import seleniumUtility.DriverScript;

import java.util.concurrent.TimeUnit;

public class LoginPageObjects extends DriverScript {

        public static final int SHORT_TIMEOUT = 10;
        public static final int MIDDLE_TIMEOUT = 30;
        public static final int LONG_TIMEOUT = 60;

        public static final int SHORT_DELAY = 1000;
        public static final int MIDDLE_DELAY = 2000;
        public static final int LONG_DELAY = 5000;
    private static final By TOOLTIP_CURRENT_EXPIRATION_DATE = By.id("tooltipCurrentExpirationDate");
        private static final Logger LOG = LoggerFactory.getLogger("LoginPageObjects");
    WebDriverWait wait  = new WebDriverWait(driver, LONG_DELAY);
        @FindBy(id = "username")
        private WebElement emailAddress;
        @FindBy(id = "password")
        private WebElement password;
        @FindBy(css = "button[type=submit]")
        private WebElement loginButton;
        @FindBy(css = ".btn-group-vertical > .btn:first-child")
        private WebElement sedexAdvance;
        @FindBy(css = "a#menu-link-company.dropdown-toggle span.ng-scope")
        private WebElement companyButton;
        @FindBy(css = "a#menu-link-myCompany")
         private WebElement myCompany;
         @FindBy(id = "category-PAYMENTS")
         private WebElement payments;
    @FindBy(css = "button#subscriptionsButton.btn")
    private WebElement subscriptionsButton;
    @FindBy(id = "inputNumberOfSites")
    private WebElement noOfSites;
    @FindBy(id = "inputLengthOfSubscription")
    private WebElement noOfYears;
    @FindBy(id = "calculateButton")
    private WebElement calculateButton;
    @FindBy(id = "tooltipCurrentExpirationDate")
    private WebElement currentExpirationDate;
    @FindBy(id = "tooltipCurrentExpirationDate")
    private WebElement newExpirationDate;
    @FindBy(id = "panelCompanyInformation-title")
    private WebElement companyInformation;
    @FindBy(id = "displayedValueCurrentExpirationDate")
    private WebElement currentExpirationDateDisplayedValue;
    @FindBy(id = "inputLengthOfSubscription")
    private WebElement lengthOfSubscription;
    @FindBy(id = "subscriptionsModalPayNowButton")
    private WebElement payNowButton;
    @FindBy(id = "providerButton-WORLDPAY")
    private WebElement worldPayButton;
    @FindBy(id = "cardNoInput")
    private WebElement cardNumber;
    @FindBy(id = "address1")
    private WebElement addressDetail;
    @FindBy(id = "town")
    private WebElement townDetails;
    @FindBy(id = "op-PMMakePayment")
    private WebElement makePayment;
    @FindBy(id = "continue")
    private WebElement continueButton;

        public LoginPageObjects(WebDriver driver) {
            super(driver);
        }

        /**
         * This method will launch the sedex enviornment and sign in page.
         *
         * @return LoginPageObjects instance
         */
        public LoginPageObjects userLogin(String emailAddress) {
            try {
                DriverScript.launchEnvironment();
                if (driver.getTitle().equals("Sedex Authentication")) {
                    //loginButton.click();
                    wait.withTimeout(LONG_TIMEOUT, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOf(this.emailAddress));
                    if (emailAddress.equals("")) {
                        this.emailAddress.sendKeys(DriverScript.emailAddress);
                    } else {
                        this.emailAddress.sendKeys(emailAddress);
                    }
                    driver.manage().timeouts().implicitlyWait(SHORT_TIMEOUT, TimeUnit.SECONDS);
                    password.sendKeys(DriverScript.password);
                    loginButton.click();
                    wait.withTimeout(LONG_TIMEOUT, TimeUnit.SECONDS).until(ExpectedConditions.titleContains("Welcome"));
                }
            }catch(Exception ex) {
                LOG.error("Login to Sedex is not successful", ex);
            }
            return new LoginPageObjects(driver);
        }

    public void clickSedexAdvanceButton() {
            sedexAdvance.click();
        }

    public void clickmyCompany() throws InterruptedException {
        Thread.sleep(12000);
        wait.withTimeout(LONG_TIMEOUT, TimeUnit.SECONDS).until(ExpectedConditions.elementToBeClickable(this.payments));
        companyButton.click();
        myCompany.click();
    }

    public void clickSubscriptionsButton() throws InterruptedException {
        Thread.sleep(12000);
        wait.withTimeout(LONG_TIMEOUT, TimeUnit.SECONDS).until(ExpectedConditions.elementToBeClickable(this.companyInformation));
        subscriptionsButton.click();
    }

    public void inputNumberOfSites(String text) {
        wait.withTimeout(LONG_TIMEOUT, TimeUnit.SECONDS).until(ExpectedConditions.elementToBeClickable(this.noOfSites));
        noOfSites.clear();
        noOfSites.sendKeys(text);
    }

    public void inputLengthOfSubscription(String text) {
        wait.withTimeout(LONG_TIMEOUT, TimeUnit.SECONDS).until(ExpectedConditions.elementToBeClickable(this.noOfYears));
        noOfYears.clear();
        noOfYears.sendKeys(text);
    }

    public void clickCalculateButton() throws InterruptedException {
        calculateButton.click();
        Thread.sleep(500);
    }

    public String getInputNumberOfSites() {
        return noOfSites.getText();
    }

    public String getInputLengthOfSubscription() {
        System.out.println("Length of subscription :" + lengthOfSubscription.getText());
        return lengthOfSubscription.getText();
    }

    public String getCurrentExpirationDate() {
        System.out.println("Current expiration date :" +  currentExpirationDateDisplayedValue.getText());
        return currentExpirationDateDisplayedValue.getText();
    }

    public String checkNewExpirationDate(int noOfYears) {
        String expirationDate = currentExpirationDateDisplayedValue.getText();
        String[] dateParts = expirationDate.split("/");
        String day = dateParts[0];
        String month = dateParts[1];
        String year = dateParts[2];
        String newExpirationYear = String.valueOf((Integer.parseInt(dateParts[2]) + noOfYears));
        String newExpirationDate = day + "/" + month + "/" + newExpirationYear ;
        System.out.println("New expiration date is:" + newExpirationDate);
        return newExpirationDate;
    }

    public void clickPayNowButton() throws InterruptedException {
        payNowButton.click();
        Thread.sleep(25000);
    }

    public void clickWorldPayButton() throws InterruptedException {
        worldPayButton.click();
        Thread.sleep(20000);
    }

    public void selectPaymentMethod() throws InterruptedException {
       // driver.findElement(By.xpath("//td[@class='cardlabel’]/table/tbody/tr/td[1]/input"));
        driver.findElement(By.name("op-DPChoose-ECMC^SSL")).click();
        Thread.sleep(20000);
    }

    public void enterCardDetails() throws InterruptedException {
       cardNumber.sendKeys("5555555555554444");

        WebElement cardExpiryMonth = driver.findElement(By.name("cardExp.month"));
        Select mySelect= new Select(cardExpiryMonth);
        mySelect.selectByVisibleText("02");

        WebElement cardExpiryYear = driver.findElement(By.name("cardExp.year"));
        Select mySelect1= new Select(cardExpiryYear);
        mySelect1.selectByVisibleText("2020");

        addressDetail.sendKeys("24 Southwark Bridge Road");
        townDetails.sendKeys("London");

        WebElement country = driver.findElement(By.name("country"));
        Select mySelect2= new Select(country);
        mySelect2.selectByVisibleText("Afghanistan");

        makePayment.click();
        Thread.sleep(10000);
       // continueButton.click();
        //Thread.sleep(10000);
    }

    public String checkWorldPayPaymentsPageTitle() {
        System.out.println(driver.getTitle());
        return driver.getTitle();
    }
}


