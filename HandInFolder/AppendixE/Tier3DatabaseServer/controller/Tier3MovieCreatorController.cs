using System;
using Tier3ServerDatabase.view;
using Tier3ServerDatabase.database;
using System.Threading;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Options;
using Microsoft.EntityFrameworkCore;

namespace Tier3ServerDatabase.controller {

    public class Tier3MovieCreatorController {
        private Tier3MovieCreatorView view;
        private Tier3MovieCreatorServer server;
        private RepositoryDatabaseAdapter database;

        public Tier3MovieCreatorView View { get => view; set => view = value; }
        public RepositoryDatabaseAdapter Database { get => database; set => database = value; }
        public Tier3MovieCreatorController(Tier3MovieCreatorView view, string adress, int port)
        {
            this.View = view;

            try{
                this.server = new Tier3MovieCreatorServer(this);
            } catch(Exception e)
            {
                //Replace with e.Message when development is done
                view.Show(e.StackTrace);
            }

            Thread t = new Thread(() => this.server.StartListening(adress, port));
            //Creating the database
            //Todo: to remove the hardcoded values
            var optionsBuilder = new DbContextOptionsBuilder<DatabaseAdapter>();
            optionsBuilder.UseSqlServer("Server=localhost\\SQLEXPRESS;Database=master;Trusted_Connection=True;MultipleActiveResultSets=true");
            DatabaseAdapter databaseAdapter = new DatabaseAdapter(optionsBuilder.Options);
            //Creating a new repository database
            database = new RepositoryDatabaseAdapter(databaseAdapter);
            t.Start();
        }
    }
}