package seleniumUtility;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class DriverScript {

        public static WebDriver driver;
        public static int myAutomationWait = 100;
        public static boolean init=false;
        protected static Actions action;

        public static final String ENV = FileUtility.testEnvironment();

        public static String browserType = FileUtility.readProperty("webdriver","browser.type");
        public static String envUrl = FileUtility.readProperty(ENV,"env.url");

        public static String emailAddress = FileUtility.readProperty(ENV, "env.emailAddress");
        public static String password = FileUtility.readProperty(ENV,"env.password");

	public DriverScript(WebDriver driver) {
        if(!init) {
            initialise(browserType); }
        PageFactory.initElements(DriverScript.driver, this);
    }

        /**
         * This method will launch the demo environment
         *
         */
    public static void launchEnvironment() {
        driver.get(envUrl);
    }

    /**
     * This method will initialise browser.
     *
     * @param browser String browser instantiate the browser.
     * @return boolean value whether browser instantiated or not.
     */
    private boolean initialise(String browser) {
        if(browser.equalsIgnoreCase("firefox")) {

            FirefoxProfile fp = new FirefoxProfile();
            DesiredCapabilities firefoxCapabilities = DesiredCapabilities.firefox();

            fp.setPreference("browser.download.folderList", 2);
            fp.setPreference("browser.download.manager.showWhenStarting", false);
            fp.setPreference("browser.helperApps.neverAsk.openFile","application/pdf");
            fp.setPreference("browser.helperApps.neverAsk.saveToDisk","application/pdf");
            fp.setPreference("browser.helperApps.alwaysAsk.force", false);
            fp.setPreference("browser.download.manager.alertOnEXEOpen", false);
            fp.setPreference("browser.download.manager.focusWhenStarting", false);
            fp.setPreference("browser.download.manager.useWindow", false);
            fp.setPreference("browser.download.manager.showAlertOnComplete", false);
            fp.setPreference("browser.download.manager.closeWhenDone", false);

            driver =  new FirefoxDriver(firefoxCapabilities);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        init = true;
        return init;
    }

    public boolean closeClient() {
        driver.manage().deleteAllCookies();
        driver.close();
        init = false;
        return init;
    }
}

