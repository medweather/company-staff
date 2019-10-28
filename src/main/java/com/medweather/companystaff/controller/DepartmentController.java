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

    /**
     * Поиск департамента
     *
     * @param name
     * @return
     */
    @GetMapping("")
    public ResponseEntity searchDepartment(
            @RequestParam String name) {

        ResponseApi responseApi = departmentService.searchDepartment(name);
        return new ResponseEntity<>(responseApi, HttpStatus.OK);
    }

    /**
     * Изменение названия департамента
     *
     * @param id
     * @param name
     * @return
     */
    @PutMapping("/{id:\\d+}")
    public ResponseEntity editDepartment(
            @PathVariable int id,
            @RequestParam String name) {

        AbstractResponse response = departmentService.editNameOfDepartment(id, name);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> badRequestResponse() {
        Map<String, String> response = new HashMap<>();
        response.put("error", "invalid_request");
        response.put("error_description", "not_found");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
