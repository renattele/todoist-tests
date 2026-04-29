package ru.itis.todoist;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.itis.todoist.helpers.LoginHelper;
import ru.itis.todoist.helpers.NavigationHelper;
import ru.itis.todoist.helpers.TodoHelper;

import java.util.HashMap;
import java.util.Map;

public class AppManager {
    private final WebDriver driver;
    private final JavascriptExecutor js;
    private final NavigationHelper navigation;
    private final LoginHelper auth;
    private final TodoHelper todo;
    private final Map<String, Object> vars = new HashMap<>();

    public AppManager() {
        driver = new FirefoxDriver();
        js = (JavascriptExecutor) driver;
        driver.manage().window().setSize(new Dimension(1252, 694));

        String baseUrl = "https://www.todoist.com/";
        todo = new TodoHelper(this);
        auth = new LoginHelper(this);
        navigation = new NavigationHelper(this, baseUrl);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public JavascriptExecutor getJs() {
        return js;
    }

    public NavigationHelper getNavigation() {
        return navigation;
    }

    public LoginHelper getAuth() {
        return auth;
    }

    public TodoHelper getTodo() {
        return todo;
    }

    public Map<String, Object> getVars() {
        return vars;
    }

    public void stop() {
        driver.quit();
    }
}
