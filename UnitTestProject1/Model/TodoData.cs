using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UnitTestProject1
{
    public class TodoData
    {
        public TodoData(string content)
        {
            Title = content;
        }
        public TodoData(string content, string description)
        {
            Title = content;
            Description = description;
        }
        public string Title { get; set; }

        public string Description { get; set; }
    }
}
