package pl.kurs.restapi.services;

import org.springframework.stereotype.Service;

@Service
public class CloudSimulator {
    double percentageOfCloud = 0.5;

    public double getPercentageOfCloud() {
        return percentageOfCloud;
    }

    public void setPercentageOfCloud(double percentageOfCloud) throws Exception {
        if(percentageOfCloud>1.0 || percentageOfCloud<0.0){
            throw new Exception("Bad Value of Cloud");
        }
        this.percentageOfCloud = percentageOfCloud;
    }
}
