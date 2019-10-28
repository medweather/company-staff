package com.medweather.companystaff.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * API департамента с общей информацией
 */
public class DepartmentApi extends AbstractResponse {

    private int id;

    private String name;

    @JsonProperty(value = "from_date")
    private long fromDate;

    private int parent_id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getFromDate() {
        return fromDate;
    }

    public void setFromDate(long fromDate) {
        this.fromDate = fromDate;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }
}
