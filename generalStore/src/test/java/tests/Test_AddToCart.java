package tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjectModel.CartPage;
import pageObjectModel.ProductsPage;
import pageObjectModel.StartPage;

import java.time.Duration;
import java.util.List;


public class Test_AddToCart extends BaseTest{

    @Test
    @Parameters({"userName","country","shoesName"})
    public void addToCart(String userName, String country, String shoesName){
        StartPage startPage = new StartPage(driver);
        ProductsPage availableProducts = new ProductsPage(driver);
        CartPage cart = new CartPage(driver);

        //Open country dropdown and select one country
        WebElement countryDropdown = startPage.getCountryDropDown();
        countryDropdown.click();
        scrollTo(country);
        WebElement country2 = startPage.getCountry(country);
        country2.click();

        //Write user's name
        WebElement name = startPage.getNameSpace();
        name.sendKeys(userName);
        driver.hideKeyboard();

        //Select genre
        WebElement femaleRadio = startPage.getFemaleRadioBtn();
        femaleRadio.click();

        //Click on "let's shop".
        WebElement shopBtn = startPage.getShopBtn();
        shopBtn.click();

        //Scroll for a product.
        scrollTo(shoesName);

        //Retrieve a list of currently available products for viewing
        List<WebElement> products = availableProducts.getProducts();
        int size = products.size();

        //Search for desired product
        for(int i=0; i<size;i++){
            String productName = products.get(i).getText();
            if(productName.equalsIgnoreCase(shoesName)){
                List<WebElement> buttons = availableProducts.getAddToCartBtn();
                buttons.get(i).click();
            }
        }
        //Click on cart btn
        WebElement cartBtn = availableProducts.getCartBtn();
        cartBtn.click();

        //We should not use explicit wait because we are already using implicit
        //Locator title will simulate the wait since it will appear after app transitions.
        //Check how locator "title" is built.
        WebElement title = cart.getTitle();
        title.click();

        WebElement productName = cart.getProductName();
        Assert.assertEquals(productName.getText(),shoesName);

        sleep(3000);
    }
}
