package models.components.login;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginFormComponent {

    private final AppiumDriver<MobileElement> appiumDriver;
    private final static By usernameSel = MobileBy.AccessibilityId("input-email");
    private final static By incorrectEmailTxtSel = MobileBy.xpath("//*[contains(@text, 'Please enter a valid email address')]");
    private final static By passwordSel = MobileBy.AccessibilityId("input-password");
    private final static By incorrectPasswordTxtSel = MobileBy.xpath("//*[contains(@text, 'Please enter at least 8 characters')]");
    private final static By loginSuccessTxt = MobileBy.id("android:id/alertTitle");
    private final static By loginBtnSel = MobileBy.AccessibilityId("button-LOGIN");

    public LoginFormComponent(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void inputUsername(String usernameTxt) {
        if (!usernameTxt.isEmpty()) appiumDriver.findElement(usernameSel).sendKeys(usernameTxt);
    }

    public String getInvalidEmailStr(){
        return appiumDriver.findElement(incorrectEmailTxtSel).getText();
    }

    public void inputPassword(String passwordTxt) {
        if (!passwordTxt.isEmpty()) appiumDriver.findElement(passwordSel).sendKeys(passwordTxt);
    }

    public String getInvalidPasswordStr(){
        return appiumDriver.findElement(incorrectPasswordTxtSel).getText();
    }

    public String getValidLoginStr(){
        WebDriverWait wait = new WebDriverWait(appiumDriver,5);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginSuccessTxt));
        return appiumDriver.findElement(loginSuccessTxt).getText();
    }


    public void clickOnLoginBtn() {
        appiumDriver.findElement(loginBtnSel).click();
    }

}