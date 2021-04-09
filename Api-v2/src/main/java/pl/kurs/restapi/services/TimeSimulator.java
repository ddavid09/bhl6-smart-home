package pl.kurs.restapi.services;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TimeSimulator {
    private LocalDateTime localDateTime = LocalDateTime.now();
    private boolean isWorkingDay = false;
    private boolean isVacations = false;

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void set(LocalDateTime localDateTime, boolean isWorkingDay, boolean isVacations) {
        this.localDateTime = localDateTime;
        this.isWorkingDay = isWorkingDay;
        this.isVacations = isVacations;
    }

    public boolean isWorkingDay() {
        return isWorkingDay;
    }

    public boolean isVacations() {
        return isVacations;
    }
}
