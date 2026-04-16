using System.Threading;
using OpenQA.Selenium;

namespace UnitTestProject1
{
    public class HelperBase
    {
        protected AppManager manager;
        protected IWebDriver driver;
        protected IJavaScriptExecutor js;

        public HelperBase(AppManager manager)
        {
            this.manager = manager;
            driver = manager.Driver;
            js = manager.Js;
        }

        public void Wait(int seconds)
        {
            Thread.Sleep(seconds * 1000);
        }
    }
}
