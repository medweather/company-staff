package com.medweather.companystaff.service;

import com.medweather.companystaff.dao.DepartmentDAO;
import com.medweather.companystaff.dao.SumSalaryDAO;
import com.medweather.companystaff.model.SumSalary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SumSalaryService {

    private static final Logger log = LoggerFactory.getLogger(SumSalaryService.class);

    private final SumSalaryDAO sumSalaryDAO;

    private final DepartmentDAO departmentDAO;

    public SumSalaryService(SumSalaryDAO sumSalaryDAO, DepartmentDAO departmentDAO) {
        this.sumSalaryDAO = sumSalaryDAO;
        this.departmentDAO = departmentDAO;
    }

    @Scheduled(initialDelayString = "15000", fixedDelayString = "300000")
    public void updateSumSalary() {

        log.info("Start update salary fund of department");
        List<SumSalary> sumSalaries = sumSalaryDAO.getAllSumSalary();
        sumSalaries.forEach(sumSalary -> {
            sumSalary.setSalaryFund(departmentDAO.getSalaryFundOfDepartment(sumSalary.getDepartment()));
            sumSalaryDAO.update(sumSalary);
        });
        log.info("End update salary fund of department");
    }
}
