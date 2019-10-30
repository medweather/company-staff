package com.medweather.companystaff.api.request;

/**
 * API тела запроса для редактирования сведений о сотруднике
 */
public class EmployeeEditApi {

   private String phone;
   private String email;
   private long salary;
   private int department_id;

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

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }
}
