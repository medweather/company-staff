package com.medweather.companystaff.service;

import com.medweather.companystaff.api.request.DepartmentCreateApi;
import com.medweather.companystaff.api.response.*;
import com.medweather.companystaff.dao.DepartmentDAO;
import com.medweather.companystaff.dao.EmployeeDAO;
import com.medweather.companystaff.dao.SumSalaryDAO;
import com.medweather.companystaff.mapper.DepartmentMapper;
import com.medweather.companystaff.mapper.EmployeeMapper;
import com.medweather.companystaff.model.Department;
import com.medweather.companystaff.model.Employee;
import com.medweather.companystaff.model.SumSalary;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentDAO departmentDAO;

    private final DepartmentMapper departmentMapper;

    private final EmployeeDAO employeeDAO;

    private final EmployeeMapper employeeMapper;

    private final SumSalaryDAO sumSalaryDAO;

    public DepartmentService(DepartmentDAO departmentDAO, DepartmentMapper departmentMapper, EmployeeDAO employeeDAO, EmployeeMapper employeeMapper, SumSalaryDAO sumSalaryDAO) {
        this.departmentDAO = departmentDAO;
        this.departmentMapper = departmentMapper;
        this.employeeDAO = employeeDAO;
        this.employeeMapper = employeeMapper;
        this.sumSalaryDAO = sumSalaryDAO;
    }

    public ResponseApi createDepartment(DepartmentCreateApi departmentCreateApi) {

        Department department = new Department();

        department.setName(departmentCreateApi.getName());
        department.setParent_id(departmentDAO.getDepartmentByName(departmentCreateApi.getNameOfParent()));
        department.setFromDate(new Date());

        departmentDAO.createDepartment(department);

        SumSalary sumSalary = new SumSalary();
        sumSalary.setDepartment(department);
        sumSalary.setDepartmentName(department.getName());
        sumSalaryDAO.save(sumSalary);

        DepartmentApi departmentApi = departmentMapper.toApi(department);
        return new ResponseApi("none", new Date().getTime(), departmentApi);
    }

    public ResponseApi searchDepartment(String name) {

        List<Department> departments = departmentDAO.searchDepartments(name);
        return fillListDepartmentsApi(departments);
    }

    public AbstractResponse editNameOfDepartment(int id, String name) {

        final AbstractResponse[] response = new AbstractResponse[1];
        Department department = departmentDAO.getDepartmentById(id);
        List<String> departmentList = departmentDAO.getAllDepartmentNames();
        departmentList.forEach(d -> {
            if(!d.toLowerCase().trim().equals(name.toLowerCase().trim())) {
                department.setName(name);
                departmentDAO.editDepartment(department);
                response[0] = new ResponseApi("none", new Date().getTime(), fillDepartmentApi(department));
            }
            else {
                response[0] = new ErrorApi("invalid_request", "Такой отдел уже существует");
                response[0].setSuccess(false);
            }
        });

        return response[0];
    }

    public AbstractResponse deleteDepartment(int id) {

        AbstractResponse response;
        Department department = departmentDAO.getDepartmentById(id);
        DepartmentDeleteApi departmentDeleteApi = new DepartmentDeleteApi();
        if(employeeDAO.transferAllEmployee(department).isEmpty()) {
            department.setParent_id(null);
            departmentDeleteApi.setId(department.getId());
            departmentDAO.deleteDepartment(department);
            response = new ResponseApi("none", new Date().getTime(), departmentDeleteApi);
        }
        else {
            response = new ErrorApi("invalid_request", "Отдел не может быть удален, так как в нем числятся сотрудники");
            response.setSuccess(false);
        }

        return response;
    }

    public ResponseApi getInfoOfDepartment(int id) {

        Department department = departmentDAO.getDepartmentById(id);
        DepartmentInfoApi departmentInfoApi = new DepartmentInfoApi();
        departmentInfoApi.setId(department.getId());
        departmentInfoApi.setName(department.getName());
        departmentInfoApi.setFromDate(department.getFromDate().getTime());
        Employee head = employeeDAO.getHeadEmployeeByDepartment(department);
        departmentInfoApi.setManager(employeeMapper.toApi(head));
        departmentInfoApi.setCountOfEmployees(employeeDAO.getCountEmployeesOfDepartment(department));

        return new ResponseApi("none", new Date().getTime(), departmentInfoApi);
    }

    public ResponseApi getSubDepartmentsLevelBelow(int id) {

        return fillListDepartmentsApi(departmentDAO.getSubDepartmentsLevelBelow(departmentDAO.getDepartmentById(id)));
    }

    public ResponseApi getAllSubDepartment(int id) {

        return fillListDepartmentsApi(departmentDAO.getAllSubDepartments(departmentDAO.getDepartmentById(id)));
    }

    public ResponseApi getDepartmentAfterTransfer(int id, int parent_id) {

        Department department = departmentDAO.getDepartmentById(id);
        department.setParent_id(departmentDAO.getDepartmentById(parent_id));
        departmentDAO.editDepartment(department);

        return new ResponseApi("none", new Date().getTime(), fillDepartmentApi(department));
    }

    public ResponseApi getAllParentDepartments(int id) {

        return fillListDepartmentsApi(departmentDAO.getAllParentDepartments(departmentDAO.getDepartmentById(id)));
    }

    public ResponseApi getSalaryFundOfDepartment(int id) {

        DepartmentSalaryFundApi salaryFundApi = new DepartmentSalaryFundApi();
        salaryFundApi.setSalaryFund(departmentDAO.getSalaryFundOfDepartment(departmentDAO.getDepartmentById(id)));

        return new ResponseApi("none", new Date().getTime(), salaryFundApi);
    }

    private DepartmentListApi fillListDepartmentsApi(List<Department> departments) {

        DepartmentListApi departmentListApi = new DepartmentListApi();
        departmentListApi.setData(departments.stream().map(this::fillDepartmentApi)
                .collect(Collectors.toList()));
        departmentListApi.setSuccess(true);

        return departmentListApi;
    }

    private DepartmentApi fillDepartmentApi(Department department) {
        return departmentMapper.toApi(department);
    }
}
