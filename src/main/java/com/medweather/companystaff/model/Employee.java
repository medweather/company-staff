package com.medweather.companystaff.model;

import com.medweather.companystaff.model.enumiration.Gender;
import com.medweather.companystaff.model.enumiration.Position;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * имя
     */
    @Column(name = "first_name")
    @NotNull
    private String firstName;

    /**
     * фамилия
     */
    @Column(name = "last_name")
    @NotNull
    private String lastName;

    /**
     * отчество
     */
    private String patronymic;

    /**
     * пол
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", columnDefinition = "ENUM('MAN', 'WOMAN')")
    @NotNull
    private Gender gender;

    /**
     * день рождения
     */
    @NotNull
    private Date birthday;

    /**
     * телефон
     */
    @NotNull
    private String phone;

    /**
     * электронная почта
     */
    @NotNull
    private String email;

    /**
     * дата приема на работу
     */
    @Column(name = "from_date")
    @NotNull
    private Date fromDate;

    /**
     * дата увольнения
     */
    private Date dismissal;

    /**
     * должность
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "position", columnDefinition = "ENUM('HEAD', 'FINANCIAL_ANALYST'," +
            " 'PERSONAL_FINANCIAL_ADVISOR', 'ACCOUNTANT', 'AUDITORS', 'LOAN_OFFICER'," +
            " 'BANK_TELLERS', 'TRADER', 'CHIEF_ACCOUNTANT', 'LEGAL')")
    @NotNull
    private Position position;

    /**
     * оклад
     */
    @NotNull
    private long salary;

    /**
     * признак, что сотрудник является руководителем
     */
    @NotNull
    private boolean head;

    /**
     * отдел, в котором работает сотрудник
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDismissal() {
        return dismissal;
    }

    public void setDismissal(Date dismissal) {
        this.dismissal = dismissal;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public boolean isHead() {
        return head;
    }

    public void setHead(boolean head) {
        this.head = head;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }
}
