package ru.itis.todoist.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.itis.todoist.AppManager;

public class TestBase {
    protected AppManager app;

    @BeforeEach
    public void setUp() {
        app = new AppManager();
    }

    @AfterEach
    public void tearDown() {
        if (app != null) {
            app.stop();
        }
    }
}
