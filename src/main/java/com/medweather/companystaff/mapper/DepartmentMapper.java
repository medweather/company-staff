package com.medweather.companystaff.mapper;

import com.medweather.companystaff.api.response.DepartmentApi;
import com.medweather.companystaff.model.Department;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class DepartmentMapper extends Mapper<Department, DepartmentApi>{

    private final ModelMapper modelMapper;

    @Autowired
    public DepartmentMapper(ModelMapper modelMapper) {
        super(Department.class, DepartmentApi.class);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(Department.class, DepartmentApi.class)
                .addMappings(m -> m.skip(DepartmentApi::setParent_id))
                .setPostConverter(toApiConverter());
    }

    @Override
    void mapSpecificFieldsEA(Department source, DepartmentApi destination) {

        if (Objects.isNull(source)) {
            return;
        }

        if (!Objects.isNull(source.getParent_id())) {
            destination.setParent_id(source.getParent_id().getId());
        }
    }
}
