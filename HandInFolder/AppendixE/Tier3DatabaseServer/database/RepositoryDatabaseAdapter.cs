using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.EntityFrameworkCore;
using Tier3ServerDatabase.common;

namespace Tier3ServerDatabase.database
{
    public class RepositoryDatabaseAdapter
    {
        private readonly DatabaseAdapter _context;

        //Initializing the Repository with the proper dbcontext
        public RepositoryDatabaseAdapter(DatabaseAdapter context)
        {
            _context = context;
        }

        // Method for getting a list of all the movies
        public List<Movie> GetAllMovies()
        {
            return _context.movies.ToList();
        }

        // Getting a string of all the movies
        public String GetStringMovies()
        {
            String result = "";
            foreach (Movie m in GetAllMovies())
            {
                result = result + m.ToString();
            }
            return result;
        }
        //Get a List of movies with rented field set to true
        public List<Movie> GetRentedMovies()
        {
            var rentedMovies = (from m in _context.movies where m.Rented == true orderby m.Id select m).ToList();
            return rentedMovies;
        }
        //Get a string with movis with the rented field set to true
        public String GetRentedStringMovies()
        {
            String result = "";
            foreach (Movie m in GetRentedMovies())
            {
                result = result + m.ToString();
            }
            return result;
        }

        //Method to get a Movie by ID
        public Movie GetMovie(string id)
        {
            return _context.movies.First(m => m.Id == Int32.Parse(id));
        }

        //Method for adding a Movie to the database
        public bool AddMovie(Movie movie)
        {
            _context.Add<Movie>(movie);
            return SaveAll();
        }

        //Method to changed the status of rented in a movie to true
        public bool RentMovie(int id)
        {
            Movie movie = _context.movies.First(m => m.Id.Equals(id));
            movie.Rented = true;
            return SaveAll();
        }

        //Method to add a room
        public bool AddRoom(Room room)
        {
            _context.Add<Room>(room);
            return SaveAll();
        }

        //Method to get a list of rooms
        public List<Room> GetRooms()
        {
            return _context.rooms.ToList();
        }

        //Method to get a string with the details of each room
        public String GetStringRooms()
        {
            String result = "";
            foreach (Room r in GetRooms())
            {
                result = result + r.ToString();
            }
            return result;
        }

        //Method to get a room by the ID
        public Room GetRoom(string id)
        {
            return _context.rooms.First(r => r.Id == Int32.Parse(id));
        }

        //Method to get a string of rented Movies
        public string GetStringRentedMovies()
        {
            String result = "";
            foreach (Movie m in _context.movies.Where(m => m.Rented.Equals(true)).ToList())
            {
                result = result + m.ToString();
            }
            return result;
        }

        //Method to get a string of movies that are not rented
        public string GetStringAvailableMovies()
        {
             String result = "";
            foreach (Movie m in _context.movies.Where(m => m.Rented.Equals(false)).ToList())
            {
                result = result + m.ToString();
            }
            return result;
        }

        //Method to remove a specific room by id
        public void RemoveRoom(string id)
        {
            _context.rooms.Remove(_context.rooms.First(r => r.Id == Int32.Parse(id)));
            SaveAll();
        }

        //Method to return the schedule
        public List<ScheduledMovie> GetSchedule()
        {   //We use eager loading to make sure we have everything we need as we query the schedule
            //https://docs.microsoft.com/en-us/ef/core/querying/related-data#eager-loading
            //We get an ordered list by time
            List<ScheduledMovie> movies = _context.schedule.Include(schedule => schedule.Movie).Include(Scheduled => Scheduled.Seats).Include(Scheduled => Scheduled.Room).OrderBy(s => s.Time).ToList();
            return movies;
        }
        //Get The Schedule in string
        public string GetStringSchedule()
        {
            String result = "";
            foreach (ScheduledMovie m in GetSchedule())
            {
                result = result + m.Show();
            }
            return result;
        }
        //Method to add the Schedule to the database
        public bool addSchedule(List<ScheduledMovie> scheduledMovies)
        {   _context.seats.RemoveRange(_context.seats.ToList());
            _context.schedule.RemoveRange(_context.schedule.ToList());
            foreach (ScheduledMovie m in scheduledMovies)
            {
                m.Movie = _context.movies.First(c => c.Title.Equals(m.Movie.Title));
                m.Room = _context.rooms.First(c => c.Description.Equals(m.Room.Description));
                _context.schedule.Add(m);
            }
            return SaveAll();
        }

        //Method to save all the data inputted
        public bool SaveAll()
        {
            return _context.SaveChanges() > 0;
        }
    }
}