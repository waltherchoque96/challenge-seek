package tests;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.XPage;
import utils.DocumentManager;
import utils.DriverManager;
import utils.TimeManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeTest {

    private AndroidDriver<MobileElement> driver;
    private XPage homePage;
    DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
    Date date = new Date();

    @Before
    public void setUp() {
        driver = DriverManager.getAndroidDriver();
        homePage = new XPage(driver);
        TimeManager.waitTime(5);
    }

    @Test
    public void testX() {

        String imgInit = DocumentManager.captureScreenshot("evidencias/evidencia0");

        homePage.seleccionarMenuBuscarExplorar();
        String imgHome = DocumentManager.captureScreenshot("evidencias/evidencia1");

        homePage.irATendencias();
        String imgBuscador = DocumentManager.captureScreenshot("evidencias/evidencia2");

        homePage.seleccionarTendencia();
        String imgTendencia = DocumentManager.captureScreenshot("evidencias/evidencia3");

        homePage.seleccionarTweet();
        String imgTweet = DocumentManager.captureScreenshot("evidencias/evidencia4");

        homePage.darMeGusta();
        String imgLiked = DocumentManager.captureScreenshot("evidencias/evidencia5");

        Assert.assertEquals(homePage.tweetMeGusta(),"Me gusta (Marcado como me gusta)");

        String[] imagePaths = {imgInit, imgHome, imgBuscador, imgTendencia, imgTweet, imgLiked};
        DocumentManager.createWordDocument(imagePaths, "evidencias/Evidencias"+dateFormat.format(date)+".docx");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
