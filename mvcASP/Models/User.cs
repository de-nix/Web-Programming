﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace mvcASP.Models
{
    public class User
    {

        public int Id { get; set; }
        public string Name { get; set; }
        public string Username { get; set; }
        public string Password { get; set; }
        public string Email { get; set; }
        public string Role { get; set; }
        public int Age { get; set; }
        public string Webpage { get; set; }
        public string Profile { get; set; }
    }
}