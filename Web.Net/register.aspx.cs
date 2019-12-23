using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace WebApplication5.Battleship
{
    public partial class register : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            string Username = "";
            string Password = "";
            string PasswordRepeat = "";
            string Nickname = "";
            try
            {
                Username = (Request.Form["Username"]);
                Password = (Request.Form["Password"]);
                PasswordRepeat = (Request.Form["PasswordRepeat"]);
                Nickname = (Request.Form["Nickname"]);


                if (Username.Trim() == "")
                {
                    Response.Write("0,Kullanici adi bos olamaz");
                }
                else if (Password.Trim() == "")
                {
                    Response.Write("0,Sifre bos olamaz");
                }
                else if (PasswordRepeat.Trim() == "")
                {
                    Response.Write("0,Tekrar Sifresi bos olamaz");
                }
                else if (Nickname.Trim() == "")
                {
                    Response.Write("0,Nickname bos olamaz");
                }
                else
                {
                    DbRegister(Username, Password, Nickname);
                }
            }
            catch
            {
                Response.Write("0,Tüm parametreleri gonderiniz!");
            }
        }

        private void DbRegister(string username, string password, string nickname)
        {
            try
            {
                SqlConnection conn = new SqlConnection("Data Source=mssql07.turhost.com;" +
                        "Initial Catalog=orcunozdilDB;Persist Security Info=True;User ID=orcun;Password=Ozge140409");
                conn.Open();

                SqlCommand command = new SqlCommand("INSERT INTO BsUsersTable (Username,Password,Nickname) " +
                    "VALUES (@username, @password, @nickname) ", conn);
                command.Parameters.AddWithValue("@username", username);
                command.Parameters.AddWithValue("@password", password);
                command.Parameters.AddWithValue("@nickname", nickname);


                int resultt = command.ExecuteNonQuery();
                conn.Close();
                Response.Write("1,Kullanici kaydi basarili");

            }
            catch (Exception ex)
            {
                if (ex.ToString().Contains("PK_BsUsersTable"))
                {
                    Response.Write("0,Bu kullanci adi onceden kayitli");
                }
                if (ex.ToString().Contains("IX_BsUsersTable"))
                {
                    Response.Write("0,Bu Nickname onceden kayitli");
                }


            }
        }
    }
}