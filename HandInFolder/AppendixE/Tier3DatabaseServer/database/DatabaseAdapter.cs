using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Design;
using Tier3ServerDatabase.common;
using EFCore.BulkExtensions;
namespace Tier3ServerDatabase.database {

    public class DatabaseAdapter : DbContext {

        public DatabaseAdapter (DbContextOptions<DatabaseAdapter> options) : base (options) { }
        public DbSet<Movie> movies { get; set; }
        public DbSet<Room> rooms { get; set; }
        public DbSet<ScheduledMovie> schedule {get; set;}

       public DbSet<Seat> seats {get; set;}
        
        //In order to update the database we need to have a factory for the dbcontext outside of the project
        public class ApplicationContextDbFactory : IDesignTimeDbContextFactory<DatabaseAdapter> {
            DatabaseAdapter IDesignTimeDbContextFactory<DatabaseAdapter>.CreateDbContext (string[] args) {
                var optionsBuilder = new DbContextOptionsBuilder<DatabaseAdapter> ();
                optionsBuilder.UseSqlServer ("Server=localhost\\SQLEXPRESS;Database=master;Trusted_Connection=True;MultipleActiveResultSets=true");
                return new DatabaseAdapter (optionsBuilder.Options);
            }
        }
    }
}