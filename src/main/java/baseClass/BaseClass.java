package baseClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    public static AppiumDriver<MobileElement> driver;

    @BeforeTest
    @Parameters({"deviceName", "udid", "platformVersion", "url"})
    public void setUp(String deviceName, String udid, String platformVersion, String url) throws InterruptedException, MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("deviceName", deviceName);
        cap.setCapability("udid", udid);
        cap.setCapability("browserName", "Chrome");
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformVersion", platformVersion);
        driver = new AppiumDriver<MobileElement>(new URL(url), cap);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        Thread.sleep(5000);

        // Calling Appium
        try {
            Process p = Runtime.getRuntime().exec(
                    "appium --chromedriver-executable /Users/doglabel/" +
                            "Downloads/chromedriver"
            );
            InputStream is = p.getInputStream();
            int i = 0;
            StringBuffer sb = new StringBuffer();
            while ((i = is.read()) != -1) {
                sb.append((char) i);
                System.out.println(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
