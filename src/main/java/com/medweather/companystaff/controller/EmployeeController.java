package com.medweather.companystaff.controller;

import com.medweather.companystaff.api.request.EmployeeCreateApi;
import com.medweather.companystaff.api.response.AbstractResponse;
import com.medweather.companystaff.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Создание сотрудника
     *
     * @param employeeCreateApi
     * @return
     */
    @PostMapping("")
    public ResponseEntity createEmployee(
            @RequestBody EmployeeCreateApi employeeCreateApi) {

        AbstractResponse response = employeeService.createEmployee(employeeCreateApi);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
