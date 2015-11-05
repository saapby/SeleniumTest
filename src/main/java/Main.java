import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        WebDriver driver = new FirefoxDriver();
        WebDriver driver = new ChromeDriver();
//        WebDriver driver = new InternetExplorerDriver();
        driver.get("http://www.onliner.by/");
        Thread.sleep(10000);
        WebElement baraholka = driver.findElement(By.xpath(".//*[@id='container']/div/div[2]/header/nav/ul[2]/li[6]/a"));
//        By elementLocator = By.id("container");
//        driver.findElement(elementLocator).click();
        baraholka.click();
        Thread.sleep(3000);
        driver.quit();
    }
}
