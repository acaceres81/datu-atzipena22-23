package talde2.model.employees.dbaccessemployees;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//The following code is to connect the programme to the hr_employee table where the employee data is
@Entity
@Table(name = "hr_employee")
public class Employee {

    // Defining the primary key for the Employee entity
    @Id
    @Column(name = "id")
    private int empId;

    // Make a join with the table hr_department. THe relation is 1:N, therefore the
    // conection is ManyToOne
    @ManyToOne
    //in order to make the connection correctly, we have to reference the column we want to join, 
    //i.e. we need to specify the column in hr_employee and the column in the hr_deprtamnet where the join is made.
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @Column(name = "name")
    private String name;

    @Column(name = "job_title")
    private String title;

    @Column(name = "work_phone")
    private String phone;

    @Column(name = "work_email")
    private String email;

    public Employee(int empId, String name, String title, String phone, String email, Department department) {
        this.empId = empId;
        this.name = name;
        this.title = title;
        this.phone = phone;
        this.email = email;
        this.department = department;

    }

    public Employee() {
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee [empId=" + empId + ", name=" + name + ", title=" + title + ", phone=" + phone + ", email="
                + email + ", department" + department + "]";
    }

}
