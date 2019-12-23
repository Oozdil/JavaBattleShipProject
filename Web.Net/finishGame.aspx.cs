using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace WebApplication5.Battleship
{
    public partial class finishGame : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            string GameID = "";

            try
            {
                GameID = (Request.Form["GameID"]);


                if (GameID.Trim() == "")
                {
                    Response.Write("0,Alanlar bos olamaz");
                }

                else
                {
                    DbfinishRequest(GameID);
                    // Response.Write(ShipsArray+" "+GameID+" "+SenderType);
                }
            }
            catch
            {
                Response.Write("0,Tum parametreleri gonderiniz!");
            }
        }
        private void DbfinishRequest(string GameID)
        {
            try
            {
                SqlConnection conn = new SqlConnection("Data Source=mssql07.turhost.com;" +
                        "Initial Catalog=orcunozdilDB;Persist Security Info=True;User ID=orcun;Password=Ozge140409");
                conn.Open();


                SqlCommand command;
                command = new SqlCommand("UPDATE BsGameTable SET GameResult=1 WHERE ID=@GameID", conn);
                command.Parameters.AddWithValue("@GameID", GameID);


                command.ExecuteNonQuery();
                conn.Close();


            }
            catch (Exception ex)
            {
                Response.Write("0,Veritabani hatasi " + ex);
            }
        }
    }
}