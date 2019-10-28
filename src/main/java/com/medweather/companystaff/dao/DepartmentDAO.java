package com.medweather.companystaff.dao;

import com.medweather.companystaff.model.Department;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public class DepartmentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Department getDepartmentById(int id) {
        return getCurrentSession().get(Department.class, id);
    }

    public Department getDepartmentByName(String name) {
        return (Department) getCurrentSession().createCriteria(Department.class).
                add(Restrictions.eq("name", name)).uniqueResult();
    }

    public List<Department> searchDepartments(String name) {
        Criteria criteria = getCurrentSession().createCriteria(Department.class);
        if(!name.isEmpty()) {
            criteria.add(Restrictions.like("name", "%" + name + "%"));
        }
        return criteria.list();
    }

    public List<Department> getSubDepartmentsLevelBelow(Department department) {
        return getCurrentSession().createQuery("from Department d where d.parent_id = "
                + department.getId(), Department.class).list();
    }

    public List<Department> getAllSubDepartments(Department department) {

        return getCurrentSession().createSQLQuery("with recursive nodes(id, name, from_date, parent_id) as " +
                "(select d1.id, d1.name, d1.from_date, d1.parent_id from department d1 where d1.parent_id = " + department.getId() +
                " union " +
                "select d2.id, d2.name, d2.from_date, d2.parent_id from department d2, nodes d1 where d2.parent_id = d1.id) " +
                "select d.id, d.name, d.from_date, d.parent_id from department d join nodes n on d.id = n.id").
                addEntity(Department.class).list();
    }

    public List<Department> getAllParentDepartments(Department department) {

        return getCurrentSession().createSQLQuery("with recursive nodes(id, name, from_date, parent_id) as " +
                "(select d1.id, d1.name, d1.from_date, d1.parent_id from department d1 where d1.id = " + department.getParent_id().getId() +
                " union " +
                "select d2.id, d2.name, d2.from_date, d2.parent_id from department d2, nodes d1 where d2.id = d1.parent_id) " +
                "select d.id, d.name, d.from_date, d.parent_id from department d join nodes n on d.id = n.id").
                addEntity(Department.class).list();
    }

    public BigDecimal getSalaryFundOfDepartment(Department department) {

        return (BigDecimal) getCurrentSession().createSQLQuery("select sum(e.salary) from department d " +
                "join employee e on d.id = e.department_id where d.id = " + department.getId()).uniqueResult();
    }

    public List<Department> getAllDepartments() {
        return getCurrentSession().createQuery("from Department d", Department.class).list();
    }

    public List<String> getAllDepartmentNames() {
        return getCurrentSession().createSQLQuery("select name from department").list();
    }

    public void createDepartment(Department department) {
        getCurrentSession().save(department);
    }

    public void editDepartment(Department department) {
        getCurrentSession().update(department);
    }

    public void deleteDepartment(Department department) {
        getCurrentSession().delete(department);
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
