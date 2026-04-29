package ru.itis.todoist.tests;

import org.junit.jupiter.api.Test;
import ru.itis.todoist.model.AccountData;
import ru.itis.todoist.model.TodoData;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends TestBase {
    private final AccountData account = new AccountData(
            "nasasas930@fpxnet.com",
            "EYWRnnVqgNEdgN3Cdr62Lolqle7VOryQ"
    );

    public void login() {
        app.getAuth().login(account);
    }

    @Test
    public void createTodo() {
        app.getAuth().login(account);
        TodoData todo = new TodoData(uniqueTitle("TITLE"), "DESCRIPTION");
        int todosBeforeCreation = app.getTodo().getInboxTaskCount();

        app.getTodo().addTodo(todo);

        app.getTodo().waitSeconds(2);
        assertTrue(app.getTodo().getInboxTaskCount() > todosBeforeCreation);
    }

    @Test
    public void deleteTodo() {
        app.getAuth().login(account);
        TodoData todo = new TodoData(uniqueTitle("DELETE"), "DESCRIPTION");

        app.getTodo().addTodo(todo);
        app.getTodo().waitSeconds(2);
        app.getTodo().deleteTodo(todo.getTitle());

        app.getTodo().waitSeconds(2);
        assertTrue(!app.getTodo().isTodoDisplayed(todo.getTitle()));
    }

    private String uniqueTitle(String prefix) {
        return prefix + " " + System.currentTimeMillis();
    }
}
