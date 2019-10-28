package com.medweather.companystaff.model.enumiration;

public enum Gender {

    MAN("мужской"),
    WOMAN("женский");

    private String description;

    Gender(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
