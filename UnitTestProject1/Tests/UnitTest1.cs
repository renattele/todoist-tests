using NUnit.Framework;

namespace UnitTestProject1
{
    [TestFixture]
    public class LoginTest : TestBase
    {
        private AccountData account = new AccountData("nasasas930@fpxnet.com", "EYWRnnVqgNEdgN3Cdr62Lolqle7VOryQ");
        private TodoData todo = new TodoData("TITLE ", "DESCRIPTION");

        public void Login()
        {
            app.Auth.Login(account);
        }

        [Test]
        public void CreateToDo()
        {
            app.Auth.Login(account);
            app.Todo.AddTodo(todo);
            app.Todo.Wait(5);
        }
    }
}
