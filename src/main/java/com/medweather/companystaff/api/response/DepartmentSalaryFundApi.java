package com.medweather.companystaff.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * API фонд заработной платы департамента
 */
public class DepartmentSalaryFundApi extends AbstractResponse {

    @JsonProperty(value = "salary_fund")
    private BigDecimal salaryFund;

    public BigDecimal getSalaryFund() {
        return salaryFund;
    }

    public void setSalaryFund(BigDecimal salaryFund) {
        this.salaryFund = salaryFund;
    }
}
