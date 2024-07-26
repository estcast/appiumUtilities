package tests;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class IOSBaseTest {

    protected IOSDriver driver = null;
    protected AppiumDriverLocalService service = null;
    protected String testName = "test name";

    @BeforeClass
    public void configureAppium(){
        // Build dynamic path for Windows users
        String currentUser = System.getProperty("user.name");
        String filePath = "C:\\Users\\" + currentUser + "\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
        /*
        //Start appium server
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File(filePath))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();
        service.start();
        */
        //Configure driver
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("appium:app", "storage:filename=UIKitCatalog-iphonesimulator.app.zip");  // The filename of the mobile app
        caps.setCapability("appium:deviceName", "iPhone Simulator");
        caps.setCapability("appium:platformVersion", "17.0");
        caps.setCapability("appium:automationName", "XCUITest");
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("appiumVersion", "2.1.3");
        sauceOptions.setCapability("username", "oauth-pablo.plesbere-340c7");
        sauceOptions.setCapability("accessKey", "33a6d4fe-f19f-4a44-ae47-d9404ce0b121");
        sauceOptions.setCapability("build", "<your build id>");
        sauceOptions.setCapability("name", testName);
        sauceOptions.setCapability("deviceOrientation", "PORTRAIT");
        caps.setCapability("sauce:options", sauceOptions);

        URL url = null;
        try {
            url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
            driver = new IOSDriver(url, caps);

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public void longPressAction(WebElement element){
        Map<String, Object> params = new HashMap<>();
        params.put("element",((RemoteWebElement)element).getId());
        params.put("duration",2);
        driver.executeScript("mobile:touchAndHold", params);
    }

    public void scrollTo(WebElement element){
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "down");
        params.put("element", ((RemoteWebElement)element).getId());
        driver.executeScript("mobile:scroll",params);
    }

    //Swipe in the middle of the screen.
    public void swipe(String direction){
        Map<String, Object> params = new HashMap<>();
        params.put("direction",direction);
        driver.executeScript("mobile:swipe",params);
    }

    //Swipe on specific element
    public void swipe(WebElement element, String direction){
        Map<String, Object> params = new HashMap<>();
        params.put("direction",direction);
        params.put("element",((RemoteWebElement)element).getId());
        driver.executeScript("mobile:swipe",params);
    }

    public void sleep(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
        //service.stop();
    }
}
