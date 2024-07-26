package tests;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjectModel.CartPage;
import pageObjectModel.ProductsPage;
import pageObjectModel.StartPage;

import java.util.List;

public class Test_VerifyTotalAmount extends BaseTest{

    @Test
    public void VerifyTotalAmount(){
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

        //Click on each 'add to cart' button available
        List<WebElement> addToCartBtns = availableProducts.getAddToCartBtn();
        for(WebElement element:addToCartBtns){
            element.click();
        }

        //Go to cart page
        availableProducts.getCartBtn().click();

        //We should not use explicit wait because we are already using implicit
        //Locator title will simulate the wait since it will appear after app transitions.
        //Check how locator "title" is built.
        WebElement title = cart.getTitle();
        title.click();

        //Get and sum product prices
        List<WebElement> productPrices = cart.getProductsPrices();
        double sum = 0;
        for(WebElement element: productPrices){
            sum += formatPrice(element.getText());
        }

        //Get total amount
        WebElement totalAmount = cart.getTotalAmount();

        //Assert both amounts are equal.
        Assert.assertEquals(formatPrice(totalAmount.getText()),sum);

        sleep(3000);
    }

    public double formatPrice(String price){
        //Delete $ from text before parsing.
        return Double.parseDouble(price.substring(1));
    }
}
