package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjectModel.ProductsPage;
import pageObjectModel.StartPage;

public class Test_FillForm extends BaseTest{

    @Test
    @Parameters({"userName","country"})
    public void fillForm(String userName, String country){
        StartPage startPage = new StartPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

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

        /*
        //Place in comments line 27 and 45 before enabling this assertion
        String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
        Assert.assertEquals(toastMessage, "Please enter your name");
        */

        WebElement title = productsPage.getTitle();
        Assert.assertEquals(title.getText(),"Products");

        sleep(3000);
    }
}
