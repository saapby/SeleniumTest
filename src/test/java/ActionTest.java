import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 28.10.2015.
 */
public class ActionTest {
    private WebDriver driver;
    private final static String BASE_URL = "https://the-internet.herokuapp.com/";

    @BeforeMethod
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        driver.get(BASE_URL);

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void sendKeysTest() throws InterruptedException {

        Assert.assertTrue(driver.findElement(By.linkText("Key Presses")).isDisplayed(), "Key Presses");
        driver.findElement(By.linkText("Key Presses")).click();
        Assert.assertEquals("https://the-internet.herokuapp.com/key_presses", driver.getCurrentUrl());

        Actions actions = new Actions(driver);
        for (int i = 80; i < 90; i++) {
            String enteredValue = String.valueOf((char) i);
            actions.sendKeys(enteredValue).perform();
            Thread.sleep(1000);
            WebElement displayed = driver.findElement(By.id("result"));
            Assert.assertEquals(displayed.getText(), "You entered: " + enteredValue);
            Thread.sleep(500);
        }

//        actions.sendKeys(Keys.CONTROL + "A");
//        actions.contextClick().

    }

    @Test
    public void contextMenuTest() throws InterruptedException {
        Assert.assertTrue(driver.findElement(By.linkText("Context Menu")).isDisplayed(), "Context Menu");
        driver.findElement(By.linkText("Context Menu")).click();
        Assert.assertEquals("https://the-internet.herokuapp.com/context_menu", driver.getCurrentUrl());
        Actions actions = new Actions(driver);
        WebElement contextBox = driver.findElement(By.id("hot-spot"));

        actions
                .contextClick(contextBox)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER).perform();
        Thread.sleep(2000);

    }

    @Test
    public void hoversTest() throws InterruptedException {
        Assert.assertTrue(driver.findElement(By.linkText("Hovers")).isDisplayed(), "Hovers");
        driver.findElement(By.linkText("Hovers")).click();
        List<WebElement> hoversList = driver.findElements(By.cssSelector(".figure"));
        Actions actions = new Actions(driver);
//        for (int i = 0; i < hoversList.size(); i++) {
//            actions.moveToElement(hoversList.get(i)).perform();
//            Thread.sleep(1000);
//            Assert.assertEquals(hoversList.get(i).findElement(By.cssSelector(".figcaption>h5")).getText(), "name: user" + (i + 1));
//        }
//
//        Thread.sleep(1000);
//
//        for (WebElement webElemTest : hoversList) {
//            actions.moveToElement(webElemTest).perform();
//            Assert.assertTrue(webElemTest.findElement(By.tagName("h5")).isDisplayed());
//            Thread.sleep(1000);
//        }
        int counter = 1;
        for (WebElement webElemTest : hoversList) {
            actions.moveToElement(webElemTest).perform();
            Assert.assertTrue(webElemTest.findElement(By.tagName("h5")).isDisplayed());
            Assert.assertEquals(webElemTest.findElement(By.tagName("h5")).getText(), "name: user" + counter);
            counter++;
            Thread.sleep(1000);
        }

    }

    @Test
    public void dragAndDrop1Test() throws InterruptedException {
        Assert.assertTrue(driver.findElement(By.linkText("Drag and Drop")).isDisplayed(), "Drag and Drop");
        driver.findElement(By.linkText("Drag and Drop")).click();

        WebElement source = driver.findElement(By.id("column-a"));
        WebElement target = driver.findElement(By.id("column-b"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();

        Assert.assertEquals(source.findElement(By.tagName("header")).getText(), "B");
        Assert.assertEquals(target.findElement(By.tagName("header")).getText(), "A");

    }

    @Test
    public void dragAndDrop2Test() throws InterruptedException {
        Assert.assertTrue(driver.findElement(By.linkText("Drag and Drop")).isDisplayed(), "Drag and Drop");
        driver.findElement(By.linkText("Drag and Drop")).click();

        WebElement source = driver.findElement(By.id("column-a"));
        WebElement target = driver.findElement(By.id("column-b"));

        Actions actions = new Actions(driver);

        actions
                .clickAndHold(source)
                .moveToElement(target)
                .release()
                .perform();
        actions.moveToElement(source).perform();
        Thread.sleep(2000);
        actions.click(source).perform();
        Thread.sleep(2000);
        actions.clickAndHold(source).perform();
        Thread.sleep(2000);
        actions.moveToElement(target).perform();
        Thread.sleep(2000);
        Assert.assertEquals(source.findElement(By.tagName("header")).getText(), "B");
        Assert.assertEquals(target.findElement(By.tagName("header")).getText(), "A");

    }

    @Test
    public void dragAndDrop3Test() throws InterruptedException {
        Assert.assertTrue(driver.findElement(By.linkText("Drag and Drop")).isDisplayed(), "Drag and Drop");
        driver.findElement(By.linkText("Drag and Drop")).click();

        WebElement source = driver.findElement(By.id("column-a"));
        WebElement target = driver.findElement(By.id("column-b"));

        Actions actions = new Actions(driver);
//        actions.dragAndDrop(source, target).perform();
        actions
                .clickAndHold(source)
                .moveByOffset(200, 300)
                .release()
                .perform();
//        source.sendKeys();
        actions.moveToElement(source).perform();
        Thread.sleep(2000);
        actions.click(source).perform();
        Thread.sleep(2000);
        actions.clickAndHold(source).perform();
        Thread.sleep(2000);
        actions.moveToElement(target).perform();
        Thread.sleep(2000);
        Assert.assertEquals(source.findElement(By.tagName("header")).getText(), "B");
        Assert.assertEquals(target.findElement(By.tagName("header")).getText(), "A");

    }

    @Test
    public void contextMenuNewDLCTest() throws InterruptedException {
        Assert.assertTrue(driver.findElement(By.linkText("Context Menu")).isDisplayed(), "Context Menu");
        driver.findElement(By.linkText("Context Menu")).click();
        Assert.assertEquals("https://the-internet.herokuapp.com/context_menu", driver.getCurrentUrl());
        Actions actions = new Actions(driver);
        WebElement contextBox = driver.findElement(By.id("hot-spot"));

        actions
                .contextClick(contextBox)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER).perform();
        Thread.sleep(2000);
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "You selected a context menu");
        alert.accept();
        Assert.assertFalse(Helper.isAlertPresent(driver), "Alert is present");
    }
}
