using System;
using System.Net.Sockets;
using System.Text;
using Newtonsoft.Json;
using Tier3ServerDatabase.common;
using Tier3ServerDatabase.database;
using Tier3ServerDatabase.view;
namespace Tier3ServerDatabase.controller
{

    public class Tier3MovieCreatorThreadHandler
    {
        private Socket handler;
        private RepositoryDatabaseAdapter database;
        private Tier3MovieCreatorView view;
        private string data;
        //Receive all the data needed
        public Tier3MovieCreatorThreadHandler(Socket handler, Tier3MovieCreatorView view, RepositoryDatabaseAdapter database)
        {
            this.database = database;
            this.view = view;
            this.handler = handler;
            view.Show("Client connected ");
        }

        public void HandleCommunication()
        {
            try
            {
                //The size of the buffer to read from the Client
                byte[] bytes = new Byte[8192];
                int bytesRec = handler.Receive(bytes);
                //Receive the Message from the Client
                //The first 2 bytes are skipped because Java uses a special format of UTF 8
                // Where is sends the length first
                data += Encoding.UTF8.GetString(bytes, 2, bytesRec);
                //Covert the Request to Package
                Package request = JsonConvert.DeserializeObject<Package>(data);
                // Print out the received message to the console.
                view.Show("Package: " + request.Header);
                //Get the proper reply
                Package reply = Operation(request);

                view.Show("Reply: " + reply.Body);
                //Convert the package to json
                string json = JsonConvert.SerializeObject(reply,
                            new JsonSerializerSettings
                            {
                                ReferenceLoopHandling = ReferenceLoopHandling.Ignore
                            });
                view.Show(json);
                // Convert it to bytes
                byte[] msg = Encoding.UTF8.GetBytes(json, 0, json.Length);

                //Sending the data
                handler.Send(msg);
                //Shutting down the communication so it knows the sending is done
                handler.Shutdown(SocketShutdown.Both);
                handler.Close();

            }
            catch (Exception e)
            {
                //Change with e.Message
                string message = e.StackTrace;
                view.Show("Error" + " - Message: " + message);
            }
        }

        public Package Operation(Package request)
        {
            Package wrong = new Package("WRONG FORMAT");
            try
            {
                switch (request.Header)
                {
                    // get a list of current movies
                    case "GETMOVIES":
                        return new Package("GETMOVIES", database.GetStringMovies());
                    // get a list of rented movies
                    case "GETRENTEDMOVIES":
                        return new Package("GETRENTEDMOVIES", database.GetStringRentedMovies());
                    // get a list of available movies
                    case "GETAVAILABLEMOVIES":
                        return new Package("GETAVAILABLEMOVIES", database.GetStringAvailableMovies());
                    //get a certain Movie
                    case "GETMOVIE":
                        return new Package("GETMOVIE", database.GetMovie(request.Body));
                    //First we add the movie then we get a list of the current movies back
                    case "ADD":
                        if (database.AddMovie(request.Movie))
                        {
                            return new Package("ADD", database.GetStringMovies());
                        }
                        break;
                    //Set the rented status to true
                    case "RENT":
                        if (database.RentMovie(request.Id))
                        {
                            return new Package("RENT", database.GetRentedStringMovies());
                        }
                        break;
                    //Add a room to the database
                    case "ADDROOM":
                        if (database.AddRoom(request.Room))
                        {
                            return new Package("ADDROOM", database.GetStringRooms());
                        }
                        break;
                    //Get rooms list
                    case "GETROOMS":
                        return new Package("GETROOMS", database.GetStringRooms());
                    //Get a a room by ID
                    case "GETROOM":
                        return new Package("GETROOM", database.GetRoom(request.Body));
                    //Remove a Room by id
                    case "REMOVEROOM":
                        database.RemoveRoom(request.Body);
                        return new Package("REMOVEROOM", database.GetStringRooms());
                    //Receives the Schedule and saves it to the database
                    case "SENDSCHEDULE":
                        if (database.addSchedule(request.ScheduleList))
                            return new Package("SENDSCHEDULE", database.GetStringSchedule());
                        break;
                    //Send the schedule to tier 2
                    case "GETSCHEDULE":
                        return new Package("GETSCHEDULE", database.GetStringSchedule());
                    case "GETALLSCHEDULE":
                        return new Package("GETALLSCHEDULE", null, null, null, database.GetSchedule());
                    default:
                        return wrong;
                }
            }
            catch (Exception e)
            {
                //Change with message
                view.Show(e.StackTrace);
            }
            return wrong;
        }
    }
}