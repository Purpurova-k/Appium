package ru.netology.tests;

import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.netology.screen.AppScreen;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppTest {

    private AndroidDriver driver;
    private AppScreen screen;

    @BeforeAll
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Nexus 6");
        desiredCapabilities.setCapability("appium:app", "C:\\Users\\Kate\\Desktop\\QA\\Netology\\Mobile\\2.4 Appium\\apk\\app-debug.apk");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        screen = new AppScreen(driver);
    }

    @Test
    @Order(1)
    public void testInputNothing() {
        String textBefore = screen.textToBeChanged.getText();
        screen.buttonChange.click();
        String textAfter = screen.textToBeChanged.getText();
        assertEquals(textBefore, textAfter);
    }

    @Test
    @Order(2)
    public void testInputSpace() {
        String textBefore = screen.textToBeChanged.getText();
        screen.inputField.sendKeys(" ");
        screen.buttonChange.click();
        String textAfter = screen.textToBeChanged.getText();
        assertEquals(textBefore, textAfter);
    }

    @Test
    @Order(3)
    public void testOpenNewActivity() {
        String textToSet = "Netology";
        screen.inputField.sendKeys(textToSet);
        screen.buttonActivity.click();
        String textAfter = screen.activityText.getText();
        assertEquals(textToSet, textAfter);
    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }
}

