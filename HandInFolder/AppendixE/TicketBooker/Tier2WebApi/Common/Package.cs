using System;
using System.Collections.Generic;
using Newtonsoft.Json;

namespace Tier2WebApi.Common {
    public class Package {

        private string header;
        private string body;
        private Movie movie;
        private int id;

        private List<ScheduledMovie> scheduleList;
        private Room room;

        public string Header { get => header; set => header = value; }
        public string Body { get => body; set => body = value; }
        public Movie Movie { get => movie; set => movie = value; }
        public int Id { get => id; set => id = value; }
        public List<ScheduledMovie> ScheduleList{ get => scheduleList; set => scheduleList = value; }
        public Room Room { get => room; set => room = value; }

        public Package (string header) {
            Header = header;
        }

        public Package (string header, string body) {
            Header = header;
            Body = body;
        }

        public Package (string header, Movie movie) {
            Header = header;
            Movie = movie;
        }

        public Package (string header, Room room) {
            Header = header;
            Room = room;
        }
        //The constructor the json needs to default to
        //Doesn't deserialize without this
        [JsonConstructor]
        public Package (string header, string body, Movie movie, Room room, List<ScheduledMovie> schedule) {
            Header = header;
            Body = body;
            Movie = movie;
            ScheduleList = schedule;
            Room = room;
        }
    }
}