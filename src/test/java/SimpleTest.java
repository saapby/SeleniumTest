import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 23.10.2015.
 */
public class SimpleTest {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void simpleSeleniumTest() throws InterruptedException {

        //arrange
        driver.get("http://www.onliner.by/");

        WebElement element = driver.findElement(By.linkText("Барахолка"));

        //act
        element.click();

        //assert
        Assert.assertEquals("http://baraholka.onliner.by/", driver.getCurrentUrl());
        Assert.assertEquals("Барахолка onliner.by - Главная страница", driver.getTitle());
    }

    //TODO: REFRESH_TEST_HW
    @Test (expectedExceptions = StaleElementReferenceException.class)
    public void refreshTest() throws InterruptedException {

        //arrange
        driver.get("http://www.onliner.by/");
        WebElement element = driver.findElement(By.linkText("Барахолка"));

        //act
        driver.navigate().refresh();

        //assert
        Assert.assertEquals("http://www.onliner.by/", driver.getCurrentUrl());
        element.click();
    }

    //TODO: BACK_TEST_HW
    @Test
    public void backTest() throws InterruptedException {

        //arrange
        driver.get("http://www.onliner.by/");
        Thread.sleep(3000);
        WebElement element = driver.findElement(By.xpath(".//*[@id='container']/div/div[2]/header/nav/ul[2]/li[6]/a"));

        //act
        element.click();
        Assert.assertEquals("http://baraholka.onliner.by/", driver.getCurrentUrl());
        Thread.sleep(3000);
        driver.navigate().back();

        //assert
        Assert.assertEquals("http://www.onliner.by/", driver.getCurrentUrl());
    }

    //TODO: FORWARD_TEST_HW
    @Test
    public void forwardTest() {

        //arrange
        driver.get("http://www.onliner.by/");
        WebElement element = driver.findElement(By.xpath(".//*[@id='container']/div/div[2]/header/nav/ul[2]/li[6]/a"));

        //act
        element.click();
        Assert.assertEquals("http://baraholka.onliner.by/", driver.getCurrentUrl());
        driver.navigate().back();

        //assert
        Assert.assertEquals("http://www.onliner.by/", driver.getCurrentUrl());

        //act
        driver.navigate().forward();
        Assert.assertEquals("http://baraholka.onliner.by/", driver.getCurrentUrl());

    }
}
