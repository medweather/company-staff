package com.medweather.companystaff.service;

import com.medweather.companystaff.api.request.DepartmentCreateApi;
import com.medweather.companystaff.api.response.*;
import com.medweather.companystaff.dao.DepartmentDAO;
import com.medweather.companystaff.mapper.DepartmentMapper;
import com.medweather.companystaff.model.Department;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentDAO departmentDAO;

    private final DepartmentMapper departmentMapper;

    public DepartmentService(DepartmentDAO departmentDAO, DepartmentMapper departmentMapper) {
        this.departmentDAO = departmentDAO;
        this.departmentMapper = departmentMapper;
    }

    public ResponseApi createDepartment(DepartmentCreateApi departmentCreateApi) {

        Department department = new Department();

        department.setName(departmentCreateApi.getName());
        department.setParent_id(departmentDAO.getDepartmentByName(departmentCreateApi.getNameOfParent()));
        department.setFromDate(new Date());

        departmentDAO.createDepartment(department);

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
