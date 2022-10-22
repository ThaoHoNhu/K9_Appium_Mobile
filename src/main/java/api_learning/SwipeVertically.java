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

public class SwipeVertically {
    //implicit wait, explicit wait
    public static void main(String[] args) {

        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);

        try {
            //Navigate Forms screen
            MobileElement navLoginScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Forms"));
            navLoginScreenBtn.click();

            // Wait until user is on Forms screen
            WebDriverWait wait = new WebDriverWait(appiumDriver, 10L);
            wait.until(ExpectedConditions
                    .visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Form component\")")));

            // Get Mobile window Size
            Dimension windowSize = appiumDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            // Calculate touch points
            int xStartPoint = 50 * screenWidth / 100;
            int xEndPoint = 50 * screenWidth / 100;

            int yStartPoint = 50 * screenHeight / 100;
            int yEndPoint = 10 * screenHeight / 100;

            // Convert coordinates -> PointOption
            PointOption startPoint = new PointOption<>().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption<>().withCoordinates(xEndPoint, yEndPoint);

            // Swipe down | trick: revert coordinates
            /*
             * .press(endPoint)
             *   .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
             * Can be replaced to: longPress
             *
             * */
//            touchAction
//                    .longPress(endPoint)
//                    .moveTo(startPoint)
//                    .release()
//                    .perform();

            //input text field
            MobileElement txtInput = appiumDriver.findElement(MobileBy.AccessibilityId("text-input"));
            txtInput.sendKeys("thao");
            MobileElement txtInputResult = appiumDriver.findElement(MobileBy.AccessibilityId("input-text-result"));
            System.out.println(txtInputResult.getText());

            //Switch
            MobileElement btnSwitch = appiumDriver.findElement(MobileBy.AccessibilityId("switch"));
            btnSwitch.click();
            MobileElement txtSwitch = appiumDriver.findElement(MobileBy.AccessibilityId("switch-text"));
            System.out.println(txtSwitch.getText().trim());

            //dropdown
            MobileElement dropdownElem = appiumDriver.findElement(MobileBy.
                    xpath("//android.view.ViewGroup[@content-desc=\"Dropdown\"]/android.view.ViewGroup/android.widget.EditText"));
            dropdownElem.click();
            //choose appium is
            List<MobileElement> dropDownValueList = appiumDriver.findElements(MobileBy.xpath("//android.widget.CheckedTextView"));
            dropDownValueList.get(2).click();

            // Using TouchAction to swipe
            TouchAction touchAction = new TouchAction(appiumDriver);
            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                    .moveTo(endPoint)
                    .release()
                    .perform();

            // Click on Btn-active
            MobileElement activeBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("button-Active"));
            activeBtnElem.click();

            //get text


            //Click on Btn-ok
            MobileElement okBtnElem = appiumDriver.findElement(MobileBy.xpath("//android.widget.Button[3]"));
            okBtnElem.click();

            //debug purpose only
            Thread.sleep(3000);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
