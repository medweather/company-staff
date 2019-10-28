package com.medweather.companystaff.api.response;

/**
 * API удаление департамента
 */
public class DepartmentDeleteApi extends AbstractResponse {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
