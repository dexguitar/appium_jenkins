package testScripts;

import org.testng.annotations.Test;

import baseClass.BaseClass;
import pages.CalculatorScreen;

public class TestScript1 extends BaseClass {

    @Test
    public void setup() throws InterruptedException {

        CalculatorScreen cs = new CalculatorScreen(driver);
        cs.clickButton_1();
        cs.clickPlusButton();
        cs.clickButton_2();
        Thread.sleep(5000);

    }
}