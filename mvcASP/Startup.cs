using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(mvcASP.Startup))]
namespace mvcASP
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
