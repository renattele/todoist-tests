package ru.itis.todoist.tests;

import org.junit.jupiter.api.BeforeEach;
import ru.itis.todoist.Settings;
import ru.itis.todoist.model.AccountData;

public class AuthBase extends TestBase {
    protected final AccountData account = Settings.getValidAccount();

    @BeforeEach
    public void login() {
        app.getAuth().login(account);
    }
}
