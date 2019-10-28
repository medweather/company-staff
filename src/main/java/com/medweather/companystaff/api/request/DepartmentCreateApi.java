package com.medweather.companystaff.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Тело запроса при создании департамента
 */
public class DepartmentCreateApi {

    private String name;

    @JsonProperty(value = "name_of_parent")
    private String nameOfParent;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameOfParent() {
        return nameOfParent;
    }

    public void setNameOfParent(String nameOfParent) {
        this.nameOfParent = nameOfParent;
    }
}
