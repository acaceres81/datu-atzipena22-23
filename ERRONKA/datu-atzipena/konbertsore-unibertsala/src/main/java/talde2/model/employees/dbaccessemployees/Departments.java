package talde2.model.employees.dbaccessemployees;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Departments")
public class Departments {
    List<Department> departments;

    public List<Department> getDepartments() {
        return departments;
    }

    // Method to add an employee to the list
    public void add(Department department) {
        if (this.departments == null) {
            this.departments = new ArrayList<Department>();
        }
        this.departments.add(department);
    }

    // Overriding the toString() method to represent Products as a string
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        for (Department d : this.departments) {
            str.append(d.toString());
        }
        return str.toString();
    }

 
    
}
