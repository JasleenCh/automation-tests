package angularTest;

import com.jprotractor.NgBy;
import com.jprotractor.NgWebDriver;
import com.jprotractor.NgWebElement;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class AngularTest {

    private static RemoteWebDriver seleniumDriver;
    private static NgWebDriver ngDriver;

    @BeforeClass
    public static void setup() throws IOException {
        DesiredCapabilities capabilities =   new DesiredCapabilities("firefox", "", Platform.ANY);
        FirefoxProfile profile = new ProfilesIni().getProfile("default");
        capabilities.setCapability("firefox_profile", profile);
        seleniumDriver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
        ngDriver = new NgWebDriver(seleniumDriver);
    }

    @Before
    public void beforeEach() {
        String baseUrl = "http://juliemr.github.io/protractor-demo/";
        ngDriver.navigate().to(baseUrl);
        ngDriver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        ngDriver.getTitle().equals("Super Calculator");
    }

    @Test
    public void testCalculator() throws Exception {
        NgWebElement inputValue1 = ngDriver.findElement(NgBy.model("first"));
        inputValue1.sendKeys("1");

        NgWebElement inputValue2 = ngDriver.findElement(NgBy.model("second"));
        inputValue2.sendKeys("2");

        NgWebElement button = ngDriver.findElement(NgBy.buttonText("Go!"));
        button.click();

        NgWebElement result = ngDriver.findElement(NgBy.binding("latest"));
        assertThat(result.getText(), equalTo("3"));
    }
}