package ru.itis.todoist.helpers;

import ru.itis.todoist.AppManager;

public class NavigationHelper extends HelperBase {
    private final String baseUrl;

    public NavigationHelper(AppManager manager, String baseUrl) {
        super(manager);
        this.baseUrl = baseUrl;
    }

    public void goToHomePage() {
        driver.navigate().to(baseUrl);
    }
}
