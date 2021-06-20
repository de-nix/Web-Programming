using System;
using System.Collections.Generic;
using System.Data;
using mvcASP.Models;
using System.Data.SqlClient;


namespace mvcASP.DataAbstractionLayer
{
    public class DAL
    {
        public List<User> GetUsersWithRole(string role) { 
            SqlConnection conn;
            string myConnectionString;
            myConnectionString = "Data Source=DESKTOP-SPCN0K8\\SQLEXPRESS;Initial Catalog=asp;Integrated Security=True";
            List<User> slist = new List<User>();
            conn = new SqlConnection(myConnectionString );
            SqlDataAdapter dataParent = new SqlDataAdapter("select * from users where role='"+role+"'", conn);
            DataSet dset = new DataSet();
            dataParent.Fill(dset, "users");
            foreach (DataRow row in dset.Tables["users"].Rows)
                {
                User user = new User{
                    Id = Int32.Parse(row["id"].ToString()),
                    Name = row["name"].ToString(),
                    Username = row["username"].ToString(),
                    Password = row["password"].ToString(),
                    Age = Int32.Parse(row["age"].ToString()),
                    Role = row["role"].ToString(),
                    Email = row["email"].ToString(),
                    Profile = row["profile"].ToString(),
                    Webpage = row["webpage"].ToString()
                };

                slist.Add(user);
                Console.Write(user);
                }
            return slist;
        }
        public List<User> GetUsers()
        {
            SqlConnection conn;
            string myConnectionString;
            myConnectionString = "Data Source=DESKTOP-SPCN0K8\\SQLEXPRESS;Initial Catalog=asp;Integrated Security=True";
            List<User> slist = new List<User>();
            conn = new SqlConnection(myConnectionString);
            SqlDataAdapter dataParent = new SqlDataAdapter("select * from users", conn);
            DataSet dset = new DataSet();
            dataParent.Fill(dset, "users");
            foreach (DataRow row in dset.Tables["users"].Rows)
            {
                User user = new User
                {
                    Id = Int32.Parse(row["id"].ToString()),
                    Name = row["name"].ToString(),
                    Username = row["username"].ToString(),
                    Password = row["password"].ToString(),
                    Age = Int32.Parse(row["age"].ToString()),
                    Role = row["role"].ToString(),
                    Email = row["email"].ToString(),
                    Profile = row["profile"].ToString(),
                    Webpage = row["webpage"].ToString()
                };

                slist.Add(user);
            }
            return slist;
        }

        public List<User> GetUser(string name) { 
            SqlConnection conn;
            string myConnectionString;
            myConnectionString = "Data Source=DESKTOP-SPCN0K8\\SQLEXPRESS;Initial Catalog=asp;Integrated Security=True";
            List<User> slist = new List<User>();
            conn = new SqlConnection(myConnectionString );
            SqlDataAdapter dataParent = new SqlDataAdapter("select * from users where name='"+name+"'", conn);
            DataSet dset = new DataSet();
            dataParent.Fill(dset, "users");
            foreach (DataRow row in dset.Tables["users"].Rows)
            {
                User user = new User
                {
                    Id = Int32.Parse(row["id"].ToString()),
                    Name = row["name"].ToString(),
                    Username = row["username"].ToString(),
                    Password = row["password"].ToString(),
                    Age = Int32.Parse(row["age"].ToString()),
                    Role = row["role"].ToString(),
                    Email = row["email"].ToString(),
                    Profile = row["profile"].ToString(),
                    Webpage = row["webpage"].ToString()
                };

                slist.Add(user);
                return slist;
            }
            return slist;
        } 
        
        public void DeleteUser(int id) { 
            SqlConnection conn;
            string myConnectionString;
            myConnectionString = "Data Source=DESKTOP-SPCN0K8\\SQLEXPRESS;Initial Catalog=asp;Integrated Security=True";
            List<User> slist = new List<User>();
            conn = new SqlConnection(myConnectionString );
            conn.Open();
            SqlCommand command= new SqlCommand("delete from users where id="+id, conn);
            command.ExecuteNonQuery();
            conn.Close();
        }        
        public void UpdateUser(int id, string name, string username, string password, int age, string email, string role, string profile, string webpage) { 
            SqlConnection conn;
            string myConnectionString;
            myConnectionString = "Data Source=DESKTOP-SPCN0K8\\SQLEXPRESS;Initial Catalog=asp;Integrated Security=True";
            List<User> slist = new List<User>();
            conn = new SqlConnection(myConnectionString );
            conn.Open();
            SqlCommand command= new SqlCommand("UPDATE users SET Name = '" + name + "', username = '"+ username + "', password = '" +password + "', age = " +age +
                ", role = '" +role+ "', profile = '" +profile + "', email = '" +email + "', webpage = '" +webpage + "' where id ="+id, conn);
            command.ExecuteNonQuery();
            conn.Close();
        }
        public void InsertUser(string name, string username, string password, int age, string email, string role, string profile, string webpage)
        {
            SqlConnection conn;
            string myConnectionString;
            myConnectionString = "Data Source=DESKTOP-SPCN0K8\\SQLEXPRESS;Initial Catalog=asp;Integrated Security=True";
            List<User> slist = new List<User>();
            conn = new SqlConnection(myConnectionString);
            conn.Open();
            SqlCommand command = new SqlCommand("insert into users( name, username, password, age, email, role, profile, webpage) values" +
                "('" + name + "', '" + username + "', '" + password + "'," + age +
                ", '" + email + "','" + role + "','" + profile + "','" + webpage + "')", conn);
            command.ExecuteNonQuery();
            conn.Close();
        }

    }
}