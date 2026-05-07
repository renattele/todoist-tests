package ru.itis.todoist.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.itis.todoist.AppManager;
import ru.itis.todoist.model.AccountData;

import java.util.Locale;

public class LoginHelper extends HelperBase {
    private static final By LOGGED_IN_INDICATOR = By.xpath("//button[span[text()='Add task']]");
    private static final By LOGIN_FIELD_LOCATOR = By.id("element-0");
    private static final By PASSWORD_FIELD_LOCATOR = By.id("element-2");
    private static final By LOGIN_ERROR_LOCATOR = By.cssSelector("[role='alert']");

    public LoginHelper(AppManager manager) {
        super(manager);
    }

    public void login(AccountData account) {
        manager.getNavigation().goToHomePage();
        waitSeconds(3);

        if (isLoggedIn()) {
            if (isLoggedIn(account.getUsername())) {
                return;
            }

            logout();
            manager.getNavigation().goToHomePage();
            waitSeconds(3);
        }

        if (isLoggedIn(account.getUsername())) {
            return;
        }

        fillCredentialsOnLoginPage(account.getUsername(), account.getPassword());
        clickLoginButtonOnLoginPage();
        waitSeconds(10);
    }

    public void logout() {
        if (!isLoggedIn()) {
            return;
        }

        manager.clearBrowserState();
        manager.getNavigation().goToHomePage();
        waitSeconds(3);
    }

    public void fillCredentialsOnLoginPage(String username, String password) {
        WebElement loginField = driver.findElement(LOGIN_FIELD_LOCATOR);
        WebElement passwordField = driver.findElement(PASSWORD_FIELD_LOCATOR);

        loginField.clear();
        loginField.sendKeys(username);

        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButtonOnLoginPage() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    public boolean isLoggedIn() {
        return !driver.findElements(LOGGED_IN_INDICATOR).isEmpty();
    }

    public boolean isLoggedIn(String username) {
        if (!isLoggedIn()) {
            return false;
        }
        String nickname = username.split("@")[0];

        String bodyText = driver.findElement(By.tagName("body")).getText().toLowerCase(Locale.ROOT);
        return bodyText.contains(nickname);
    }

    public boolean hasLoginError() {
        if (!driver.findElements(LOGIN_ERROR_LOCATOR).isEmpty()) {
            return true;
        }

        String bodyText = driver.findElement(By.tagName("body")).getText().toLowerCase(Locale.ROOT);
        return bodyText.contains("wrong email or password");
    }
}
