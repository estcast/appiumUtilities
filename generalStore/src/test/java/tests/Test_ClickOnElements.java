package tests;

import io.appium.java_client.AppiumBy;
import org.testng.annotations.Test;

//IOS
public class Test_ClickOnElements extends IOSBaseTest{

    @Test
    public void clickOnElement(){
        this.testName = "simple click test";
        driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
    }
}
