using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Newtonsoft.Json;
using SEP3RazorClient.Communication;
namespace SEP3RazorClient.Pages
{
    public class SeatsModel : PageModel
    {
        public Boolean disapprovedBooking = false;
        private List<ScheduledMovie> schedule;

        [BindProperty(SupportsGet = true)]
        public int Choice { get; set; }
        public Boolean DisapprovedBooking { get; set; }
        public List<ScheduledMovie> Schedule { get => schedule; set => schedule = value; }
        public APIProvider Provider { get => provider; set => provider = value; }

        APIProvider provider;

        public SeatsModel(APIProvider provider)
        {
            Provider = provider;
        }

        // get the schedule from the api using the APIProvider
        [HttpGet]
        public void OnGet()
        {
            Schedule = Provider.GetScheduleAsync("https://localhost:5003/api/schedule").Result; 
            DisapprovedBooking = Provider.DisapprovedBooking;
        }
        // book a seat inside the movie
        public ActionResult OnPost()
        {   Schedule = Provider.GetScheduleAsync("https://localhost:5003/api/schedule").Result;
            int chosenSeat = Int32.Parse(Request.Form["SeatChoice"]) -1;
             if (Provider.UpdateProductAsync(Choice, (chosenSeat)).Result)
             {  
                if(!Schedule.ElementAt(Choice).Seats.ElementAt(chosenSeat).Booked)
                {
                Provider.DisapprovedBooking = false;
                return Redirect("/TicketBooked/");
                }
                else
                {
                Provider.DisapprovedBooking = true;
                return Redirect("/BookSeats/" + Choice);
                }
             }
            else
                return Redirect("/Error");
               
        }
    }
}