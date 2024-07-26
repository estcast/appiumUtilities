package tests;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.URI;
import java.time.Duration;

public class BaseTest {

    protected AndroidDriver driver = null;
    protected AppiumDriverLocalService service = null;

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
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Pixel-8-Pro-API-33");
        String pathYourSystem = System.getProperty("user.dir") + "\\";

        options.setApp(pathYourSystem + "src\\test\\resources\\General-Store.apk");
        options.setChromedriverExecutable(pathYourSystem + "src/test/resources/chromeDriver126-0-6478/chromedriver.exe");
        try {
            driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        catch (Exception e){}
    }

    public void longPressAction(WebElement element){
        ((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId",((RemoteWebElement)element).getId(), "duration",2000));
    }

    public void scrollTo(String word){
        String template = "new UiScrollable(new UiSelector()).scrollIntoView(text(\"?\"));";
        driver.findElement(AppiumBy.androidUIAutomator(template.replace("?",word)));
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
