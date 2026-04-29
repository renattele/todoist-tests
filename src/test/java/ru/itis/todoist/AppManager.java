package ru.itis.todoist;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import ru.itis.todoist.helpers.LoginHelper;
import ru.itis.todoist.helpers.NavigationHelper;
import ru.itis.todoist.helpers.TodoHelper;

import java.util.HashMap;
import java.util.Map;

public class AppManager {
    private static final ThreadLocal<AppManager> APP = new ThreadLocal<>();

    private final WebDriver driver;
    private final JavascriptExecutor js;
    private final NavigationHelper navigation;
    private final LoginHelper auth;
    private final TodoHelper todo;
    private final Map<String, Object> vars = new HashMap<>();

    public AppManager() {
        FirefoxOptions options = new FirefoxOptions();

        driver = new FirefoxDriver(options);
        js = (JavascriptExecutor) driver;
        driver.manage().window().setSize(new Dimension(1252, 694));

        String baseUrl = "https://www.todoist.com/auth/login";
        todo = new TodoHelper(this);
        auth = new LoginHelper(this);
        navigation = new NavigationHelper(this, baseUrl);
    }

    public static AppManager getInstance() {
        if (APP.get() == null) {
            APP.set(new AppManager());
        }
        return APP.get();
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

    public void clearBrowserState() {
        driver.get("https://www.todoist.com");
        driver.manage().deleteAllCookies();
        js.executeScript("window.localStorage.clear(); window.sessionStorage.clear();");
    }

    public void stop() {
        driver.quit();
        APP.remove();
    }
}
