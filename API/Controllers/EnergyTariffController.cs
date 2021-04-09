using API.Controllers.Interfaces;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace API.Controllers
{
    public class EnergyTariffController : Controller
    {
        private ITariffService _tariffService;
        public EnergyTariffController(ITariffService tariffService)
        {
            _tariffService = tariffService;
        }

    }
}
