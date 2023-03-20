package pl.kurs.services;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class Shape {

    @JsonProperty("type")
    private String type;

    public abstract double calculatePerimeter();

    public abstract double calculateArea();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
