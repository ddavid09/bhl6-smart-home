package pl.kurs.restapi.services;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class TimeSimulator {
    private LocalDateTime prevLocalDateTime = LocalDateTime.now();
    private LocalDateTime localDateTime = LocalDateTime.now().plusHours(1);
    private boolean isVacations = false;

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void set(LocalDateTime localDateTime, boolean isVacations) {
        this.localDateTime = localDateTime;
        this.isVacations = isVacations;
    }

    public boolean isWorkingDay() {
        if(localDateTime.toLocalDate().getDayOfWeek().equals(DayOfWeek.SUNDAY) || localDateTime.toLocalDate().getDayOfWeek().equals(DayOfWeek.SATURDAY))
            return false;
        else {
            return true;
        }
    }

    public boolean isVacations() {
        return isVacations;
    }

    public void moveTimeForwardByMinutes(long minutes){
        prevLocalDateTime = localDateTime;
        localDateTime = localDateTime.plusMinutes(minutes);
    }

    public long getTimeDiffInMinutes(){
        return Duration.between(prevLocalDateTime, localDateTime).toMinutes();
    }
}
