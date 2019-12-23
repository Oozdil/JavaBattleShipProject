using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace WebApplication5.Battleship
{
    public partial class gamerequest : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            string PlayerID = "";
           

            try
            {
                PlayerID = (Request.Form["PlayerID"]);
              
                if (PlayerID.Trim() == "")
                {
                    Response.Write("0,Oyuncu ID bos olamaz");
                }
              
                else
                {
                    DbGameRequest(PlayerID);
                }
            }
            catch
            {
                Response.Write("0,Tum parametreleri gonderiniz!");
            }
        }
        private void DbGameRequest(string PlayerID)
        {
            try
            {
                SqlConnection conn = new SqlConnection("Data Source=mssql07.turhost.com;" +
                        "Initial Catalog=orcunozdilDB;Persist Security Info=True;User ID=orcun;Password=Ozge140409");
                conn.Open();

               
                SqlCommand command = new SqlCommand("GameRequest", conn);
                command.CommandType = CommandType.StoredProcedure;
                command.Parameters.AddWithValue("@PlayerID", PlayerID);
              


                using (SqlDataReader reader = command.ExecuteReader())
                {
                    if (reader.HasRows)
                    {
                        while (reader.Read())
                        {
                            Response.Write(reader[0].ToString()+","+ reader[1].ToString());
                        }
                    }
                    else
                    {
                        Response.Write("0,Oyun istegi yapilamadi");
                    }
                }
                conn.Close();


            }
            catch
            {
                Response.Write("0,Veritabani hatasi");
            }
        }
    }
}