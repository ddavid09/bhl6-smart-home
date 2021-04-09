package pl.kurs.restapi.services;

public interface HomeHeaterService {

    double getCost();

    void setHeater(HomeHeaterServiceImpl.State state);

    void heatOrNot();

    void weathering(double percentageOfHour) throws Exception;
}