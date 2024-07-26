package pageObjectModel;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage {

    @AndroidFindBys({@AndroidBy(id = "com.androidsample.generalstore:id/productName")})
    private List<WebElement> products;

    @AndroidFindBys({@AndroidBy(id = "com.androidsample.generalstore:id/productAddCart")})
    private List<WebElement> addToCartBtn;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement cartBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Products']")
    private WebElement title;

    //Constructor
    public ProductsPage(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public List<WebElement> getProducts(){return products;}
    public List<WebElement> getAddToCartBtn() {return addToCartBtn;}
    public WebElement getCartBtn() {return cartBtn;}
    public WebElement getTitle(){return title;}
}
