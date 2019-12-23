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
    public partial class login : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            string Username = "";
            string Password = "";

            try
            {
                Username = (Request.Form["Username"]);
                Password = (Request.Form["Password"]);
                if (Username.Trim() == "")
                {
                    Response.Write("0,'',0,Kullanici adi bos olamaz");
                }
                else if (Password.Trim() == "")
                {
                    Response.Write("0,'',0,Sifre bos olamaz");
                }
                else
                {
                    DbLogin(Username, Password);
                }
            }
            catch
            {
                Response.Write("0,Tum parametreleri gonderiniz!");
            }
        }
        private void DbLogin(string username, string password)
        {
            try
            {
                SqlConnection conn = new SqlConnection("Data Source=mssql07.turhost.com;" +
                        "Initial Catalog=orcunozdilDB;Persist Security Info=True;User ID=orcun;Password=Ozge140409");
                conn.Open();

                // SqlCommand command = new SqlCommand("SELECT * FROM BsUsersTable WHERE Username=@username AND Password=@password;", conn);
                SqlCommand command = new SqlCommand("Login", conn);
                command.CommandType = CommandType.StoredProcedure;
                command.Parameters.AddWithValue("@username", username);
                command.Parameters.AddWithValue("@password", password);


                using (SqlDataReader reader = command.ExecuteReader())
                {
                    if (reader.HasRows)
                    {
                        while (reader.Read())
                        {
                            Response.Write(reader["ID"].ToString() + "," + reader["Nickname"].ToString() + "," + reader["Point"].ToString() + ",Login basarili");
                        }
                    }
                    else
                    {
                        Response.Write("0,'',0,Kullanici adi veya sifre hatali");
                    }
                }
                conn.Close();


            }
            catch
            {
                Response.Write("0,'',0,Veritabani hatasi");
            }
        }
    }
}