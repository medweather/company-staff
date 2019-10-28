package com.medweather.companystaff.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * API департамента с определенной информацией
 */
public class DepartmentInfoApi extends AbstractResponse {

    private int id;

    private String name;

    @JsonProperty(value = "from_date")
    private long fromDate;

    private EmployeeApi manager;

    @JsonProperty(value = "count_of_employees")
    private long countOfEmployees;


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

    public EmployeeApi getManager() {
        return manager;
    }

    public void setManager(EmployeeApi manager) {
        this.manager = manager;
    }

    public long getCountOfEmployees() {
        return countOfEmployees;
    }

    public void setCountOfEmployees(long countOfEmployees) {
        this.countOfEmployees = countOfEmployees;
    }
}
