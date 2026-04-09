using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.Remote;
using OpenQA.Selenium.Support.UI;
using OpenQA.Selenium.Interactions;
using NUnit.Framework;

namespace UnitTestProject1
{
    public class TestBase
    {
        public IWebDriver driver;
        public IDictionary<string, object> vars { get; set; }
        public IJavaScriptExecutor js;

        public void Login(AccountData account)
        {
            NavigateToHomepage();
            ClickLogInButtonOnHomepage();
            Wait(10);
            FillCredentialsOnLoginPage(username: account.Username, password: account.Password);
            ClickLogInButtonOnLoginPage();
            Wait(20);
        }

        public void AddTodo(TodoData todo)
        {
            ClickNewTaskButtonOnMainpage();
            FillTodoDataOnMainpage(todo);
            ClickSubmitNewTaskButtonOnMainpage();
        }

        public void ResizeWindowToDesktopMode()
        {
            driver.Manage().Window.Size = new System.Drawing.Size(1252, 694);
        }


        public void ClickLogInButtonOnLoginPage()
        {
            driver.FindElement(By.XPath("//button[@type='submit']")).Click();
        }

        public void FillCredentialsOnLoginPage(String username, String password)
        {
            driver.FindElement(By.Id("element-0")).SendKeys(username);
            driver.FindElement(By.Id("element-2")).SendKeys(password);
        }

        public static void Wait(int seconds)
        {
            Thread.Sleep(seconds * 1000);
        }

        public void ClickLogInButtonOnHomepage()
        {
            driver.FindElement(By.LinkText("Log in")).Click();
        }

        public void ClickNewTaskButtonOnMainpage()
        {
            driver.FindElement(By.XPath("//button[span[text()='Add task']]")).Click();
        }

        public void FillTodoDataOnMainpage(TodoData todo)
        {
            {
                var element = driver.FindElement(By.XPath("//div[@aria-label='Task name']"));
                js.ExecuteScript($"if(arguments[0].contentEditable === 'true') {{arguments[0].innerText = '{todo.Title}'}}", element);
            }
            {
                var element = driver.FindElement(By.XPath("//div[@aria-label='Description']"));
                js.ExecuteScript($"if(arguments[0].contentEditable === 'true') {{arguments[0].innerText = '{todo.Description}'}}", element);
            }
        }
        public void ClickSubmitNewTaskButtonOnMainpage()
        {
            driver.FindElement(By.XPath("//button[@type='submit' and span[text()='Add task']]")).Click();
        }

        public void NavigateToHomepage()
        {
            driver.Navigate().GoToUrl("https://www.todoist.com/");
        }
    }
}
