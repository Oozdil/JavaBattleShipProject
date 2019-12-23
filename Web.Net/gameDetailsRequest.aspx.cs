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
    public partial class gameDetailsRequest : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            string GameID = "";


            try
            {
                GameID = (Request.Form["GameID"]);

                if (GameID.Trim() == "")
                {
                    Response.Write("0,Oyuncu ID bos olamaz");
                }

                else
                {
                    DbGameDetailRequest(GameID);
                }
            }
            catch
            {
                Response.Write("0,Tum parametreleri gonderiniz!");
            }
        }

        private void DbGameDetailRequest(string GameID)
        {
            try
            {
                SqlConnection conn = new SqlConnection("Data Source=mssql07.turhost.com;" +
                        "Initial Catalog=orcunozdilDB;Persist Security Info=True;User ID=orcun;Password=Ozge140409");
                conn.Open();


                SqlCommand command = new SqlCommand("SELECT * FROM GameDetailsView WHERE ID=@GameID", conn);              
                command.Parameters.AddWithValue("@GameID", GameID);



                using (SqlDataReader reader = command.ExecuteReader())
                {
                    if (reader.HasRows)
                    {
                        while (reader.Read())
                        {
                            Response.Write(reader[0].ToString() + "," + reader[1].ToString()+","+
                                reader[2].ToString() + "," + reader[3].ToString()+","+
                                reader[4].ToString() + "," + reader[5].ToString()+","+
                                reader[6].ToString() + "," + reader[7].ToString()+","+
                                reader[8].ToString() + "," + reader[9].ToString());
                        }
                    }
                    else
                    {
                        Response.Write("0,Oyun istegi bulunamadı");
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