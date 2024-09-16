package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.TimeManager;

import java.time.Duration;

public class XPage {

    @FindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"Buscar y explorar\"]/android.view.View")
    private WebElement menuBuscarExplorar;
    @FindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"Tendencias\"]/android.widget.TextView")
    private WebElement moduloTendencias;
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.ScrollView/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[1]/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[4]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.TextView[1]")
    private WebElement itemTendenciaRandom;
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]")
    private WebElement itemTweetRandom;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Me gusta\"]/android.widget.FrameLayout[1]/android.widget.ImageView")
    private WebElement botonMeGusta;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Me gusta (Marcado como me gusta)\"]")
    private WebElement textoBotonMeGusta;

    private AndroidDriver<MobileElement> driver;

    public XPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void iniciarSesion(){
        driver.findElement(By.id("com.twitter.android:id/ocf_button")).click();
        driver.findElement(By.id("com.google.android.gms:id/account_display_name")).click();
        driver.findElement(By.id("com.google.android.gms:id/agree_and_share_button")).click();
    }
    public void seleccionarMenuBuscarExplorar(){
        menuBuscarExplorar.click();
        TimeManager.waitTime(2);
    }
    public void irATendencias(){
        TimeManager.waitTime(2);
        moduloTendencias.click();
        TimeManager.waitTime(2);
    }
    public void seleccionarTendencia(){
        swipeDown();
        itemTendenciaRandom.click();
        TimeManager.waitTime(4);
    }
    public void seleccionarTweet(){
        swipeDown();
        itemTweetRandom.click();
        TimeManager.waitTime(2);
    }
    public void darMeGusta(){
        botonMeGusta.click();
        TimeManager.waitTime(2);
    }
    public String tweetMeGusta(){
        String texto = textoBotonMeGusta.getAttribute("contentDescription");
        System.out.println(texto);
        return texto;
    }

    public void swipeDown() {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.9);
        int endY = (int) (size.height * 0.1);

        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();
        TimeManager.waitTime(2);
    }

}
