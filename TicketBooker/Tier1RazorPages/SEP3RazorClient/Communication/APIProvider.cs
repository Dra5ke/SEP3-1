using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;

namespace SEP3RazorClient.Communication
{
    public class APIProvider
    {
        public Boolean DisapprovedBooking = false;
        public async Task<List<ScheduledMovie>> GetScheduleAsync(string path)
        {
            using (var client = new HttpClient())
            {
                // Update port # in the following line.
                client.BaseAddress = new Uri("https://localhost:5003/api/");
                client.DefaultRequestHeaders.Accept.Clear();
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                List<ScheduledMovie> list = null;
                HttpResponseMessage response = await client.GetAsync(path);
                if (response.IsSuccessStatusCode)
                {
                    list = await response.Content.ReadAsAsync<List<ScheduledMovie>>();
                }
                return list;
            }

        }

        public async Task<Boolean> UpdateProductAsync(int id, int id2)
        {
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri("https://localhost:5003/api/");
                client.DefaultRequestHeaders.Accept.Clear();
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                HttpResponseMessage response = await client.PutAsJsonAsync("https://localhost:5003/api/schedule/" + id, id2);
               try{response.EnsureSuccessStatusCode();
               }
               catch(HttpRequestException){
                   return false;
               }
                return true;
        }
        }


    }
    
}