using System;
using System.Collections.Generic;
using System.Net.Sockets;
using System.Text;
using Newtonsoft.Json;
using Tier2WebApi.Common;

namespace Tier2WebApi.Communication{
    public class Tier2JavaProvider{
        private List<ScheduledMovie> schedule;

        public List<ScheduledMovie> Schedule { get => schedule; set => schedule = value; }

        public Tier2JavaProvider()
        {
            Package Answer;
            Package GETALLSCHEDULE = new Package("GETALLSCHEDULE");
            //Connect to localhost on port 1100
            TcpClient client = new TcpClient("127.0.0.1", 1100);
            //Get the stream for writing and reading
            NetworkStream NetworkStream = client.GetStream();
            //Serialize the Package in json format
            string json = JsonConvert.SerializeObject(GETALLSCHEDULE);
            //Setting up the byte array to send data
            byte[] bytesToSend = Encoding.UTF8.GetBytes(json, 0, json.Length);
            //Send the data
            NetworkStream.Write(bytesToSend);
            //Send the receiver an enter so it knows it's the end of the line
            NetworkStream.Write(Encoding.UTF8.GetBytes(Environment.NewLine));
            //Create the array to receive the response
            Byte[] bytes = new byte[client.ReceiveBufferSize];
            //Read from the stream and save them in the byte arrray
            NetworkStream.Read(bytes, 0, client.ReceiveBufferSize);
            //Decode the reply from UTF8
            String reply = Encoding.UTF8.GetString(bytes, 2, client.ReceiveBufferSize - 2);
            //Decode the reply from json
            Answer = JsonConvert.DeserializeObject<Package>(reply);
            Schedule = Answer.ScheduleList;
            //Closing the stream and client connection
            client.GetStream().Close();
            client.Close();
        }
    }
}