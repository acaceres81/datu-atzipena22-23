package talde2.model.employees.dbaccessemployees;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Employees")
public class Employees {
    List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    // Method to add an employee to the list
    public void add(Employee employee) {
        if (this.employees == null) {
            this.employees = new ArrayList<Employee>();
        }
        this.employees.add(employee);
    }

    // Overriding the toString() method to represent Products as a string
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        for (Employee e : this.employees) {
            str.append(e.toString());
        }
        return str.toString();
    }

 
    
}
