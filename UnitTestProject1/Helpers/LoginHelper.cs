using System;
using OpenQA.Selenium;

namespace UnitTestProject1
{
    public class LoginHelper : HelperBase
    {
        public LoginHelper(AppManager manager)
            : base(manager)
        {
        }

        public void Login(AccountData account)
        {
            manager.Navigation.GoToHomePage();
            ClickLogInButtonOnHomepage();
            Wait(10);
            FillCredentialsOnLoginPage(username: account.Username, password: account.Password);
            ClickLogInButtonOnLoginPage();
            Wait(20);
        }

        public void ClickLogInButtonOnHomepage()
        {
            driver.FindElement(By.LinkText("Log in")).Click();
        }

        public void FillCredentialsOnLoginPage(String username, String password)
        {
            driver.FindElement(By.Id("element-0")).SendKeys(username);
            driver.FindElement(By.Id("element-2")).SendKeys(password);
        }

        public void ClickLogInButtonOnLoginPage()
        {
            driver.FindElement(By.XPath("//button[@type='submit']")).Click();
        }
    }
}
