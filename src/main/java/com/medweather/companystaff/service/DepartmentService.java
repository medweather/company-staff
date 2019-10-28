package com.medweather.companystaff.service;

import com.medweather.companystaff.api.request.DepartmentCreateApi;
import com.medweather.companystaff.api.response.DepartmentApi;
import com.medweather.companystaff.api.response.ResponseApi;
import com.medweather.companystaff.dao.DepartmentDAO;
import com.medweather.companystaff.mapper.DepartmentMapper;
import com.medweather.companystaff.model.Department;
import org.springframework.stereotype.Service;

import java.util.Date;

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
}
