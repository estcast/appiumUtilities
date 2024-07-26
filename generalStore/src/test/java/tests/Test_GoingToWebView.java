package tests;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjectModel.CartPage;
import pageObjectModel.ProductsPage;
import pageObjectModel.StartPage;

import java.util.List;
import java.util.Set;

public class Test_GoingToWebView extends BaseTest{

    @Test
    public void WebViewTest(){
        StartPage startPage = new StartPage(driver);
        ProductsPage availableProducts = new ProductsPage(driver);
        CartPage cart = new CartPage(driver);

        //Open country dropdown and select one country
        WebElement countryDropdown = startPage.getCountryDropDown();
        countryDropdown.click();
        scrollTo("Australia");
        startPage.getCountry("Australia").click();

        //Write user's name
        WebElement name = startPage.getNameSpace();
        name.sendKeys("John Smith");
        driver.hideKeyboard();

        //Select genre
        WebElement femaleRadio = startPage.getFemaleRadioBtn();
        femaleRadio.click();

        //Click on "let's shop".
        WebElement shopBtn = startPage.getShopBtn();
        shopBtn.click();

        //Click on first 'add to cart' button available
        List<WebElement> addToCartBtns = availableProducts.getAddToCartBtn();
        addToCartBtns.get(0).click();

        //Go to cart page
        availableProducts.getCartBtn().click();

        //We should not use explicit wait because we are already using implicit
        //Locator title will simulate the wait since it will appear after app transitions.
        //Check how locator "title" is built.
        WebElement title = cart.getTitle();
        title.click();

        //Click on radio button
        cart.getRadioBtn().click();

        //Click on proceed btn
        cart.getProceedBtn().click();

        //In slow computer this sleep is necessary to let emulator load browser
        sleep(15000);

        //Print in console available contexts
        Set<String> contexts = driver.getContextHandles();
        System.out.println(contexts);

        //Change to webView context
        driver.context("WEBVIEW_com.androidsample.generalstore");
        driver.findElement(By.name("q")).sendKeys("appium" + Keys.ENTER);
        sleep(5000);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.context("NATIVE_APP");
        sleep(5000);
    }
}
