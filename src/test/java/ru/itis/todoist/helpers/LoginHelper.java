package ru.itis.todoist.helpers;

import org.openqa.selenium.By;
import ru.itis.todoist.AppManager;
import ru.itis.todoist.model.AccountData;

public class LoginHelper extends HelperBase {
    public LoginHelper(AppManager manager) {
        super(manager);
    }

    public void login(AccountData account) {
        if (isLoggedIn()) {
            return;
        }

        manager.getNavigation().goToHomePage();
        if (isLoggedIn()) {
            return;
        }

        waitSeconds(5);
        if (isLoggedIn()) {
            return;
        }
        fillCredentialsOnLoginPage(account.getUsername(), account.getPassword());
        clickLoginButtonOnLoginPage();
        waitSeconds(10);
    }

    public void fillCredentialsOnLoginPage(String username, String password) {
        driver.findElement(By.id("element-0")).sendKeys(username);
        driver.findElement(By.id("element-2")).sendKeys(password);
    }

    public void clickLoginButtonOnLoginPage() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    public boolean isLoggedIn() {
        return !driver.findElements(By.xpath("//button[span[text()='Add task']]")).isEmpty();
    }
}
