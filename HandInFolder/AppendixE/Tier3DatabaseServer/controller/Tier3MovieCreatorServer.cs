using System;
using System.Net;
using System.Net.Sockets;
using System.Threading;

namespace Tier3ServerDatabase.controller {

    public class Tier3MovieCreatorServer{

        public Tier3MovieCreatorController controller;
        public Socket listener;
        public Tier3MovieCreatorServer(Tier3MovieCreatorController controller)
        {
            this.controller = controller;
        }

        public void StartListening (string adress, int port) {
        // Establish the local endpoint for the socket.  
        // Dns.GetHostName returns the name of the   
        // host running the application.  
        IPHostEntry ipHostInfo = Dns.GetHostEntry (adress);
        IPAddress ipAddress = ipHostInfo.AddressList[1];
        IPEndPoint localEndPoint = new IPEndPoint (ipAddress, port);

        // Create a TCP/IP socket.  
        listener = new Socket (ipAddress.AddressFamily,
            SocketType.Stream, ProtocolType.Tcp);
            try {
            listener.Bind (localEndPoint);
            listener.Listen (10);

            // Start listening for connections.  
            while (true) {
                // Program is suspended while waiting for an incoming connection.  
                Socket handler = listener.Accept ();
                // Starting a new thread with the client
                Tier3MovieCreatorThreadHandler managingCommunication = new Tier3MovieCreatorThreadHandler(handler, controller.View, controller.Database);
                //Thread for the method handling the communication
                Thread t = new Thread(() => managingCommunication.HandleCommunication());
                t.Start();
               
            }

        } catch (Exception e) {
            Console.WriteLine (e.ToString ());
        };
        }

       
    }
}