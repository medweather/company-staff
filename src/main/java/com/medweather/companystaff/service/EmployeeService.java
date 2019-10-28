package com.medweather.companystaff.service;

import com.medweather.companystaff.api.request.EmployeeCreateApi;
import com.medweather.companystaff.api.response.*;
import com.medweather.companystaff.dao.DepartmentDAO;
import com.medweather.companystaff.dao.EmployeeDAO;
import com.medweather.companystaff.mapper.EmployeeMapper;
import com.medweather.companystaff.model.Employee;
import com.medweather.companystaff.model.enumiration.Gender;
import com.medweather.companystaff.model.enumiration.Position;
import com.medweather.companystaff.util.Validate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeDAO employeeDAO;

    private final DepartmentDAO departmentDAO;

    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeDAO employeeDAO, DepartmentDAO departmentDAO, EmployeeMapper employeeMapper) {
        this.employeeDAO = employeeDAO;
        this.departmentDAO = departmentDAO;
        this.employeeMapper = employeeMapper;
    }

    public AbstractResponse createEmployee(EmployeeCreateApi employeeCreateApi) {

        AbstractResponse response;

        if(Validate.isValidDate(employeeCreateApi.getBirthday()) &&
            Validate.isValidEmail(employeeCreateApi.getEmail()) &&
            Validate.isValidFIO(employeeCreateApi.getFirstName(), employeeCreateApi.getLastName(), employeeCreateApi.getPatronymic()) &&
            Validate.isValidPhone(employeeCreateApi.getPhone()) &&
            Validate.isValidSalary(String.valueOf(employeeCreateApi.getSalary())) &&
            isSalaryCompareHead(employeeCreateApi.getSalary(), employeeCreateApi.getDepartmentId())) {
            try {

                Employee employee = new Employee();

                employee.setFirstName(employeeCreateApi.getFirstName());
                employee.setLastName(employeeCreateApi.getLastName());
                employee.setPatronymic(employeeCreateApi.getPatronymic());
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(employeeCreateApi.getBirthday());
                employee.setBirthday(date);
                employee.setEmail(employeeCreateApi.getEmail());
                employee.setDepartment(departmentDAO.getDepartmentById(employeeCreateApi.getDepartmentId()));
                employee.setGender(Gender.valueOf(employeeCreateApi.getGender()));
                employee.setFromDate(new Date());
                employee.setPhone(employeeCreateApi.getPhone());
                employee.setSalary(employeeCreateApi.getSalary());
                if(employeeCreateApi.getPosition().equals("HEAD") && !isHeadExists(employeeCreateApi.getDepartmentId())) {
                    employee.setHead(true);
                    employee.setPosition(Position.HEAD);
                }
                else if(employeeCreateApi.getPosition().equals("HEAD") && isHeadExists(employeeCreateApi.getDepartmentId())) {
                    response = new ErrorApi("invalid_request", "В этом отделе уже есть руководитель");
                    response.setSuccess(false);
                    return response;
                }
                employee.setPosition(Position.valueOf(employeeCreateApi.getPosition()));
                employeeDAO.addEmployee(employee);

                return new ResponseApi("none", new Date().getTime(), fillEmployeeApi(employee));

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        response = new ErrorApi("invalid_request", "Формат вводимых данных должен содержать следующие символы:  " +
                "email: буквы из латиницы или числа от 0 до 9, символы '@' и '.'; " +
                "birthday: формат даты рождения должен быть следующий: 'yyyy-MM-dd и дата рождения не должна быть больше даты приема на работу; " +
                "FIO: имя, фамилия и отчество должны иметь буквы из кириллицы; " +
                "phone: телефон может содержать символы '+-0123456789()'; " +
                "salary: оклад может состоять из цифр от 0 до 9 и не должен превышать оклад руководителя; " +
                "position: руководитель в отделе может быть только один");
        response.setSuccess(false);
        return response;
    }

    public ResponseApi getAllEmployee() {

        return fillListEmployeeApi(employeeDAO.getAllEmployees());
    }

    public ResponseApi transferAllEmployees(int departmentId, int otherDepartmentId) {

        List<Employee> employees = employeeDAO.transferAllEmployee(departmentDAO.getDepartmentById(departmentId));
        for(Employee employee : employees) {
            if(!employee.getPosition().toString().equals("HEAD")) {
                employee.setDepartment(departmentDAO.getDepartmentById(otherDepartmentId));
                employeeDAO.editEmployee(employee);
            }
        }

        return fillListEmployeeApi(employees);
    }

    public ResponseApi searchEmployee(String lastName, String firstName) {

        List<Employee> employees = employeeDAO.searchEmployees(lastName, firstName);
        return fillListEmployeeApi(employees);
    }

    private EmployeeListApi fillListEmployeeApi(List<Employee> employees) {

        EmployeeListApi employeeListApi = new EmployeeListApi();
        employeeListApi.setData(employees.stream().map(this::fillEmployeeApi)
                .collect(Collectors.toList()));
        employeeListApi.setSuccess(true);

        return employeeListApi;
    }

    private EmployeeApi fillEmployeeApi(Employee employee) {
        return employeeMapper.toApi(employee);
    }

    private boolean isSalaryCompareHead(long salary, int department_id) {

        Employee head = employeeDAO.getHeadEmployeeByDepartment(departmentDAO.getDepartmentById(department_id));
        if(head != null) {
            return salary < head.getSalary();
        }
        return true;
    }

    private boolean isHeadExists(int department_id) {

        Employee head = employeeDAO.getHeadEmployeeByDepartment(departmentDAO.getDepartmentById(department_id));
        return head != null;
    }
}
