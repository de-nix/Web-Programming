using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using mvcASP.Models;
using mvcASP.DataAbstractionLayer;

namespace mvcASP.Controllers
{
    [Authorize]
    public class MainController : Controller
    {
        // GET: Main
        public ActionResult FilterUsersByRole ()
        {
            

            return View("FilterUsersByRole");
        }
        
        public ActionResult Index ()
        {

            return View("FilterUsersByRole");
        }

        public ActionResult UpdateView(int id)
        {
            
            //int id = Int32.Parse(Request.Params["id"]);
            ViewBag.message = id;
            return View("UpdateUser");
        }
        
        public ActionResult AddUser()
        {
            
            return View("AddUser");
        }



        public string GetUsersWithRole()
        {
            String role = Request.Params["role"];
            DAL dal = new DAL();
            List<User> ulist = dal.GetUsersWithRole(role);
            ViewData["userList"] = ulist;

            string result = "<table><thead><tr><td>ID</td><td>Name</td><td>Username</td><td>Password</td><td>Age</td><td>Role</td><td>Profile</td><td>Email</td><td>Webpage</td><td>Delete</td><td>Update</td></tr></thead>";


            foreach (User user in ulist)
            {
                result += "<tr><td>" + user.Id + "</td><td>" + user.Name + "</td><td>" + user.Username + "</td><td>" + user.Password + "</td><td>" + user.Age + "</td><td>" +
                          user.Role + "</td><td>" + user.Profile + "</td><td>" + user.Email + "</td><td>" + user.Webpage + "</td>" +
                            "<td><button onclick=\"deleteFunc(" + user.Id + ")\" > Delete</button></td>" +
                            "<td><button onclick=\"updateFunc(" + user.Id + ")\"> Update</button></td></tr>";
            }
            result += "</table>";
            return result;
        }  
        
        public string GetUserByName()
        {
            String name = Request.Params["name"];
            DAL dal = new DAL();
            List<User> ulist = dal.GetUser(name);
            ViewData["userList"] = ulist;

            string result = "<table><thead><tr><td>ID</td><td>Name</td><td>Username</td><td>Password</td><td>Age</td><td>Role</td><td>Profile</td><td>Email</td><td>Webpage</td><td>Delete</td><td>Update</td></tr></thead>";


            foreach (User user in ulist)
            {
                result += "<tr><td>" + user.Id + "</td><td>" + user.Name + "</td><td>" + user.Username + "</td><td>" + user.Password + "</td><td>" + user.Age + "</td><td>" +
                          user.Role + "</td><td>" + user.Profile + "</td><td>" + user.Email + "</td><td>" + user.Webpage + "</td>" +

                            "<td><button onclick=\"deleteFunc(" + user.Id + ")\" > Delete</button></td>" +
                            "<td><button onclick=\"updateFunc(" + user.Id + ")\"> Update</button></td></tr>";
            }
            result += "</table>";
            return result;
        }public string GetUsers()
        {
            DAL dal = new DAL();
            List<User> ulist = dal.GetUsers();
            ViewData["userList"] = ulist;

            string result = "<table><thead><tr><td>ID</td><td>Name</td><td>Username</td><td>Password</td><td>Age</td><td>Role</td><td>Profile</td><td>Email</td><td>Webpage</td><td>Delete</td><td>Update</td></tr></thead>";


            foreach (User user in ulist)
            {
                result += "<tr><td>" + user.Id + "</td><td>" + user.Name + "</td><td>" + user.Username + "</td><td>" + user.Password + "</td><td>" + user.Age + "</td><td>" +
                          user.Role + "</td><td>" + user.Profile + "</td><td>" + user.Email + "</td><td>" + user.Webpage + "</td>" +
                            "<td><button onclick=\"deleteFunc(" + user.Id + ")\"> Delete</button></td>" +
                            "<td><button onclick=\"updateFunc(" + user.Id + ")\"> Update</button></td></tr>";
            }
            result += "</table>";
            return result;
        }
        
        public ActionResult InsertUser()
        {
            String name = Request.Params["name"];
            String username = Request.Params["username"];
            String password = Request.Params["password"];
            int age = Int32.Parse(Request.Params["age"]);
            String email = Request.Params["email"];
            String role = Request.Params["role"];
            String profile = Request.Params["profile"];
            String webpage = Request.Params["webpage"];
            DAL dal = new DAL();
            dal.InsertUser(name,username,password,age,email,role,profile,webpage);

            return this.Index();

        }
        public ActionResult UpdateUser()
        {
            int id = Int32.Parse( Request.Params["id"]);
            String name = Request.Params["name"];
            String username = Request.Params["username"];
            String password = Request.Params["password"];
            int age = Int32.Parse(Request.Params["age"]);
            String email = Request.Params["email"];
            String role = Request.Params["role"];
            String profile = Request.Params["profile"];
            String webpage = Request.Params["webpage"];
            DAL dal = new DAL();
            dal.UpdateUser(id,name,username,password,age,email,role,profile,webpage);


            return this.Index();

        }   
        
        public string deleteUser()
        {
            int id = Int32.Parse( Request.Params["id"]);
            
            DAL dal = new DAL();
            dal.DeleteUser(id);

            return this.GetUsers();
        }

    }
}