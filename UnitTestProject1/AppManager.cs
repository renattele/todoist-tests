using System;
using System.Collections.Generic;
using System.Text;
using OpenQA.Selenium;
using OpenQA.Selenium.Firefox;

namespace UnitTestProject1
{
    public class AppManager
    {
        private IWebDriver driver;
        private IJavaScriptExecutor js;
        private StringBuilder verificationErrors;
        private string baseURL;

        private NavigationHelper navigation;
        private LoginHelper auth;
        private TodoHelper todo;

        public IDictionary<string, object> Vars { get; set; }

        public AppManager()
        {
            driver = new FirefoxDriver();
            js = (IJavaScriptExecutor)driver;
            Vars = new Dictionary<string, object>();
            driver.Manage().Window.Size = new System.Drawing.Size(1252, 694);

            baseURL = "https://www.todoist.com/";
            verificationErrors = new StringBuilder();

            todo = new TodoHelper(this);
            auth = new LoginHelper(this);
            navigation = new NavigationHelper(this, baseURL);
        }

        public IWebDriver Driver
        {
            get
            {
                return driver;
            }
        }

        public IJavaScriptExecutor Js
        {
            get
            {
                return js;
            }
        }

        public NavigationHelper Navigation
        {
            get
            {
                return navigation;
            }
        }

        public LoginHelper Auth
        {
            get
            {
                return auth;
            }
        }

        public TodoHelper Todo
        {
            get
            {
                return todo;
            }
        }

        public void Stop()
        {
            driver.Quit();
        }
    }
}
