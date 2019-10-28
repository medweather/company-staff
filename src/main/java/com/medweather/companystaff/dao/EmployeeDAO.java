package com.medweather.companystaff.dao;

import com.medweather.companystaff.model.Department;
import com.medweather.companystaff.model.Employee;
import com.medweather.companystaff.model.enumiration.Position;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public class EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Employee getEmployeeById(int id) {
        return getCurrentSession().get(Employee.class, id);
    }

    public List<Employee> getAllEmployees() {
        return getCurrentSession().createQuery("from Employee e", Employee.class).getResultList();
    }

    public void addEmployee(Employee employee) {
        getCurrentSession().save(employee);
    }

    public List<Employee> searchEmployees(String lastName, String firstName) {

        Criteria criteria = getCurrentSession().createCriteria(Employee.class);
        if(!firstName.isEmpty()) {
            criteria.add(Restrictions.like("firstName", "%" + firstName + "%"));
        }
        if(!lastName.isEmpty()) {
            criteria.add(Restrictions.like("lastName", "%" + lastName + "%"));
        }
        return criteria.list();
    }

    public Employee getHeadEmployeeByDepartment(Department department) {
        return getCurrentSession().createQuery("from Employee e where e.department = "
                + department.getId() + " and position in ('HEAD')", Employee.class).uniqueResult();
    }

    public List<Employee> transferAllEmployee(Department department) {
        return getCurrentSession().createQuery("from Employee e where e.department = "
                + department.getId(), Employee.class).list();
    }

    public void editEmployee(Employee employee) {
        getCurrentSession().update(employee);
    }

    public long getCountEmployeesOfDepartment(Department department) {
        return getCurrentSession().createQuery("select count(*) as count_employees from Employee e where e.department = "
                + department.getId(), Long.class).uniqueResult();
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
