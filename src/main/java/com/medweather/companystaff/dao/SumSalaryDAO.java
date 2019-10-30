package com.medweather.companystaff.dao;

import com.medweather.companystaff.model.SumSalary;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class SumSalaryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public List<SumSalary> getAllSumSalary() {
        return getCurrentSession().createQuery("from SumSalary s", SumSalary.class).list();
    }

    public void save(SumSalary sumSalary) {
        getCurrentSession().save(sumSalary);
    }

    public void update(SumSalary sumSalary) {
        getCurrentSession().update(sumSalary);
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
