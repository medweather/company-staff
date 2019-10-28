package com.medweather.companystaff.controller;

import com.medweather.companystaff.api.request.DepartmentCreateApi;
import com.medweather.companystaff.api.response.AbstractResponse;
import com.medweather.companystaff.api.response.ResponseApi;
import com.medweather.companystaff.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * Создание департамента
     *
     * @param departmentCreateApi
     * @return
     */
    @PostMapping("")
    public ResponseEntity createDepartment(
            @RequestBody DepartmentCreateApi departmentCreateApi) {

        ResponseApi responseApi = departmentService.createDepartment(departmentCreateApi);
        return responseApi == null ? badRequestResponse() : new ResponseEntity<>(responseApi, HttpStatus.OK);
    }

    private ResponseEntity<Object> badRequestResponse() {
        Map<String, String> response = new HashMap<>();
        response.put("error", "invalid_request");
        response.put("error_description", "not_found");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
