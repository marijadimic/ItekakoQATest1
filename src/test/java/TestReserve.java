import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestReserve {
    public static final String CHROME_DRIVER_LOCATION = "chromedriver.exe";
    public static  WebDriver driver;
    public static WebDriverWait wdWait;

    @BeforeClass
    public void testSetUp() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_LOCATION);
        driver = new ChromeDriver();
        wdWait = new WebDriverWait(driver,30);
    }

    @Test
    public void submitReservation() {
        PageReserve pageReserve = new PageReserve(driver);

        final String name = "Marija Dimic";
        final String email = "marija.dimic@kmail.com";
        final String phone = "064777888";
        final String numPersons = "2";

        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));

        pageReserve.enterName(name);
        pageReserve.enterEmail(email);
        pageReserve.enterPhone(phone);
        pageReserve.enterDate("07162022");
        pageReserve.enterTime("0430PM");
        pageReserve.selectPersons(numPersons);
        pageReserve.parkingCheck();
        pageReserve.pressSubmitButton();

        WebStorage webStorage = (WebStorage) new Augmenter().augment(driver);
        LocalStorage localStorage = webStorage.getLocalStorage();

        Assert.assertEquals(localStorage.getItem("name"), name);
        Assert.assertEquals(localStorage.getItem("email"), email);
        Assert.assertEquals(localStorage.getItem("phone"), phone);
        Assert.assertEquals(localStorage.getItem("date"), "2022-07-16");
        Assert.assertEquals(localStorage.getItem("time"), "16:30");
        Assert.assertEquals(localStorage.getItem("person"), numPersons);
        Assert.assertEquals(localStorage.getItem("parking"), "on");


    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
