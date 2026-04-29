package ru.itis.todoist.helpers;

import org.openqa.selenium.By;
import ru.itis.todoist.AppManager;
import ru.itis.todoist.model.AccountData;

public class LoginHelper extends HelperBase {
    public LoginHelper(AppManager manager) {
        super(manager);
    }

    public void login(AccountData account) {
        manager.getNavigation().goToHomePage();
        clickLoginButtonOnHomepage();
        waitSeconds(10);
        fillCredentialsOnLoginPage(account.getUsername(), account.getPassword());
        clickLoginButtonOnLoginPage();
        waitSeconds(20);
    }

    public void clickLoginButtonOnHomepage() {
        driver.findElement(By.linkText("Log in")).click();
    }

    public void fillCredentialsOnLoginPage(String username, String password) {
        driver.findElement(By.id("element-0")).sendKeys(username);
        driver.findElement(By.id("element-2")).sendKeys(password);
    }

    public void clickLoginButtonOnLoginPage() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }
}
