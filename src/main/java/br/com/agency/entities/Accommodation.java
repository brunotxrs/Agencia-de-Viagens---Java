package br.com.agency.entities;

import java.math.BigDecimal;

public class Accommodation {
    private String description;
    private BigDecimal dailyRate;

    public Accommodation(String description, BigDecimal dailyRate) {
        this.description = description;
        this.dailyRate = dailyRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(BigDecimal dailyRate) {
        this.dailyRate = dailyRate;
    }
}
