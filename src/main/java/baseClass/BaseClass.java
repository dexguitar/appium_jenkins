package baseClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseClass {

    public static AppiumDriver<MobileElement> driver;
    public static AppiumDriverLocalService service;
    public static AppiumServiceBuilder builder;
    public static DesiredCapabilities cap;

    @BeforeSuite
    public void startServer() throws MalformedURLException {
        cap = new DesiredCapabilities();
        cap.setCapability("deviceName", "emulator-5554");
        cap.setCapability("browserName", "Chrome");
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformVersion", "8.1");
        cap.setCapability("chromedriverExecutable", "/Users/doglabel/Downloads/chromedriver");

        builder = new AppiumServiceBuilder();
        builder.usingPort(4723);
        builder.withCapabilities(cap);
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");

        service = AppiumDriverLocalService.buildService(builder);
        service.start();

        driver = new AppiumDriver<MobileElement>(
                new URL("http://0.0.0.0:4723/wd/hub"), cap
        );
    }

    @AfterSuite
    public void stopServer() {
        try {
            driver.quit();
            service.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
