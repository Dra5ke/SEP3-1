using System;

namespace Tier3ServerDatabase.view{
    public class ConsoleMovie : Tier3MovieCreatorView
    {
        public void Show(string text)
        {
            System.Console.WriteLine(text);
        }
    }
}