package ru.itis.todoist.tests;

import org.junit.jupiter.api.Test;
import ru.itis.todoist.model.AccountData;
import ru.itis.todoist.model.TodoData;

public class LoginTest extends TestBase {
    private final AccountData account = new AccountData(
            "nasasas930@fpxnet.com",
            "EYWRnnVqgNEdgN3Cdr62Lolqle7VOryQ"
    );
    private final TodoData todo = new TodoData("TITLE ", "DESCRIPTION");

    public void login() {
        app.getAuth().login(account);
    }

    @Test
    public void createTodo() {
        app.getAuth().login(account);
        app.getTodo().addTodo(todo);
        app.getTodo().waitSeconds(5);
    }
}
