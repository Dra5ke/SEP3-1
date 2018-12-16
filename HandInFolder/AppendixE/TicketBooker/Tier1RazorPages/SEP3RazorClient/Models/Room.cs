using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Newtonsoft.Json;
namespace SEP3RazorClient {
    public class Room{
        private int id;
        private int size;
        private string description;
        private ICollection<ScheduledMovie> scheduled;
        [Key]
        [JsonIgnore]
        [Required (ErrorMessage="Id {0} is required")]
        public int Id { get => id; set => id = value; }
        [Required (ErrorMessage="Size {0} is required")]
        public int Size { get => size; set => size = value; }
        [StringLength (40,MinimumLength=2,
        ErrorMessage="Description Should be minimum 2 characters and a maximum of 40 characters")]
        [DataType(DataType.Text)]
        public string Description { get => description; set => description = value; }
        [JsonIgnore]
        public ICollection<ScheduledMovie> Scheduled { get => scheduled; set => scheduled = value; }

        [JsonConstructor]
        public Room(int size, string description)
        {
            Size = size;
            Description = description;
        }

         public override String ToString() {
	    return "Room Id=" + Id  + ", Size=" + Size + ", Description=" + Description +  " \n ";
        }
    }
}