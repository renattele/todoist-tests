package ru.itis.todoist.tests;

import org.junit.jupiter.api.Test;
import ru.itis.todoist.Settings;
import ru.itis.todoist.model.AccountData;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends TestBase {
    @Test
    public void loginWithValidData() {
        app.getAuth().logout();

        app.getAuth().login(Settings.getValidAccount());

        assertTrue(app.getAuth().isLoggedIn());
        assertTrue(app.getAuth().isLoggedIn(Settings.getLogin()));
    }

    @Test
    public void loginWithInvalidData() {
        app.getAuth().logout();
        AccountData invalidAccount = new AccountData(Settings.getLogin(), Settings.getPassword() + "_invalid");

        app.getAuth().login(invalidAccount);

        assertFalse(app.getAuth().isLoggedIn());
        assertTrue(app.getAuth().hasLoginError());
    }
}
