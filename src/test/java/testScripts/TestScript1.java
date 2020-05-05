package testScripts;

import baseClass.BaseClass;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.testng.Assert.assertEquals;

public class TestScript1 extends BaseClass {

    @BeforeTest
    public void setUp() {
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

    @Test
    public void simpleLoginTest() {
        driver.get("https://jdi-testing.github.io/jdi-mobile-web/index.html");
        driver.findElement(By.id("user-icon")).click();
        driver.findElement(By.id("name")).sendKeys("Roman");
        driver.findElement(By.id("password")).sendKeys("Jdi1234");
        driver.findElement(By.id("login-button")).click();
        assertEquals(driver.findElement(By.id("user-name")).getText(), "ROMAN IOVLEV");
    }

    @Test
    public void buttonPressTest() {
        driver.findElement(By.xpath("//span[contains(text(), 'Elements')]")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'HTML 5')]")).click();

        assertEquals(driver.getTitle(), "HTML 5");

        driver.findElement(By.xpath("//input[@type='button']")).click();

        assertEquals(driver.switchTo().alert().getText(), "Red button");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}