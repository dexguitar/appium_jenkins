package baseClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BaseClass {

    public static AppiumDriver<MobileElement> driver;
    //    public static AppiumDriverLocalService service;
//    public static AppiumServiceBuilder builder;
    public static DesiredCapabilities caps;

    @BeforeSuite
    public void startServer() throws MalformedURLException {
        caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "emulator-5554");
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "8.1");
        caps.setCapability("chromedriverExecutable",
                Paths.get(".").toAbsolutePath() +
                        "/src/test/resources/chromedriver"
        );

//        builder = new AppiumServiceBuilder();
//        builder.usingPort(4723);
//        builder.withCapabilities(caps);
//        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
//        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
//
//        service = AppiumDriverLocalService.buildService(builder);
//        service.start();

        driver = new AppiumDriver<MobileElement>(
                new URL("http://192.168.0.101:4723/wd/hub"), caps
        );
    }

    @AfterSuite
    public void stopServer() {
//        try {
        driver.quit();
//            service.stop();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}
