package talde2.model.employees.dbaccessemployees;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "hr_department")
public class Department {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String department;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Department(String deprtament) {
        this.department = deprtament;
    }

    public Department() {
    }

    @Override
    public String toString() {
        return department + "";
    }

    
    

}
