package br.com.agency.entities;

import java.math.BigDecimal;

public class Transport {
    private String type;
    private BigDecimal value;

    public Transport(String type, BigDecimal value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
