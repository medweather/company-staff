package com.medweather.companystaff.controller;

import com.medweather.companystaff.api.request.EmployeeCreateApi;
import com.medweather.companystaff.api.request.EmployeeEditApi;
import com.medweather.companystaff.api.response.AbstractResponse;
import com.medweather.companystaff.api.response.ResponseApi;
import com.medweather.companystaff.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * Список всех сотрудников
     *
     * @return
     */
    @GetMapping("")
    public ResponseEntity getAllEmployees() {

        ResponseApi responseApi = employeeService.getAllEmployee();
        return responseApi == null ? badRequestResponse() : new ResponseEntity<>(responseApi, HttpStatus.OK);
    }

    /**
     * Перевод всех сотрудников отдела в другой отдел
     *
     * @param department_id
     * @param other_department_id
     * @return
     */
    @PutMapping("")
    public ResponseEntity transferAllEmployees(
            @RequestParam int department_id,
            @RequestParam int other_department_id) {

        ResponseApi responseApi = employeeService.transferAllEmployees(department_id, other_department_id);
        return responseApi == null ? badRequestResponse() : new ResponseEntity<>(responseApi, HttpStatus.OK);
    }

    /**
     * Поиск сотрудников
     *
     * @param last_name
     * @param first_name
     * @return
     */
    @GetMapping("/search")
    public ResponseEntity searchEmployee(
            @RequestParam String last_name,
            @RequestParam String first_name) {

        ResponseApi responseApi = employeeService.searchEmployee(last_name, first_name);
        return responseApi == null ? badRequestResponse() : new ResponseEntity<>(responseApi, HttpStatus.OK);
    }

    /**
     * Информация о сотруднике
     *
     * @param id
     * @return
     */
    @GetMapping("/{id:\\d+}")
    public ResponseEntity getEmployee(
            @PathVariable int id) {

        AbstractResponse response = employeeService.getInfoEmployee(id);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    /**
     * Редактирование информации о сотруднике
     *
     * @param id
     * @param employeeEditApi
     * @return
     */
    @PutMapping("/{id:\\d+}")
    public ResponseEntity editEmployee(
            @PathVariable int id,
            @RequestBody EmployeeEditApi employeeEditApi) {

        AbstractResponse response = employeeService.editInfoEmployee(id, employeeEditApi);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    /**
     * Увольнение сотрудника
     *
     * @param id
     * @param dismissal
     * @return
     */
    @PutMapping("/{id:\\d+}/dismissal")
    public ResponseEntity dismissalEmployee(
            @PathVariable int id,
            @RequestParam String dismissal) {

        AbstractResponse response = employeeService.dismissalEmployee(id, dismissal);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    /**
     * Перевод сотрудника в другой отдел
     *
     * @param id
     * @param department_id
     * @return
     */
    @PutMapping("/{id:\\d+}/transfer")
    public ResponseEntity transferEmployee(
            @PathVariable int id,
            @RequestParam int department_id) {

        AbstractResponse response = employeeService.transferEmployee(id, department_id);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    /**
     * Сведения о руководителе данного сотрудника
     *
     * @param id
     * @return
     */
    @GetMapping("/{id:\\d+}/head")
    public ResponseEntity getHeadOfEmployee(
            @PathVariable int id) {

        AbstractResponse response = employeeService.getHeadOfEmployee(id);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> badRequestResponse() {
        Map<String, String> response = new HashMap<>();
        response.put("error", "invalid_request");
        response.put("error_description", "not_found");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
