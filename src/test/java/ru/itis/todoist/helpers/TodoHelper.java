package ru.itis.todoist.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import ru.itis.todoist.AppManager;
import ru.itis.todoist.model.TodoData;

import java.util.List;

public class TodoHelper extends HelperBase {
    public TodoHelper(AppManager manager) {
        super(manager);
    }

    public void addTodo(TodoData todo) {
        clickNewTaskButtonOnMainPage();
        fillTodoDataOnMainPage(todo);
        clickSubmitNewTaskButtonOnMainPage();
    }

    public void deleteTodo(String title) {
        openTodo(title);
        waitSeconds(1);
        driver.findElement(By.xpath("(//button[@aria-label='More actions'])[last()]")).click();
        waitSeconds(1);
        driver.findElement(By.xpath("//div[@data-destructive]")).click();
        waitSeconds(1);
        driver.findElement(By.xpath("//button[span[text()='Delete']]")).click();
        waitSeconds(3);
    }

    public int getInboxTaskCount() {
        waitSeconds(2);
        try {
            String label = driver.findElement(By.xpath("//li[@id='filter_inbox']//a[contains(@aria-label, 'Inbox')]")).getAttribute("aria-label");
            return Integer.parseInt(label.replaceAll("\\D+", ""));
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isTodoDisplayed(String title) {
        return !driver.findElements(todoTitleLocator(title)).isEmpty();
    }

    public void openTodo(String title) {
        driver.findElement(todoTitleLocator(title)).click();
    }

    public void clickNewTaskButtonOnMainPage() {
        waitSeconds(2);
        clickFirstAvailable(List.of(
                By.xpath("//button[.//span[normalize-space()='Add task'] or normalize-space()='Add task']"),
                By.cssSelector("[data-testid='add-task-button']"),
                By.cssSelector(".plus_add_button")
        ));
        waitSeconds(1);
    }

    public void fillTodoDataOnMainPage(TodoData todo) {
        WebElement titleElement = driver.findElement(By.xpath("//div[@aria-label='Task name']"));
        setContentEditableText(titleElement, todo.getTitle());

        if (!todo.getDescription().isEmpty()) {
            driver.findElements(By.xpath("//div[@aria-label='Description']"))
                    .stream()
                    .findFirst()
                    .ifPresent(descriptionElement -> {
                        try {
                            setContentEditableText(descriptionElement, todo.getDescription());
                        } catch (StaleElementReferenceException ignored) {
                            driver.findElements(By.xpath("//div[@aria-label='Description']"))
                                    .stream()
                                    .findFirst()
                                    .ifPresent(refreshedDescription -> setContentEditableText(refreshedDescription, todo.getDescription()));
                        }
                    });
        }
    }

    public void clickSubmitNewTaskButtonOnMainPage() {
        waitSeconds(1);
        driver.findElement(By.xpath("//button[@data-testid='task-editor-submit-button' or (@type='submit' and .//span[text()='Add task'])]")).click();
        waitSeconds(3);
    }

    private void setContentEditableText(WebElement element, String value) {
        waitSeconds(1);
        element.click();
        element.sendKeys(value);
    }

    private void clickFirstAvailable(List<By> locators) {
        for (By locator : locators) {
            List<WebElement> elements = driver.findElements(locator);
            if (!elements.isEmpty()) {
                elements.get(0).click();
                return;
            }
        }
    }

    private By todoTitleLocator(String title) {
        return By.xpath("//*[contains(@class, 'task_content') and contains(normalize-space(.), '" + title + "')]");
    }
}
