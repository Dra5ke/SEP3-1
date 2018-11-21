using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using Newtonsoft.Json;
namespace Tier3ServerDatabase.common {

    public class ScheduledMovie {
        private int id;
        private string time;
        private string day;
        private Movie movie;
        private Room room;
        private ICollection<Seat> seats;

        [Key]
        [JsonIgnore]
        [Required (ErrorMessage = "Id {0} is required")]
        public int Id { get => id; set => id = value; }

        [Required (ErrorMessage = "Time {0} is required")]
        [StringLength (10, MinimumLength = 2,
            ErrorMessage = "Time Should be minimum 2 characters and a maximum of 40 characters")]
        [DataType (DataType.Text)]
        public string Time { get => time; set => time = value; }

        [Required (ErrorMessage = "Day {0} is required")]
        [StringLength (10, MinimumLength = 2,
            ErrorMessage = "Time Should be minimum 2 characters and a maximum of 40 characters")]
        [DataType (DataType.Text)]
        public string Day { get => day; set => day = value; }
        public ICollection<Seat> Seats { get => seats; set => seats = value; }
        public Room Room { get => room; set => room = value; }
        public Movie Movie { get => movie; set => movie = value; }

        [JsonConstructor]
        public ScheduledMovie (string day, string time, Movie movie, Room room, ICollection<Seat> Seats) {
            Time = time;
            Seats = seats;
            Movie = movie;
            Room = room;
        }
        //Default working constructor. Issues with the List
        public ScheduledMovie () {
            
        }
        public override String ToString () {
            return "Movie Id=" + Id + ", Day=" + Day + ", Time=" + Time + ", MovieId=" + Movie.Id + ", RoomId=" +
                Room.Id + NrOfBookedSeats () + " \n ";
        }
        //Method to check how many seats are booked
        public String NrOfBookedSeats () {
            return " Number of Booked Seats=" + Seats.Count (b => b.Booked);
        }

    }
    //We keep the Seat class here because it's closely tied to the Scheduled Movie
    public class Seat {
        private int id;
        private bool booked;
        [Key]
        [JsonIgnore]
        [Required (ErrorMessage = "Id {0} is required")]

        public int Id { get => id; set => id = value; }

        [Required (ErrorMessage = "Booked {0} is required")]
        public bool Booked { get => booked; set => booked = value; }
        [JsonConstructor]
        public Seat (bool booked) {
            Booked = booked;
        }
        public Seat () {
        }
    }
}