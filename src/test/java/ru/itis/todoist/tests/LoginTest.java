package ru.itis.todoist.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.itis.todoist.model.AccountData;
import ru.itis.todoist.model.TodoData;

import javax.xml.parsers.DocumentBuilderFactory;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends TestBase {
    private final AccountData account = new AccountData(
            "nasasas930@fpxnet.com",
            "EYWRnnVqgNEdgN3Cdr62Lolqle7VOryQ"
    );

    public void login() {
        app.getAuth().login(account);
    }

    @ParameterizedTest
    @MethodSource("todoDataFromXmlFile")
    public void createTodo(TodoData todo) {
        app.getAuth().login(account);
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
        assertFalse(app.getTodo().isTodoDisplayed(todo.getTitle()));
    }

    static Stream<TodoData> todoDataFromXmlFile() throws Exception {
        Path file = Path.of("src", "test", "resources", "todos.xml");
        NodeList todoNodes = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(file.toFile())
                .getDocumentElement()
                .getElementsByTagName("todo");
        List<TodoData> todos = new ArrayList<>();

        for (int i = 0; i < todoNodes.getLength(); i++) {
            Element todoElement = (Element) todoNodes.item(i);
            todos.add(new TodoData(
                    getText(todoElement, "title"),
                    getText(todoElement, "description")
            ));
        }

        return todos.stream();
    }

    private static String getText(Element element, String tagName) {
        return element.getElementsByTagName(tagName).item(0).getTextContent();
    }

    private String uniqueTitle(String prefix) {
        return prefix + " " + System.currentTimeMillis();
    }
}
