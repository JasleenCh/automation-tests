package seleniumUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 * Wait conditions for use with Selenium WebDriverWaits
 */
public class WaitConditions {

    public static ExpectedCondition<Boolean> urlContains(final String text) {
        return new ExpectedCondition<Boolean>() {
            private String currentUrl = "";

            @Override
            public Boolean apply(final WebDriver driver) {
                currentUrl = driver.getCurrentUrl();
                return currentUrl.contains(text);
            }

            @Override
            public String toString() {
                return String.format("URL to contain \"%s\". Current URL: \"%s\"", text, currentUrl);
            }
        };
    }

    public static ExpectedCondition<Boolean> pageContainsText(final String text) {
        return new ExpectedCondition<Boolean>() {
            private String currentPage = "";

            @Override
            public Boolean apply(final WebDriver driver) {
                currentPage = driver.getPageSource();
                return currentPage.contains(text);
            }

            @Override
            public String toString() {
                return String.format("Page to contain \"%s\"", text);
            }
        };
    }

    public static ExpectedCondition<Boolean> elementEnabled(final By locator) {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(final WebDriver driver) {
                final WebElement element = driver.findElement(locator);
                return element.isEnabled();
            }

            @Override
            public String toString() {
                return "Element to become enabled";
            }
        };
    }

    public static ExpectedCondition<Boolean> pageNotContainsText(final String text) {
        return new ExpectedCondition<Boolean>() {
            private String currentPage = "";

            @Override
            public Boolean apply(final WebDriver driver) {
                currentPage = driver.getPageSource();
                return !currentPage.contains(text);
            }

            @Override
            public String toString() {
                return String.format("Page to contain \"%s\"", text);
            }
        };
    }

    public static ExpectedCondition<Boolean> elementTextChanged(final By locator, final String originalText) {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(final WebDriver driver) {
                //System.out.println(driver.getPageSource());
                final String elementText = driver.findElement(locator).getText();
                System.out.println("ELEMENT TEXT " + elementText);
                return (!elementText.equals(originalText));
            }

            @Override
            public String toString() {
                return "element text to change";
            }
        };
    }

    public static ExpectedCondition<Boolean> elementTextNotEmpty(final By locator) {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(final WebDriver driver) {
                final String elementText = driver.findElement(locator).getText();
                return (!elementText.equals(""));
            }

            @Override
            public String toString() {
                return "Element to contain text";
            }
        };
    }

    public static ExpectedCondition<Boolean> elementValueNotEmpty(final By locator) {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(final WebDriver driver) {
                final String elementText = driver.findElement(locator).getAttribute("value");
                return (!elementText.equals(""));
            }

            @Override
            public String toString() {
                return "Element to contain text";
            }
        };
    }

    public static ExpectedCondition<Boolean> tableContainsRows(final By tableLocator) {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(final WebDriver driver) {
                final int tableSize = driver.findElement(tableLocator).findElement(By.tagName("tbody")).findElements(By.tagName("tr")).size();
                return (tableSize > 0);
            }

            @Override
            public String toString() {
                return "Element to contain text";
            }
        };
    }

    public static ExpectedCondition<Boolean> elementClassContains(final By locator, final String classText) {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(final WebDriver driver) {
                return driver.findElement(locator).getAttribute("class").contains(classText);
            }

            @Override
            public String toString() {
                return "Element to contain class";
            }
        };
    }
}
