package com.medweather.companystaff.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * название отдела
     */
    @NotNull
    private String name;

    /**
     * дата создания
     */
    @Column(name = "from_date")
    @NotNull
    private Date fromDate;

    /**
     * отдел-родитель
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private Department parent_id;

    /**
     * список дочерних отделов
     */
    @OneToMany(mappedBy = "parent_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Department> departments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Department getParent_id() {
        return parent_id;
    }

    public void setParent_id(Department parent_id) {
        this.parent_id = parent_id;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
