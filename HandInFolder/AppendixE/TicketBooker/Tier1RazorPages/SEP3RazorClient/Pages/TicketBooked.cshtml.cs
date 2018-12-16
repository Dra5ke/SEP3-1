using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
namespace SEP3RazorClient.Pages
{
    public class TicketBookedModel : PageModel
    {
        [BindProperty]
        public string EmailAddress { get; set; }
        public string Telephone { get; set; }
        public void OnPost()
        {
            EmailAddress = Request.Form["Emailaddress"];
            Telephone = Request.Form["Telephone"];
        }
    }
}