using System;
using System.Net;
using System.Net.Sockets;
using System.Text;
using Newtonsoft.Json;
using Tier3ServerDatabase.common;
using Tier3ServerDatabase.controller;
using Tier3ServerDatabase.view;
using Tier3ServerDatabase.database;
namespace Tier3ServerDatabase{
public class Program {

    public static void Main (String[] args) {
       Tier3MovieCreatorView view = new ConsoleMovie();
		//Remove Hardcoding
		Tier3MovieCreatorController controller = new Tier3MovieCreatorController(view, "localhost", 1097);
    }
}
}