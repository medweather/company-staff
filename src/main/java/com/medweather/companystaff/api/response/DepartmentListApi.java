package com.medweather.companystaff.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * API список департаментов
 */
public class DepartmentListApi extends ResponseApi {

    private List<DepartmentApi> data = new ArrayList<>();

    @JsonProperty(value = "data")
    public List<DepartmentApi> getData() {
        return data;
    }

    public void setData(List<DepartmentApi> data) {
        this.data = data;
    }
}
