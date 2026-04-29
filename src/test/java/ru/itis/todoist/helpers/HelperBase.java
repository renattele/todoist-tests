package ru.itis.todoist.helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.itis.todoist.AppManager;

import java.time.Duration;

public class HelperBase {
    protected final AppManager manager;
    protected final WebDriver driver;
    protected final JavascriptExecutor js;

    public HelperBase(AppManager manager) {
        this.manager = manager;
        this.driver = manager.getDriver();
        this.js = manager.getJs();
    }

    public void waitSeconds(int seconds) {
        try {
            Thread.sleep(Duration.ofSeconds(seconds).toMillis());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Wait was interrupted", e);
        }
    }
}
