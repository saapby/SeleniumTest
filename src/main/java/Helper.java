import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Admin on 26.10.2015.
 */
public class Helper {

    public static void check(WebElement checkbox) {
//        if (!checkbox.isSelected()) {
//            checkbox.click();
//        }

        setCheckboxTo(checkbox, true);
    }

    public static void unCheck(WebElement checkbox) {
//        if (checkbox.isSelected()) {
//            checkbox.click();
//        }

        setCheckboxTo(checkbox, false);
    }

    private static void setCheckboxTo(WebElement checkbox, boolean value) {
        if (checkbox.isSelected() != value) {
            checkbox.click();
        }
    }

    public static boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException ex) {
            return false;
        }
    }
}
