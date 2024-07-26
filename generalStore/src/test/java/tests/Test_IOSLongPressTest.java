package tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pageObjectModel.IOS_UIKitCatalog_MainPage;

public class Test_IOSLongPressTest extends IOSBaseTest{

    private final String testName = "IOS longpress test";

    @Test
    public void longPressTest(){
        IOS_UIKitCatalog_MainPage mainPage = new IOS_UIKitCatalog_MainPage(driver);

        mainPage.getSteppersButton().click();
        longPressAction(mainPage.getPlusBtn());
    }
}
