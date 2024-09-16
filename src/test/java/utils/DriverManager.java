package utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {

    private static AndroidDriver<MobileElement> driver;

    public static AndroidDriver<MobileElement> getAndroidDriver() {
        if (driver == null) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", "Android");
            caps.setCapability("platformVersion", "11.0");
            caps.setCapability("deviceName", "R28M70KGGEN");
            caps.setCapability("appPackage", "com.twitter.android");
            //caps.setCapability("appWaitActivity", "com.twitter.android.onboarding.core.common.CtaSubtaskActivity");
            caps.setCapability("appWaitActivity", "com.twitter.app.main.MainActivity");
            caps.setCapability("automationName", "UiAutomator2");

            caps.setCapability("app", "D:\\personal\\jobs\\seek\\X.apk");
            try {
                driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

}
