using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Routing;

namespace mvcASP
{
    public class RouteConfig
    {
        public static void RegisterRoutes(RouteCollection routes)
        {
            routes.IgnoreRoute("{resource}.axd/{*pathInfo}");

            routes.MapRoute(
                name: "main",
                url: "{controller}/{action}/{id}",
                defaults: new { controller = "Main", action = "Index", id = UrlParameter.Optional }
            );            
            routes.MapRoute(
                name: "filter",
                url: "{controller}/{action}",
                 new { controller = "Main", action = "FilterUserByRole" }
            );            
            routes.MapRoute(
                name: "update",
                url: "{controller}/{action}/{id}",
                 new { controller = "Main", action = "UpdateUser", id = UrlParameter.Optional }
            );  
            routes.MapRoute(
                name: "add",
                url: "{controller}/{action}",
                 new { controller = "Main", action = "AddUser" }
            );
        }
    }
}
