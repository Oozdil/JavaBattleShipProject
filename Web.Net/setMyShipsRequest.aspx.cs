using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace WebApplication5.Battleship
{
    public partial class setMyShipsRequest : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            string ShipsArray = "";
            string GameID = "";
            string SenderType = "";

            try
            {
                ShipsArray = (Request.Form["ShipsArray"]);
                GameID = (Request.Form["GameID"]);
                SenderType = (Request.Form["SenderType"]);

                if (ShipsArray.Trim() == "")
                {
                    Response.Write("0,Alanlar bos olamaz");
                }

                else
                {
                      DbSetShipRequest(ShipsArray, GameID, SenderType);
                   // Response.Write(ShipsArray+" "+GameID+" "+SenderType);
                }
            }
            catch
            {
                Response.Write("0,Tum parametreleri gonderiniz!");
            }
        }
        private void DbSetShipRequest(string ShipsArray,string GameID,string SenderType)
        {
            try
            {
                SqlConnection conn = new SqlConnection("Data Source=mssql07.turhost.com;" +
                        "Initial Catalog=orcunozdilDB;Persist Security Info=True;User ID=orcun;Password=Ozge140409");
                conn.Open();


                SqlCommand command;

                if (SenderType.Trim()=="true")
                {
                    command = new SqlCommand("UPDATE BsGameTable SET HomeShips=@ShipsArray WHERE ID=@GameID", conn);
                }
                else
                {
                    command = new SqlCommand("UPDATE BsGameTable SET VisitorShips=@ShipsArray WHERE ID=@GameID", conn);
                }
                command.Parameters.AddWithValue("@ShipsArray", ShipsArray);
                command.Parameters.AddWithValue("@GameID", GameID);


                command.ExecuteNonQuery();
                conn.Close();


            }
            catch
            {
                Response.Write("0,Veritabani hatasi");
            }
        }
    }

}