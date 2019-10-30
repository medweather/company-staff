package com.medweather.companystaff.dao;

import com.medweather.companystaff.model.DepartmentEventInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class DepartmentEventInfoDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(DepartmentEventInfo departmentEventInfo) {
        getCurrentSession().save(departmentEventInfo);
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
