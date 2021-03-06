package py_to_ja;
import org.junit.*;
import event.EventConfig;
import java.util.concurrent.TimeUnit;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
public class login {
    private static FirefoxDriver driver;
    WebElement element;
    private EventConfig event = new EventConfig(driver);

    @BeforeClass
    public static void openBrowser() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://sta-kiv-gt1-setup01-spp-01.nix.cydmodule.com:8080/admin/tester.jsp");
        driver.findElementByCssSelector("input[name=login]").sendKeys("manager");
        driver.findElementByName("password").sendKeys("manager");
        driver.findElementByCssSelector("input[type=submit]").click();

    }


    @Test
    public void launch_game() throws Exception {
        System.out.println("Starting test" + new Object() {
        }.getClass().getEnclosingMethod().getName());
        driver.findElementByPartialLinkText("wolfcub").click();
        driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
        WebElement element = (new WebDriverWait(driver, 90)).until(ExpectedConditions.elementToBeClickable(By.id("undefined")));
        element.click();
        WebElement cont = driver.findElementById("canvasAnimationManager");
        Actions builder = new Actions(driver);
        builder.moveToElement(cont,1119, 636).click().build().perform();
        builder.moveToElement(cont,635,634).click().build().perform();
        event.waitForfinspin();
        driver.navigate().back();

    }

    @AfterClass
        public static void closeBrowser(){

        driver.quit();
    }

}

