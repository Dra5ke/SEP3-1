using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using SEP3RazorClient.Communication;
namespace SEP3RazorClient.Pages
{
    public class BookTicketModel : PageModel
    {
        public string Message { get; set; }
        List<ScheduledMovie> schedule;

        private APIProvider provider;
        public List<ScheduledMovie> Schedule { get => schedule; set => schedule = value; }
        public APIProvider Provider { get => provider; set => provider = value; }

        public BookTicketModel(APIProvider provider)
        {
            //This assures it doesn't need to ask again for information each time a method is called
            Provider = provider;
            Provider.DisapprovedBooking = false;
        }
        // get the schedule from the api using the APIProvider
        public void OnGet()
        {
            Schedule = Provider.GetScheduleAsync("https://localhost:5003/api/schedule").Result;
        }
        // Request to redirect to the page of the chosen movie
        public ActionResult OnPost()
        {
            var choice = Request.Form["Movie"];
            return Redirect("/BookSeats/" + choice);
        }
    }
}
