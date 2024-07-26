package org.example;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URI;

public class AppiumBasics {

    @Test
    public void AppiumTest(){

        String usuarioActual = System.getProperty("user.name");
        // Construir el path dinámico
        String pathArchivo = "C:\\Users\\" + usuarioActual + "\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";

        //Android driver
        //Appium code -> appium server -> mobile
        //xpath, id, accessibilityID, classname, androidUIAutomator.
/*
        AppiumDriverLocalService service = new AppiumServiceBuilder()
                .withAppiumJS(new File(pathArchivo))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();
        service.start();
*/
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Pixel-8-API-33");
        options.setApp("C:\\Users\\estcastr2\\Documents\\appium\\appiumTest\\src\\test\\resources\\ApiDemos-debug.apk");

        AndroidDriver driver = null;
        try {
            driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
            driver.findElement(AppiumBy.accessibilityId("7")).click();
            driver.quit();
            //service.stop();


        }
        catch(Exception e){}
        


/*
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:app", "storage:filename=app-release.apk");  // The filename of the mobile app
        caps.setCapability("appium:deviceName", "Android GoogleAPI Emulator");
        caps.setCapability("appium:platformVersion", "12.0");
        caps.setCapability("appium:automationName", "UiAutomator2");
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", "plesbere");
        sauceOptions.setCapability("accessKey", "6503dc99-9314-4e74-8c60-0a2ca64dd557");
        sauceOptions.setCapability("build", "appium-build-KMTNI");
        sauceOptions.setCapability("name", "<your test name>");
        sauceOptions.setCapability("deviceOrientation", "PORTRAIT");
        caps.setCapability("sauce:options", sauceOptions);

        try {
            URI url = new URI("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
            AndroidDriver driver = new AndroidDriver(url.toURL(), caps);
            WebElement e = driver.findElement(AppiumBy.accessibilityId("7"));
            e.click();
            driver.quit();
        }
        catch(Exception e){}
*/
    }
}
