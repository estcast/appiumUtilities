package pageObjectModel;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class IOS_UIKitCatalog_MainPage {

    @iOSXCUITFindBy(accessibility = "Steppers")
    private WebElement steppersButton;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == 'Increment'`][3]")
    private WebElement plusBtn;

    //Constructor
    public IOS_UIKitCatalog_MainPage(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public WebElement getSteppersButton(){return steppersButton;}
    public WebElement getPlusBtn(){return plusBtn;}
}
