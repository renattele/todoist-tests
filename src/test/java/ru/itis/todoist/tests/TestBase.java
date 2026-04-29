package ru.itis.todoist.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.itis.todoist.AppManager;

public class TestBase {
    protected static AppManager app;

    @BeforeAll
    public static void setUp() {
        app = AppManager.getInstance();
    }

    @BeforeEach
    public void clearBrowserState() {
        app.clearBrowserState();
    }

    @AfterAll
    public static void tearDown() {
        if (app != null) {
            app.stop();
        }
    }
}
