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

    /**
     * Удаление департамента
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id:\\d+}")
    public ResponseEntity deleteDepartment(
            @PathVariable int id) {

        AbstractResponse response = departmentService.deleteDepartment(id);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    /**
     * Информация о департаменте
     *
     * @param id
     * @return
     */
    @GetMapping("/{id:\\d+}")
    public ResponseEntity getInfoByDepartment(
            @PathVariable int id) {

        ResponseApi responseApi = departmentService.getInfoOfDepartment(id);
        return responseApi == null ? badRequestResponse() : new ResponseEntity<>(responseApi, HttpStatus.OK);
    }

    /**
     * Информация о подчиненных данному отделу департаментах (на уровень ниже)
     *
     * @param id
     * @return
     */
    @GetMapping("/{id:\\d+}/departments")
    public ResponseEntity getSubDepartmentsLevelBelow(
            @PathVariable int id) {

        ResponseApi responseApi = departmentService.getSubDepartmentsLevelBelow(id);
        return new ResponseEntity<>(responseApi, HttpStatus.OK);
    }

    /**
     * Информация о всех подчиненных данному отделу департаментах
     *
     * @param id
     * @return
     */
    @GetMapping("/{id:\\d+}/sub_departments")
    public ResponseEntity getAllSubDepartments(
            @PathVariable int id) {

        ResponseApi responseApi = departmentService.getAllSubDepartment(id);
        return new ResponseEntity<>(responseApi, HttpStatus.OK);
    }

    /**
     * Перенос департамента в другой департамент
     *
     * @param id
     * @param parent_id
     * @return
     */
    @PutMapping("/{id:\\d+}/parent_department")
    public ResponseEntity getDepartmentAfterTransfer(
            @PathVariable int id,
            @RequestParam int parent_id) {

        ResponseApi responseApi = departmentService.getDepartmentAfterTransfer(id, parent_id);
        return responseApi == null ? badRequestResponse() : new ResponseEntity<>(responseApi, HttpStatus.OK);
    }

    /**
     * Информация о всех вышестоящих над данным отделом департаментах
     *
     * @param id
     * @return
     */
    @GetMapping("/{id:\\d+}/parent_department")
    public ResponseEntity getAllParentDepartments(
            @PathVariable int id) {

        ResponseApi responseApi = departmentService.getAllParentDepartments(id);
        return new ResponseEntity<>(responseApi, HttpStatus.OK);
    }

    private ResponseEntity<Object> badRequestResponse() {
        Map<String, String> response = new HashMap<>();
        response.put("error", "invalid_request");
        response.put("error_description", "not_found");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
