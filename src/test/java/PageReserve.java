import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PageReserve {
    private static final String URL = "http://10.15.1.204:3000/reserve";
    protected WebDriver driver;

    public PageReserve(WebDriver driver) {
        this.driver = driver;
        this.driver.get(URL);

        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "name")
    private WebElement name;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "phone")
    private WebElement phone;

    @FindBy(id = "date")
    private WebElement date;

    @FindBy(id = "time")
    private WebElement time;

    @FindBy(id = "persons")
    private WebElement persons;

    @FindBy(css = "div.switch")
    private WebElement parkingCheck;

    @FindBy(id = "submitForm")
    private WebElement submitForm;

    public void enterName(String name){ this.name.sendKeys(name); }

    public void enterEmail(String email){
        this.email.sendKeys(email);
    }

    public void enterPhone(String phone) {
        this.phone.sendKeys(phone);
    }

    public void enterDate(String date) { this.date.sendKeys(date); }

    public void enterTime(String time) {
        this.time.sendKeys(time);
    }

    public void selectPersons(String numPersons) {
        Select drpCountry = new Select(persons);
        drpCountry.selectByVisibleText(numPersons);
    }

    public void parkingCheck() {
        parkingCheck.click();
    }

    public void pressSubmitButton(){
        this.submitForm.click();
    }
}
