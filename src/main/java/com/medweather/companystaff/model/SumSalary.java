package com.medweather.companystaff.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "sum_salary")
public class SumSalary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "salary_fund")
    private BigDecimal salaryFund;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public BigDecimal getSalaryFund() {
        return salaryFund;
    }

    public void setSalaryFund(BigDecimal salaryFund) {
        this.salaryFund = salaryFund;
    }
}
