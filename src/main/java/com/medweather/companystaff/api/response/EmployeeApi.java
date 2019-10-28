package com.medweather.companystaff.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * API информация о сотруднике
 */
public class EmployeeApi extends AbstractResponse {

    private int id;

    @JsonProperty(value = "first_name")
    private String firstName;

    @JsonProperty(value = "last_name")
    private String lastName;

    private String patronymic;

    private gender gender;

    private long birthday;

    private String phone;

    private String email;

    @JsonProperty(value = "from_date")
    private long fromDate;

    private long dismissal;

    private position position;

    private long salary;

    private boolean head;

    @JsonProperty(value = "department_id")
    private int department;

    public enum position {HEAD, FINANCIAL_ANALYST,
            PERSONAL_FINANCIAL_ADVISOR, ACCOUNTANT, AUDITORS, LOAN_OFFICER,
            BANK_TELLERS, TRADER, CHIEF_ACCOUNTANT, LEGAL}

    public enum gender {MAN, WOMAN}


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

    public EmployeeApi.gender getGender() {
        return gender;
    }

    public void setGender(EmployeeApi.gender gender) {
        this.gender = gender;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
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

    public long getFromDate() {
        return fromDate;
    }

    public void setFromDate(long fromDate) {
        this.fromDate = fromDate;
    }

    public long getDismissal() {
        return dismissal;
    }

    public void setDismissal(long dismissal) {
        this.dismissal = dismissal;
    }

    public EmployeeApi.position getPosition() {
        return position;
    }

    public void setPosition(EmployeeApi.position position) {
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

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }
}
