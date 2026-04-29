package ru.itis.todoist.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.itis.todoist.AppManager;
import ru.itis.todoist.model.TodoData;

public class TodoHelper extends HelperBase {
    public TodoHelper(AppManager manager) {
        super(manager);
    }

    public void addTodo(TodoData todo) {
        clickNewTaskButtonOnMainPage();
        fillTodoDataOnMainPage(todo);
        clickSubmitNewTaskButtonOnMainPage();
    }

    public void clickNewTaskButtonOnMainPage() {
        driver.findElement(By.xpath("//button[span[text()='Add task']]")).click();
    }

    public void fillTodoDataOnMainPage(TodoData todo) {
        WebElement titleElement = driver.findElement(By.xpath("//div[@aria-label='Task name']"));
        setContentEditableText(titleElement, todo.getTitle());

        WebElement descriptionElement = driver.findElement(By.xpath("//div[@aria-label='Description']"));
        setContentEditableText(descriptionElement, todo.getDescription());
    }

    public void clickSubmitNewTaskButtonOnMainPage() {
        driver.findElement(By.xpath("//button[@type='submit' and span[text()='Add task']]")).click();
    }

    private void setContentEditableText(WebElement element, String value) {
        js.executeScript(
                "if (arguments[0].contentEditable === 'true') { arguments[0].innerText = arguments[1]; }",
                element,
                value
        );
    }
}
