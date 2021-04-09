using System;

namespace API.Controllers.Interfaces
{
    public interface ITariffService
    {
        decimal Cost(DateTime dateTime);

        decimal Income(DateTime dateTime);
    }
}