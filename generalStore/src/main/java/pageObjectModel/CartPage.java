package pageObjectModel;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Cart\"]")
    private WebElement title;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
    private WebElement productName;

    @AndroidFindBys({
            @AndroidBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productPrice\"]")
    })
    private List<WebElement> productsPrices;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement totalAmount;

    @AndroidFindBy(className = "android.widget.CheckBox")
    private WebElement radioBtn;

    @iOSXCUITFindBy(id = "")
    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
    private WebElement proceedBtn;



    //Constructor
    public CartPage(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public WebElement getTitle(){return title;}
    public WebElement getProductName(){return productName;}
    public List<WebElement> getProductsPrices(){return productsPrices;}
    public WebElement getTotalAmount(){return totalAmount;}
    public WebElement getRadioBtn() {return radioBtn;}
    public WebElement getProceedBtn() {return proceedBtn;}
}
