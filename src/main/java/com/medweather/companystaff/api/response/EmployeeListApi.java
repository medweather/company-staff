package com.medweather.companystaff.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * API список сотрудников
 */
public class EmployeeListApi extends ResponseApi {

    private List<EmployeeApi> employeeApiList = new ArrayList<>();

    @JsonProperty("data")
    public List<EmployeeApi> getData() {
        return employeeApiList;
    }

    public void setData(List<EmployeeApi> employeeApiList) {
        this.employeeApiList = employeeApiList;
    }
}
