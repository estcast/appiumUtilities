package pageObjectModel;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class StartPage {

    private final AndroidDriver driver;

    @AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
    private WebElement nameSpace;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.androidsample.generalstore:id/radioFemale\")")
    private WebElement femaleRadioBtn;

    @AndroidFindBy(xpath = "//android.widget.Button[@index='6']")
    private WebElement shopBtn;

    private final String country = "//android.widget.TextView[@text='?']";

    @AndroidFindBy(id="com.androidsample.generalstore:id/spinnerCountry")
    private WebElement countryDropDown;

    //Constructor
    public StartPage(AndroidDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public WebElement getNameSpace(){return nameSpace;}
    public WebElement getFemaleRadioBtn(){return femaleRadioBtn;}
    public WebElement getShopBtn(){return shopBtn;}

    public WebElement getCountry(String kountry){
        return driver.findElement(By.xpath(country.replace("?",kountry)));
    }

    public WebElement getCountryDropDown(){return countryDropDown;}
}
