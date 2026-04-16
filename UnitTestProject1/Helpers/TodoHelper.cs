using OpenQA.Selenium;

namespace UnitTestProject1
{
    public class TodoHelper : HelperBase
    {
        public TodoHelper(AppManager manager)
            : base(manager)
        {
        }

        public void AddTodo(TodoData todo)
        {
            ClickNewTaskButtonOnMainpage();
            FillTodoDataOnMainpage(todo);
            ClickSubmitNewTaskButtonOnMainpage();
        }

        public void ClickNewTaskButtonOnMainpage()
        {
            driver.FindElement(By.XPath("//button[span[text()='Add task']]")).Click();
        }

        public void FillTodoDataOnMainpage(TodoData todo)
        {
            var titleElement = driver.FindElement(By.XPath("//div[@aria-label='Task name']"));
            js.ExecuteScript($"if(arguments[0].contentEditable === 'true') {{arguments[0].innerText = '{todo.Title}'}}", titleElement);

            var descriptionElement = driver.FindElement(By.XPath("//div[@aria-label='Description']"));
            js.ExecuteScript($"if(arguments[0].contentEditable === 'true') {{arguments[0].innerText = '{todo.Description}'}}", descriptionElement);
        }

        public void ClickSubmitNewTaskButtonOnMainpage()
        {
            driver.FindElement(By.XPath("//button[@type='submit' and span[text()='Add task']]")).Click();
        }
    }
}
