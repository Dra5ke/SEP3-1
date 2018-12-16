using Microsoft.EntityFrameworkCore.Migrations;

namespace Tier3DatabaseServer.Migrations
{
    public partial class SettingUp3 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_seats_schedule_ScheduledMovieId",
                table: "seats");

            migrationBuilder.RenameColumn(
                name: "ScheduledMovieId",
                table: "seats",
                newName: "MovieId");

            migrationBuilder.RenameIndex(
                name: "IX_seats_ScheduledMovieId",
                table: "seats",
                newName: "IX_seats_MovieId");

            migrationBuilder.AddForeignKey(
                name: "FK_seats_schedule_MovieId",
                table: "seats",
                column: "MovieId",
                principalTable: "schedule",
                principalColumn: "Id",
                onDelete: ReferentialAction.Restrict);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_seats_schedule_MovieId",
                table: "seats");

            migrationBuilder.RenameColumn(
                name: "MovieId",
                table: "seats",
                newName: "ScheduledMovieId");

            migrationBuilder.RenameIndex(
                name: "IX_seats_MovieId",
                table: "seats",
                newName: "IX_seats_ScheduledMovieId");

            migrationBuilder.AddForeignKey(
                name: "FK_seats_schedule_ScheduledMovieId",
                table: "seats",
                column: "ScheduledMovieId",
                principalTable: "schedule",
                principalColumn: "Id",
                onDelete: ReferentialAction.Restrict);
        }
    }
}
