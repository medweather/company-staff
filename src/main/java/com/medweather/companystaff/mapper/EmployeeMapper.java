package com.medweather.companystaff.mapper;

import com.medweather.companystaff.api.response.EmployeeApi;
import com.medweather.companystaff.model.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class EmployeeMapper extends Mapper<Employee, EmployeeApi> {

    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeMapper(ModelMapper modelMapper) {
        super(Employee.class, EmployeeApi.class);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(Employee.class, EmployeeApi.class)
                .addMappings(m -> m.skip(EmployeeApi::setDepartment))
                .setPostConverter(toApiConverter());
    }

    @Override
    void mapSpecificFieldsEA(Employee source, EmployeeApi destination) {

        if (Objects.isNull(source)) {
            return;
        }

        if (!Objects.isNull(source.getDepartment())) {
            destination.setDepartment(source.getDepartment().getId());
        }
    }
}
