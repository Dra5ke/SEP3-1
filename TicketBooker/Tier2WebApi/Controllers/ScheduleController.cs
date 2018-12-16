using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using Tier2WebApi.Common;
using Tier2WebApi.Communication;

namespace Tier2WebApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ScheduleController : ControllerBase
    {
        private List<ScheduledMovie> Schedule;

        public ScheduleController(Tier2JavaProvider provider)
        {
            Schedule = provider.Schedule;
        }
        // GET api/schedule
        [HttpGet]
        public List<ScheduledMovie> Get()
        {
            return Schedule;
        }

        // PUT api/schedule
        //[HttpPut("{id}")] ?id=bla&id2=bla2
        [HttpPut("{id}")]
        public void Put(int id, [FromBody]int id2)
        {   
            //Book the seat chosen by the client
            Schedule[id].Seats.ElementAt(id2).Booked = true;
            //Prepare the package to update the values in the database
            Package UPDATECHEDULE = new Package("UPDATESCHEDULE", null, null, null, Schedule);
            //Connect back to the java server
            TcpClient client = new TcpClient("127.0.0.1", 1100);
            //Send the package, look in Tier2JavaProvider for more explanations
            NetworkStream NetworkStream = client.GetStream();
            string json = JsonConvert.SerializeObject(UPDATECHEDULE);
            byte[] bytesToSend = Encoding.UTF8.GetBytes(json, 0, json.Length);
            NetworkStream.Write(bytesToSend);
            NetworkStream.Write(Encoding.UTF8.GetBytes(Environment.NewLine));
            // Reading so it doesn't throw error and block the whole data flow
            Byte[] bytes = new byte[client.ReceiveBufferSize];
            NetworkStream.Read(bytes, 0, client.ReceiveBufferSize);
            String reply = Encoding.UTF8.GetString(bytes, 2, client.ReceiveBufferSize - 2);
            client.GetStream().Close();
            client.Close();
        }
    }
}
