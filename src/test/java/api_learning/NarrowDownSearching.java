package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;

import java.time.Duration;
import java.util.List;

public class NarrowDownSearching {
    //implicit wait, explicit wait
    public static void main(String[] args) {

        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            //Navigate Forms screen
//            MobileElement navSwipeScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Swipe"));
//            navSwipeScreenBtn.click();
//
//            // Wait until user is on Swipe screen
//            WebDriverWait wait = new WebDriverWait(appiumDriver, 10L);
//            wait.until(ExpectedConditions
//                    .visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector()." +
//                            "textContains(\"Swipe horizontal\")")));

            // Get Mobile window Size
            Dimension windowSize = appiumDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            // Calculate touch points
            int xStartPoint = 70 * screenWidth / 100;
            int xEndPoint = 10 * screenWidth / 100;

            int yStartPoint = 0;
            int yEndPoint = 50 * screenHeight / 100;

            // Convert coordinates -> PointOption
            PointOption startPoint = new PointOption<>().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption<>().withCoordinates(xEndPoint, yEndPoint);

            // Using TouchAction to swipe
            TouchAction touchAction = new TouchAction(appiumDriver);
            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                    .moveTo(endPoint)
                    .release()
                    .perform();

            //get list notification
            List<MobileElement> notificationElems =
                    appiumDriver.findElements(MobileBy.id("android:id/status_bar_latest_event_content"));
            for (MobileElement notificationElem : notificationElems) {
                MobileElement titleElem = notificationElem.findElement(MobileBy.id("android:id/app_name_text"));
                MobileElement contentElem = notificationElem.findElement(MobileBy.id("android:id/text"));

                System.out.println("Title: " + titleElem.getText());
                System.out.println("Content: " + contentElem.getText());
            }
            //debug purpose only
            Thread.sleep(3000);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
