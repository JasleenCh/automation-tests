package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import seleniumUtility.DriverScript;

import java.util.concurrent.TimeUnit;

public class SubscriptionsPage extends DriverScript {

    public WebDriver driver;

    public SubscriptionsPage(WebDriver driver) {
        super(driver);
    }

    public static final int SHORT_TIMEOUT = 10;
    public static final int MIDDLE_TIMEOUT = 30;
    public static final int LONG_TIMEOUT = 60;

    public static final int SHORT_DELAY = 1000;
    public static final int MIDDLE_DELAY = 2000;
    public static final int LONG_DELAY = 5000;

    WebDriverWait wait = new WebDriverWait(driver, LONG_DELAY);

    @FindBy(css = "button#subscriptionsButton.btn")
    private WebElement subscriptionsButton;

    @FindBy(id = "inputNumberOfSites")
    private WebElement noOfSites;

    @FindBy(id = "inputLengthOfSubscription")
    private WebElement noOfYears;

    @FindBy(id = "calculateButton")
    private WebElement calculateButton;

    @FindBy(id = "subscriptionsModalPayNowButton")
    private WebElement payNowButton;

    @FindBy(id = "tooltipCurrentExpirationDate")
    private WebElement currentExpirationDate;

    @FindBy(id = "tooltipCurrentExpirationDate")
    private WebElement newExpirationDate;

    @FindBy(id = "panelCompanyInformation-title")
    private WebElement companyInformation;

    private static final By TOOLTIP_CURRENT_EXPIRATION_DATE = By.id("tooltipCurrentExpirationDate");


    public void clickSubscriptionsButton() {
        wait.withTimeout(LONG_TIMEOUT, TimeUnit.SECONDS).until(ExpectedConditions.elementToBeClickable(this.companyInformation));
        subscriptionsButton.click();
    }

    public void inputNumberOfSites(String text) {
        wait.withTimeout(LONG_TIMEOUT, TimeUnit.SECONDS).until(ExpectedConditions.elementToBeClickable(this.noOfSites));
        noOfSites.sendKeys(text);
    }

    public void inputLengthOfSubscription(String text) {
        wait.withTimeout(LONG_TIMEOUT, TimeUnit.SECONDS).until(ExpectedConditions.elementToBeClickable(this.noOfYears));
        noOfYears.sendKeys(text);
    }

    public void clickCalculateButton() throws InterruptedException {
        calculateButton.click();
        Thread.sleep(500);
    }

    public void clickPayNowButton() {
        payNowButton.click();
    }

    public String getInputNumberOfSites() {
        return noOfSites.getText();
    }

    public String inputLengthOfSubscription() {
        return noOfYears.getText();
    }
}
